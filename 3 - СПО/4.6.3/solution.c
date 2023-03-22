#include <stdio.h>
#include <stdlib.h>
#include <signal.h>


int s1,s2 = 0;

void sig_solve_func(int signal) {
    switch (signal) {
        case SIGUSR1:
            s1++;
            break;
        case SIGUSR2:
            s2++;
            break;
        case SIGTERM:
            printf("%d %d\n", s1, s2);
            exit(0);
    }
}

int main() {
    signal(SIGUSR1, sig_solve_func);
    signal(SIGUSR2, sig_solve_func);
    signal(SIGTERM, sig_solve_func);
    while (1) {
    }
    return 0;
}
