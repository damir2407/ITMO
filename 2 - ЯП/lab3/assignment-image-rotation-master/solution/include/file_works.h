#include <stdio.h>
#include <stddef.h>

enum open_status {
	OPEN_OK = 0,
	OPEN_ERROR
};

enum close_status {
	CLOSE_OK = 0,
	CLOSE_ERROR
};



enum open_status read_open(char* const path, FILE **file);

enum open_status write_open(char* const path, FILE **file);

enum close_status file_close(FILE *file);
