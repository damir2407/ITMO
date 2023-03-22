import json
from enum import Enum


class Opcode(str, Enum):
    CMP = 'CMP'
    JB = 'JB'
    MOV = 'MOV'
    MOD = 'MOD'
    ADD = 'ADD'
    JNZ = 'JNZ'
    JMP = 'JMP'
    READ = 'READ'
    VAR = 'VAR'
    PRINT = 'PRINT'
    PRINT_CH_INC = 'PRINT_CH#'
    SV = 'SV'
    LD_POINT = 'LD*'
    JE = 'JE'
    INC = 'INC'
    PRINT_CH = 'PRINT_CH'
    INTER_END = 'INTER_END'
    INTER_START = 'INTER_START'
    HALT = 'HALT'


class SelectType(int, Enum):
    INPUT = 0
    DATA = 1
    ALU = 2
    ACC = 3
    BR = 4
    ARG = 5

class StateType(int, Enum):
    ON_INTERRUPT = 0
    READY = 1


args_count = {
    Opcode.CMP.value: 2,
    Opcode.JB.value: 1,
    Opcode.MOV.value: 2,
    Opcode.ADD.value: 2,
    Opcode.JNZ.value: 1,
    Opcode.JMP.value: 1,
    Opcode.READ.value: 0,
    Opcode.HALT.value: 0,
    Opcode.PRINT.value: 1,
    Opcode.MOD.value: 2,
    Opcode.PRINT_CH_INC.value: 1,
    Opcode.JE.value: 1,
    Opcode.LD_POINT.value: 1,
    Opcode.SV.value: 1,
    Opcode.PRINT_CH.value: 0,
    Opcode.INTER_END.value: 0,
    Opcode.INTER_START.value: 0
}


def write_code(filename, code, data_file, data):
    """Записать машинный код в файл."""
    with open(filename, "w", encoding="utf-8") as file:
        file.write(json.dumps(code, indent=4))

    with open(data_file, "w", encoding="utf-8") as file:
        file.write(json.dumps(data, indent=4))


def read_code(filename, data_file):
    """Прочесть машинный код из файла."""
    with open(filename, encoding="utf-8") as file:
        code = json.loads(file.read())

    with open(data_file, encoding="utf-8") as file:
        if data_file == "":
            data = []
        else:
            data = json.loads(file.read())

    return code, data
