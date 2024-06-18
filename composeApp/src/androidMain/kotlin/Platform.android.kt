import android.os.Build
import platform.PlatformCmd
import platform.PlatformDataStore

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun getPlatformCmd(): PlatformCmd {
    return object : PlatformCmd {
        override fun runCmd(cmd: String): String {
            return "Not Support"
        }

        override fun closeJdkExe() {

        }
    }
}

actual suspend inline fun <reified T> dataStorePutData(key: String, value: T) {
}

actual suspend inline fun <reified T> dataStoreGetData(key: String, def: T): T {
    return def
}