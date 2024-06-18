package viewmodel.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WindowsIPData(
    @SerialName("id")
    var id: String = "",
    @SerialName("ip")
    var ip: String = "",
    @SerialName("name")
    var name: String = ""
)
