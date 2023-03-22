#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>

int main(int argc, char** argv){
    if (argc > 2)
        return 1;
    if (argc < 2)
        return 1;
    struct hostent *host;
    if ((host = gethostbyname(argv[1]))==NULL){
        printf("Smth wrong!");
        return 1;
    }
    int k = 0;
    while (host->h_addr_list[k]){
        struct in_addr *addr = (struct in_addr*) host->h_addr_list[k];
        printf("%s\n", inet_ntoa(*addr));
        k++;
    }
    return 0;
}
