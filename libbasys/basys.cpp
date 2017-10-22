#include <sys/types.h>
#include <unistd.h>

extern "C" {
    void setUid(int uid) {
        setuid(uid);
    }
    int getUid() {
        return getuid();
    }
    int forkProcess() {
        return fork();
    }
    int changeRootDirectory(const char *path) {
        return chroot(path)==0;
    }
    int waitProcess() {
        int status;
        return wait(&status);
    }
    int waitPidProcess(int pid) {
        int status;
        return waitpid(pid, &status, 0);
    }
}
