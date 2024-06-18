import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.browser.localStorage
import kotlinx.serialization.serializer
import org.w3c.dom.Storage
import org.w3c.dom.get
import org.w3c.dom.set
import platform.PlatformCmd

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
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
    when(value) {
        is String, is Int, is Float, is Double, is Long, is Boolean -> localStorage[key] = value.toString()
        else -> {
            val serializer = serializer<T>()
            localStorage[key] = DefaultJson.encodeToString(serializer, value)
        }
    }
}

actual suspend inline fun <reified T> dataStoreGetData(key: String, def: T): T {
    return when(def) {
        is String, is Int, is Float, is Double, is Long, is Boolean -> localStorage[key] as? T ?: "" as T
        else -> {
            val str = localStorage[key] ?: ""
            DefaultJson.decodeFromString(str) ?: def
        }
    }
}