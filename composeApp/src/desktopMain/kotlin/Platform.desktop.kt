import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.russhwolf.settings.PreferencesSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import platform.PlatformCmd

actual fun getPlatformCmd(): PlatformCmd {
    return WindowsPlatform()
}

val dataStore: DataStore<Preferences> by lazy {
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { "adb_help.preferences_pb".toPath() }
    )
}

val settings by lazy {
    PreferencesSettings.Factory().create("adb_help.preferences")
}

actual suspend inline fun <reified T> dataStorePutData(key: String, value: T) {
    /*when(value) {
        is Int -> dataStore.edit { it[intPreferencesKey(key)] = value }
        is Boolean -> dataStore.edit { it[booleanPreferencesKey(key)] = value }
        is Float -> dataStore.edit { it[floatPreferencesKey(key)] = value }
        is Long -> dataStore.edit { it[longPreferencesKey(key)] = value }
        is String -> dataStore.edit { it[stringPreferencesKey(key)] = value }
        is Double -> dataStore.edit { it[doublePreferencesKey(key)] = value }
        is ByteArray -> dataStore.edit { it[byteArrayPreferencesKey(key)] = value }
        else -> dataStore.edit { it[stringPreferencesKey(key)] = Gson().toJson(value) }
    }*/
    when(value) {
        is Int -> settings.putInt(key, value)
        is Boolean -> settings.putBoolean(key, value)
        is Float -> settings.putFloat(key, value)
        is Long -> settings.putLong(key, value)
        is String -> settings.putString(key, value)
        is Double -> settings.putDouble(key, value)
        else ->settings.putString(key, Gson().toJson(value))
    }
}

actual suspend inline fun <reified T> dataStoreGetData(key: String, def: T): T {
    println("Windows - dataStoreGetData: ${key}, ${def}")
    /*return when(def) {
        is Int -> dataStore.data.map { it[intPreferencesKey(key)] }.first() as? T ?: def
        is Boolean -> dataStore.data.map { it[booleanPreferencesKey(key)] }.first() as? T ?: def
        is Float -> dataStore.data.map { it[floatPreferencesKey(key)] }.first() as? T ?: def
        is Long -> dataStore.data.map { it[longPreferencesKey(key)] }.first() as? T ?: def
        is String -> dataStore.data.map { it[stringPreferencesKey(key)] }.first() as? T ?: def
        is Double -> dataStore.data.map { it[doublePreferencesKey(key)] }.first() as? T ?: def
        is ByteArray -> dataStore.data.map { it[byteArrayPreferencesKey(key)] }.first() as? T ?: def
        else ->  {
            println("Windows - dataStoreGetData: ${key}, ${def}, 未知类型 转JSON")
            val str = dataStore.data.map { it[stringPreferencesKey(key)] }.first() ?: ""
            try {
                Gson().fromJson(str, object : TypeToken<T>(){}.type) ?: def
            } catch (e: Exception) {
                def
            }
        }
    }*/
    return when(def) {
        is Int -> settings.getInt(key, def) as T
        is Boolean -> settings.getBoolean(key, def) as T
        is Float -> settings.getFloat(key, def) as T
        is Long -> settings.getLong(key, def) as T
        is String -> settings.getString(key, def) as T
        is Double -> settings.getDouble(key, def) as T
        else ->  {
            println("Windows - dataStoreGetData: ${key}, ${def}, 未知类型 转JSON")
            val str = settings.getString(key, "")
            try {
                Gson().fromJson(str, object : TypeToken<T>(){}.type) ?: def
            } catch (e: Exception) {
                def
            }
        }
    }
}