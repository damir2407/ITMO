#include <stdio.h>
#include <arpa/inet.h>
#include <netinet/ip.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>

struct sockaddr_in sockaddrIn;

int main(int argc, char **argv) {
    int sock = socket(AF_INET, SOCK_DGRAM, 0);
    sockaddrIn.sin_family = AF_INET;
    sockaddrIn.sin_port = htons(atoi(argv[1]));
    inet_aton("127.0.0.1", &sockaddrIn.sin_addr);
    int check = bind(sock, (struct sockaddr *) &sockaddrIn, sizeof(sockaddrIn));
    while (!0) {
        char ch_buf[5000];
        read(sock, ch_buf, 5000);

        if (strcmp(ch_buf, "OFF\n")!=NULL) {
            printf("%s\n", ch_buf);
            int k;
            for (k = 0; k < 5000; k++) ch_buf[k] = 0;
        }else break;
    }
    return 0;
}
