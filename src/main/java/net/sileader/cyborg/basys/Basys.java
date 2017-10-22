package net.sileader.cyborg.basys;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Basys extends Library {
    Basys INSTANCE= Native.loadLibrary("basys", Basys.class);


    void setUid(int uid);
    int getUid();

    int changeRootDirectory(String path);

    int forkProcess();
    int waitProcess();
    int waitPidProcess(int pid);
}
