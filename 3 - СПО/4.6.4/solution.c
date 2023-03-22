#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <syslog.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>

void sig_solver_function(int sig)
{
    if (sig == SIGURG) {
        exit(EXIT_SUCCESS);
    }
}

void daemon_start()
{
    pid_t pid = fork();
    if (pid > 0) {
        printf("%d\n", pid);
        exit(EXIT_SUCCESS);
    }
    if (pid < 0) {
        exit(EXIT_FAILURE);
    }
    umask(0);
    pid_t sid=setsid();
    if (sid < 0) {
        exit(EXIT_FAILURE);
    }
    if (chdir("/") < 0) {
        exit(EXIT_FAILURE);
    }
    close(STDIN_FILENO);
    close(STDOUT_FILENO);
    close(STDERR_FILENO);
    signal(SIGURG, sig_solver_function);
}

int main()
{
    daemon_start();
    openlog("solution", LOG_PID, LOG_DAEMON);
    syslog(LOG_INFO, "starting");
    while (1) {
        sleep(1);
    }
    closelog();
    return 0;
}
