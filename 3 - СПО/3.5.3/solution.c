#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#define _GNU_SOURCE

int main(int argc, char** argv) {
	if (argc > 2) return 0;
	if (argc < 2) return 0;
	pid_t pid = atoi(argv[1]);
	printf("%d\n", pid);
        char path[128];
        int init = 0;

	do {
		if (snprintf(path, sizeof path, "/proc/%d/stat", pid)==-1) return 1;
		FILE* fd = fopen(path, "r");

		if (!fd) {
			printf("error fopen");
			return 1;
		}

		fscanf(fd, "%*d %*s %*c %d", &init);
		fclose(fd);
		printf("%d\n", init);
		pid = init;
	} while (pid != 1);


	return 0;

}
