#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/select.h>
#include <stdio.h>

int main(int argc, char **argv) {
    int first_fd = open("in1", O_RDONLY);
    int second_fd = open("in2", O_RDONLY);

    fd_set ffd;
    char ch_buff[10];
    ssize_t len;
    size_t k = 0;
    while (1) {
        FD_ZERO(&ffd);
        FD_SET(first_fd, &ffd);
        FD_SET(second_fd, &ffd);
        select(second_fd + 1, &ffd, NULL, NULL, NULL);
        if (!FD_ISSET(first_fd, &ffd)) {
            len = read(second_fd, ch_buff, 9);
        } else {
            len = read(first_fd, ch_buff, 9);
        }

        if (len <= 0) {
            break;
        } else {
            ch_buff[len] = '\0';
            k += atoi(ch_buff);
        }
    }

    close(second_fd);
    close(first_fd);
    printf("%ld\n", k);

    return 0;
}