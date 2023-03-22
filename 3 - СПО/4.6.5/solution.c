#include <sys/shm.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv) {
    key_t k_key;
    int shmid1 = shmget(atoll(argv[1]), 1000, 0600);
    int shmid2 = shmget(atoll(argv[2]), 1000, 0600);
    int shmid3 = shmget(k_key, 1000, IPC_CREAT | 0666);
    int *shared1 = shmat(shmid1, NULL, 0);
    int *shared2 = shmat(shmid2, NULL, 0);
    int *shared3 = shmat(shmid3, (void *) 0, 0);

    int k;
    for (k = 0; k < 100; ++k) {
        shared3[k] = shared1[k] + shared2[k];
    }

    printf("%i\n", k_key);

    shmdt(shared1);
    shmdt(shared2);
    shmdt(shared3);
    return 0;
}