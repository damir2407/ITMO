#include <stdio.h>

int main(__attribute__((unused)) int argc, char **argv) {
    char ch_buff[BUFSIZ];
    int k = 0;
    sprintf(ch_buff, "%s %s", argv[1], argv[2]);
    FILE *fd = popen(ch_buff, "r");
    if (fd != NULL) {
        while ((*ch_buff = fgetc(fd)) != EOF) {
            k = k + (*ch_buff == '0');
        }
        pclose(fd);
    }

    printf("%d\n", k);
    return 0;
}

