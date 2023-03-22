#include <stdlib.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#define _GNU_SOURCE

int main() {

	pid_t pid = getpid();
	char path[128];
	char buff[128];

	if (snprintf(path, sizeof path, "/proc/%d/stat", pid)==-1) return 1;
	FILE* fd = fopen(path, "r");
	if (fd == NULL) {
		printf("fopen error");
		return 1;
	}	
	int ppid = 0;
	fscanf(fd, "%*d %*s %*c %d", &ppid);
	fclose(fd);
	printf("%d\n", ppid);
	return 0;
}
