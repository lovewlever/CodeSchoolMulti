import androidx.compose.ui.util.fastMapIndexed
import platform.PlatformCmd

class WindowsPlatform: PlatformCmd {
    override fun runCmd(cmd: String): String {
        val process = Runtime.getRuntime().exec(cmd)
        val result = process.inputStream.bufferedReader().use { it.readText() }
        process.waitFor()
        return result
    }

    override fun closeJdkExe() {
        val currentPid = ProcessHandle.current().pid()
        val tasklist = Runtime.getRuntime().exec("tasklist")
        val tasklist2 = tasklist.inputStream.bufferedReader()
            .use { it.readText() }
            .split("\n")
            .filter { it.startsWith("java.exe") }
        tasklist2.forEach {
            val spArr = it.split(" ").toMutableList()
            spArr[0] = ""
            val pid = spArr.find { it.isNotBlank() }
            println("currentPid: ${currentPid};; Pid: ${pid}")
            if (pid.toString() != currentPid.toString()) {
                Runtime.getRuntime().exec("taskkill /F /PID $pid")
            }
        }
    }
}