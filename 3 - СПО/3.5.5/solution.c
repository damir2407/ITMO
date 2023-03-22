#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    printf("%d", getpid());
    daemon(0, 0);
    pause();
    return 0;
}
