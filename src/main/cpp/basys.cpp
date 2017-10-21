#include <jni.h>

#include <sys/types.h>
#include <unistd.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     net_sileader_cyborg_basys_Basys
 * Method:    setUid
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_net_sileader_cyborg_basys_Basys_setUid(JNIEnv *, jclass, jint uid) {
    ::setuid(uid);
}

/*
 * Class:     net_sileader_cyborg_basys_Basys
 * Method:    getUid
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_net_sileader_cyborg_basys_Basys_getUid(JNIEnv *, jclass) {
    return static_cast<jint>(::getuid());
}

#ifdef __cplusplus
}
#endif