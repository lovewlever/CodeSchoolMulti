import platform.PlatformCmd

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPlatformCmd(): PlatformCmd

expect suspend inline fun <reified T> dataStorePutData(key: String, value: T)

expect suspend inline fun <reified T> dataStoreGetData(key: String, def: T): T