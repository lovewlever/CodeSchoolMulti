package viewmodel.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
class LabelData(
    @SerialName("addTime")
    val addTime: String? = null,
    @SerialName("classificationName")
    val classificationName: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("refTcbLabelEntities")
    val refTcbLabelEntities: List<RefTcbLabelEntity>? = null,
)

@Serializable
class RefTcbLabelEntity(
    @SerialName("addTime")
    val addTime: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("labelName")
    val labelName: String? = null
)