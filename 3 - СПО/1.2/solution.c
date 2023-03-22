#include <string.h>
#include <stdio.h>
#include <stddef.h>

int stringStat(const char *string, size_t multiplier, int *count);

int stringStat(const char *string, size_t multiplier, int *count) {
	(*count)++;
	return strlen(string) * multiplier;
}