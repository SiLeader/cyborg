package net.sileader.cyborg.serivce

import net.sileader.cyborg.core.Service

class PermissionService : Service() {
    companion object {
        const val INTERNET_ACCESS="internet_access"
        const val FORK_PROCESS="fork_process"
        const val RUN_AS_ROOT="run_as_root"
        const val INSTALL_SOFTWARE="install_software"
        const val WRITE_TO_STORAGE="write_to_storage"
        const val READ_FROM_STORAGE="read_from_storage"
        const val IPC="ipc"
        const val INTER_PROCESS_COMMUNICATION= IPC
    }
}
