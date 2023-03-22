#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <dirent.h>

int check_on_digit(const char *s)
{
    while (*s!=NULL) {
        if (isdigit(*s++) == 0) return 0;
    }
    return 1;
}

int main() {
	char path[128];
	struct dirent *en;
	int k = 0;
	DIR *directory = opendir("/proc");
	while ((en = readdir(directory))!= NULL) {

		if (check_on_digit(en->d_name) && en->d_type == DT_DIR ) {
			char ch_buff[128];
			if (0 > snprintf(path, sizeof path, "/proc/%d/comm", atoi(en->d_name))) {
				return 1;
			}
			FILE* fd = fopen(path, "r");

            		if (fd==NULL) {
                		printf("fopen failed");
                		return 1;
            		}

			fscanf(fd, "%s", ch_buff);
			fclose(fd);
			if (strstr(ch_buff, "genenv") != NULL) k++;
		}
	}

	printf("%d\n", k);
	return 0;
}

