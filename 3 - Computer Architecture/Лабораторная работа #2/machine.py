#!/usr/bin/python3
# pylint: disable=missing-function-docstring
# pylint: disable=invalid-name
# pylint: disable=consider-using-f-string

import logging
import sys

from ast import literal_eval
from isa import Opcode, read_code, SelectType, StateType


class DataPath:

    def __init__(self, data_memory_size, data, input_buffer):
        assert len(data) < data_memory_size, "Data exceeds the maximum memory size!"
        assert data_memory_size > 0, "Data memory size should be non-zero"
        self.data_memory_size = data_memory_size
        self.data_address = 0
        self.br = 0
        self.acc = 0
        self.alu = 0
        self.data = list(map(int, data)) + [0] * (self.data_memory_size - len(data))
        self.input_buffer = input_buffer
        self.output_buffer = []

    def latch_data_addr(self, sel: SelectType, addr=0):
        assert 0 <= addr <= self.data_memory_size, \
            f"data_address out of bounds [0;{self.data_memory_size}]"

        if sel is SelectType.ACC:
            self.data_address = self.acc
        elif sel is SelectType.BR:
            self.data_address = self.br
        elif sel is SelectType.ARG:
            self.data_address = addr

    def latch_br(self):
        self.br = self.data_address

    def latch_acc(self, sel: SelectType):
        if sel is SelectType.DATA:
            self.acc = self.data[self.data_address]
        elif sel is SelectType.INPUT:
            self.acc = ord(self.input_buffer.pop(0)[1])
        elif sel is SelectType.ALU:
            self.acc = self.alu

    def zero(self):
        return self.acc == 0

    def negative(self):
        return self.acc < 0

    def wr(self):
        self.data[self.data_address] = self.acc

    def output(self, char):
        if char:
            symbol = chr(self.acc)
        else:
            symbol = self.acc
        logging.info('output: %s << %s', repr(
            ''.join(self.output_buffer)), repr(symbol))
        self.output_buffer.append(str(symbol))

    def alu_execute(self, opcode: Opcode):

        if opcode == Opcode.ADD:
            self.alu = int(self.acc) + int(self.data[self.data_address])

        if opcode == Opcode.CMP:
            self.alu = int(self.acc) - int(self.data[self.data_address])

        if opcode == Opcode.MOD:
            self.alu = int(self.acc) % int(self.data[self.data_address])

        if opcode == Opcode.INC:
            self.alu = int(self.acc) + 1


