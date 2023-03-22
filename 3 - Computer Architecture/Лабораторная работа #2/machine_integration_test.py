import unittest

import machine
import translator


def start(source_code, output_file, data_file, input_file):
    translator.main([source_code, output_file, data_file])
    if input_file == "":
        return machine.main([output_file, data_file])
    return machine.main([output_file, data_file, input_file])


class TestMachine(unittest.TestCase):

    def test_hello_world(self):
        output = start("examples/hello_world", "examples/hello_world_opcodes", "examples/data_file", "")
        self.assertEqual(output, 'Hello world!')
        assert output[0] == 'H' and output[1] == 'e' and output[2] == 'l' \
               and output[3] == 'l' and output[4] == 'o' and output[5] == ' ' \
               and output[6] == 'w' and output[7] == 'o' and output[8] == 'r' \
               and output[9] == 'l' and output[10] == 'd' and output[11] == '!'

    def test_cat(self):
        output = start("examples/cat", "examples/cat_opcodes", "examples/data_file", "examples/hello_text")
        assert output[0] == 'h' and output[1] == 'e' and output[2] == 'l' \
               and output[3] == 'l' and output[4] == 'o'

    def test_prob2(self):
        output = start("examples/prob2", "examples/prob2_opcodes", "examples/data_file", "")
        assert output == '4613732'

    def test_first(self):
        output = start("examples/test1", "examples/test1_opcodes", "examples/data_file", "")
        assert output == '99'

    def test_second(self):
        output = start("examples/test2", "examples/test2_opcodes", "examples/data_file", "")
        assert output == '-1'
