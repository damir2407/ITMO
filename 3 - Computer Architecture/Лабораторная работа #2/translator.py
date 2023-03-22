import sys

from isa import Opcode, write_code, args_count

command2opcode = {
    "CMP": Opcode.CMP,
    "JB": Opcode.JB,
    "MOV": Opcode.MOV,
    "ADD": Opcode.ADD,
    "MOD": Opcode.MOD,
    "JNZ": Opcode.JNZ,
    "JMP": Opcode.JMP,
    "PRINT": Opcode.PRINT,
    "HALT": Opcode.HALT,
    "PRINT_CH#": Opcode.PRINT_CH_INC,
    "JE": Opcode.JE,
    "LD*": Opcode.LD_POINT,
    "SV": Opcode.SV,
    "PRINT_CH": Opcode.PRINT_CH,
    "READ": Opcode.READ,
    "INTER_END": Opcode.INTER_END,
    "INTER_START": Opcode.INTER_START
}

commands = {
    "CMP",
    "JB",
    "MOV",
    "ADD",
    "MOD",
    "JNZ",
    "JMP",
    "PRINT",
    "HALT",
    "JE",
    "SV",
    "PRINT_CH#",
    "LD*",
    "PRINT_CH",
    "READ",
    "INTER_END",
    "INTER_START"
}

sections = {
    "data",
    "text"
}


def translate(text):
    current_section = 0
    last_section = -1
    sections_used = 0

    labels = {}
    undeclared_labels = {}
    variables = {}
    code = []

    instructions_address = 0

    data = []

    for line in text.split("\n"):
        line_splitted = line.strip().split(maxsplit=2)

        if len(line_splitted) == 0:
            continue

        if line_splitted[0] == "section":
            assert len(line_splitted) == 2, "Wrong section usage!"
            assert line_splitted[1][1:-1] in sections, "Wrong section! Use only data and text sections!"
            current_section = line_splitted[1][1:-1]
            sections_used += 1
            assert sections_used < 3, "Use only section data and section text!"
            continue

        assert current_section != -1, "Section is not set!"

        if line_splitted[-1][-1] == ":":
            assert len(line_splitted) == 1, "Wrong label usage!"
            assert (line_splitted[-1][0]) == ".", "All labels must start with '.' symbol"
            assert current_section != "data", "Wrong label usage! You cant use label in data section"
            name_of_label = line_splitted[-1][:-1]
            assert (name_of_label not in labels), "You cant redeclare label"

            labels[name_of_label] = instructions_address

            if name_of_label in undeclared_labels:
                for opcode in code:
                    if opcode.get('address') == undeclared_labels.get(name_of_label):
                        opcode['args'] = [instructions_address]
                undeclared_labels.pop(name_of_label)

            continue

        if current_section == "data":
            assert last_section in (-1, "data"), "Data section must declare before the text section!"
            last_section = "data"
            var_name = line_splitted[0]
            assert var_name not in variables, "You can't re-declare the same variable!"
            if var_name[0] == '(' and var_name[-1] == ')':
                line_for_string = line.strip().split(maxsplit=1)
                for symbol in line_for_string[1]:
                    data.append(ord(symbol))
                data.append(ord('\0'))
                data.append(len(data) - len(line_for_string[1]) - 1)
                variables[var_name] = len(data) - 1
                continue
            else:
                assert len(line_splitted) == 2, "Incorrect declaration of variables!"
                data.append(line_splitted[-1])

            variables[var_name] = len(data) - 1
            continue

        if current_section == "text":
            last_section = "text"
            current_command = line_splitted[0].upper()
            assert current_command in commands, "No such command!"
            args = []

            if args_count[current_command] == 1:

                assert len(line_splitted) == 2, "This command takes only one argument!"
                arg = line_splitted[1]

                if arg in labels:
                    args.append(labels.get(arg))

                    code.append(
                        {'address': instructions_address,
                         'opcode': command2opcode[current_command],
                         'args': args})

                    instructions_address += 1
                    continue

                elif arg[0] == '.':
                    undeclared_labels[arg] = instructions_address
                    code.append(
                        {'address': instructions_address,
                         'opcode': command2opcode[current_command],
                         'args': None})
                    instructions_address += 1
                    continue
                else:
                    if arg in variables:
                        args.append(variables.get(arg))
                        code.append(
                            {'address': instructions_address,
                             'opcode': command2opcode[current_command],
                             'args': args})
                        instructions_address += 1
                        continue
                    else:
                        assert arg.isdigit(), "This variable is not declared"

                        data.append(arg)
                        args.append(len(data) - 1)

                        code.append({'address': instructions_address,
                                     'opcode': command2opcode[current_command],
                                     'args': args})

                        instructions_address += 1
                        continue

            elif args_count[current_command] == 2:
                assert len(line_splitted) == 3, "This command takes two arguments!"
                arg1 = line_splitted[1].strip()
                arg2 = line_splitted[2].strip()

                if arg1 in variables:
                    args.append(variables.get(arg1))
                else:
                    assert arg1.isdigit(), "This variable is not declared"
                    data.append(arg1)
                    args.append(len(data) - 1)

                if arg2 in variables:
                    args.append(variables.get(arg2))
                else:
                    assert arg2.isdigit(), "This variable is not declared"
                    data.append(arg2)
                    args.append(len(data) - 1)

                code.append(
                    {'address': instructions_address,
                     'opcode': command2opcode[current_command],
                     'args': args})
                instructions_address += 1
                continue
            else:
                if current_command == Opcode.PRINT_CH:
                    args.append(0)
                    code.append(
                        {'address': instructions_address,
                         'opcode': command2opcode[current_command],
                         'args': args})

                    instructions_address += 1
                    continue

                if current_command == Opcode.READ:
                    args.append(0)
                    code.append(
                        {'address': instructions_address,
                         'opcode': command2opcode[current_command],
                         'args': args})

                    instructions_address += 1
                    continue

                code.append(
                    {'address': instructions_address,
                     'opcode': command2opcode[current_command],
                     'args': args})
                instructions_address += 1
                continue
    return code, data


def main(args):
    assert len(args) == 3, "Wrong arguments: translator.py <input_file> <target_file> <data_file>"
    source, target, data_file = args

    with open(source, "rt", encoding="utf-8") as f:
        source = f.read()

    code, data = translate(source)
    print("source LoC:", len(source.split()), "code instr:", len(code))
    write_code(target, code, data_file, data)


if __name__ == '__main__':
    main(sys.argv[1:])
