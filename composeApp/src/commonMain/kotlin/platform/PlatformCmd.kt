package platform

interface PlatformCmd {

    fun runCmd(cmd: String): String

    fun closeJdkExe()
}