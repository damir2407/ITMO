import unittest

import isa
import translator


def start(input_file, output_file, correct_file, data_file):
    translator.main([input_file, output_file, data_file])
    result = isa.read_code(output_file, data_file)
    correct_result = isa.read_code(correct_file, data_file)
    assert result == correct_result


class TestTranslator(unittest.TestCase):

    def test_prob2(self):
        start("examples/prob2", "examples/prob2_opcodes",
              "examples/correct_prob2_opcodes", "examples/data_file")

    def test_hello_world(self):
        start("examples/hello_world", "examples/hello_world_opcodes",
              "examples/correct_hello_world_opcodes", "examples/data_file")

    def test_cat(self):
        start("examples/cat", "examples/cat_opcodes",
              "examples/correct_cat_opcodes", "examples/data_file")
