#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <dirent.h>


int check_on_digit(const char *arg) {
    while (*arg != NULL) {
        if (isdigit(*arg++) == 0) return 0;
    }
    return 1;
}

int task_fun(int arg) {
    char path[64];
    struct dirent *entry;
    if (snprintf(path, sizeof path, "/proc/%d/task", arg) == -1) 
        return 1;
    DIR *directory = opendir(path);

    int k = 0;
    while ((entry = readdir(directory))!=NULL) {
        if (check_on_digit(entry->d_name)) {
            k++;
        }
    }

    return k;
}

int parent_function(int arg) {
    char path[64];
    if (snprintf(path, sizeof path, "/proc/%d/task/%d/children", arg, arg)==-1) return 1;
    FILE *fd = fopen(path, "r");
    if (!fd) return 1;
    int pid2 = 0;
    int k = 0;
    while (fscanf(fd, "%d", &pid2) != EOF) {
        k += task_fun(pid2);
        k += parent_function(pid2);
    }
    return k;

}

int main(int argc, char **argv) {
    if (argc > 2) return 1;
    if (argc < 2) return 1;
    int pid = atoi(argv[1]);
    printf("%d\n", (task_fun(pid)+parent_function(pid)));
    return 0;
}