class ControlUnit:

    def __init__(self, program, data_path):
        self.program = program
        self.program_counter = 0
        self.data_path = data_path
        self._tick = 0
        self.state = StateType.READY.value
        self.context = 0
        self.interrupt_handler_address = 0
        self.latch_interrupt_handler_address()

    def tick(self):
        self._tick += 1

    def latch_state(self, state: StateType):
        self.state = state.value

    def latch_interrupt_handler_address(self):
        for opcode in self.program:
            if opcode.get('opcode') == Opcode.INTER_START:
                self.interrupt_handler_address = opcode.get('address')

    def current_tick(self):
        return self._tick

    def latch_program_counter(self, sel):
        if sel == 1:
            self.program_counter += 1
        elif sel == 2:
            instr = self.program[self.program_counter]
            assert 'args' in instr, "Internal error"
            self.program_counter = instr['args'][0]
        elif sel == 3:
            self.program_counter = self.context
        elif sel == 4:
            self.program_counter = self.interrupt_handler_address

    def latch_context(self):
        self.context = self.program_counter

    def interrupt_check(self):
        if len(self.data_path.input_buffer) == 0 or self.state == StateType.ON_INTERRUPT.value:
            return
        if self.state == StateType.READY.value and self.current_tick() >= self.data_path.input_buffer[0][0]:
            self.latch_context()
            self.latch_state(StateType.ON_INTERRUPT)

            self.latch_program_counter(sel=4)

            logging.debug("Interrupt! TICK: {%d}", self.current_tick())

    def decode_and_execute_instruction(self):
        self.interrupt_check()
        logging.debug('%s', self)
        instr = self.program[self.program_counter]
        opcode = instr["opcode"]

        if opcode == Opcode.INTER_START:
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.HALT:
            raise StopIteration

        if opcode == Opcode.JMP:
            self.latch_program_counter(sel=2)
            self.tick()

        if opcode == Opcode.INTER_END:
            self.latch_state(StateType.READY)
            self.latch_program_counter(sel=3)
            self.tick()
            logging.debug("INTERRUPT END!")

        if opcode == Opcode.MOV:
            var_from_addr = instr["args"][-1]
            var_to_addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=var_from_addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=var_to_addr)
            self.tick()
            self.data_path.wr()
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.ADD:
            var_from_addr = instr["args"][-1]
            var_to_addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=var_from_addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=var_to_addr)
            self.tick()
            self.data_path.alu_execute(opcode)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.ALU)
            self.tick()
            self.data_path.wr()
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.CMP:
            first_var = instr["args"][0]
            second_var = instr["args"][1]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=first_var)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=second_var)
            self.tick()
            self.data_path.alu_execute(opcode)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.ALU)
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.JB:
            if self.data_path.negative():
                self.latch_program_counter(sel=2)
            else:
                self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.MOD:
            first_var = instr["args"][0]
            second_var = instr["args"][1]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=first_var)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=second_var)
            self.tick()
            self.data_path.alu_execute(opcode)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.ALU)
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.JNZ:
            if self.data_path.zero():
                self.latch_program_counter(sel=1)
            else:
                self.latch_program_counter(sel=2)
            self.tick()

        if opcode == Opcode.PRINT:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.output(char=False)
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.READ:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.INPUT)
            self.tick()
            self.data_path.wr()
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.PRINT_CH:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.output(char=True)
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.LD_POINT:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ACC)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.SV:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.wr()
            self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.JE:
            if self.data_path.zero():
                self.latch_program_counter(sel=2)
            else:
                self.latch_program_counter(sel=1)
            self.tick()

        if opcode == Opcode.PRINT_CH_INC:
            addr = instr["args"][0]
            self.data_path.latch_data_addr(sel=SelectType.ARG, addr=addr)
            self.tick()
            self.data_path.latch_br()
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.latch_data_addr(sel=SelectType.ACC)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.output(char=True)
            self.tick()

            self.data_path.latch_data_addr(sel=SelectType.BR)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.DATA)
            self.tick()
            self.data_path.alu_execute(Opcode.INC)
            self.tick()
            self.data_path.latch_acc(sel=SelectType.ALU)
            self.tick()
            self.data_path.wr()
            self.latch_program_counter(sel=1)
            self.tick()

    def __repr__(self):
        state = "{{TICK: {}, PC: {}, ADDR: {}, OUT: {}, ACC: {}}}".format(
            self._tick,
            self.program_counter,
            self.data_path.data_address,
            self.data_path.data[self.data_path.data_address],
            self.data_path.acc,
        )

        instr = self.program[self.program_counter]
        opcode = instr["opcode"]
        args = instr.get("args", '')

        action = "{} {}".format(opcode, args)

        return "{} {}".format(state, action)


def simulation(code, data_memory_size, limit, input_buffer, data):
    data_path = DataPath(data_memory_size, data, input_buffer)
    control_unit = ControlUnit(code, data_path)
    instr_counter = 0

    try:
        while True:
            if limit <= instr_counter:
                logging.error("Limit is exceeded!")
                break
            control_unit.decode_and_execute_instruction()
            instr_counter += 1
    except StopIteration:
        pass

    logging.info('output_buffer: %s', repr(''.join(data_path.output_buffer)))
    return ''.join(data_path.output_buffer), instr_counter, control_unit.current_tick()


def main(args):
    assert 2 <= len(args) <= 3, "Wrong arguments: machine.py <code_file> <data_file> [<input_file> - if necessary]"

    if len(args) == 3:
        code_file, data_file, input_file = args
    else:
        code_file = args[0]
        data_file = args[1]
        input_file = ""

    code, data = read_code(code_file, data_file)

    if input_file != "":
        with open(input_file, encoding="utf-8") as file:
            input_text = file.read()
            if input_text == "":
                input_list = []
            else:
                input_list = literal_eval(input_text)
    else:
        input_list = []

    output, instr_counter, ticks = simulation(code, 1024, 350, input_list, data)

    print(''.join(output))
    print("instruction counter: ", instr_counter, "ticks: ", ticks)
    return output


if __name__ == '__main__':
    logging.getLogger().setLevel(logging.DEBUG)
    main(sys.argv[1:])
