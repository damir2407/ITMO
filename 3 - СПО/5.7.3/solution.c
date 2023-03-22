#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netdb.h>
#include <unistd.h>

struct sockaddr_in addr;

int compare_func(const void *first_arg, const void *second_arg) {
    return *((char *) second_arg) - *((char *) first_arg);
}


int main(int argc, char **argv) {
    if (argc != 2) return 1;

    int sock_fd;
    if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) > -1) {
        int a;
        setsockopt(sock_fd, SOL_SOCKET, SO_REUSEADDR, &a, sizeof(a));
        addr.sin_port = htons(atoi(argv[1]));
        addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
        addr.sin_family = AF_INET;
        int result1 = bind(sock_fd, (struct sockaddr *) &addr, sizeof(addr));
        int result2 = listen(sock_fd, 1);
        if (!result1 && !result2) {
            int accepted = accept(sock_fd, NULL, 0);
            if (accepted > -1) {
                char ch_buf[2048];
                ssize_t capacity;
                while (((capacity = recv(accepted, ch_buf, sizeof(ch_buf), 0)) > 0) && strncmp(ch_buf, "OFF", 3)) {
                    qsort(ch_buf, capacity - 1, sizeof(char), compare_func);
                    send(accepted, ch_buf, capacity, 0);
                }
                close(accepted);
            }
        }
        close(sock_fd);
    }
    return 0;
}