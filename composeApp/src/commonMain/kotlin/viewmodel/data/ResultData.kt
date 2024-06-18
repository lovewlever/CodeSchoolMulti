package viewmodel.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultData<T>(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: T? = null,
    @SerialName("msg")
    val msg: String? = null
)
