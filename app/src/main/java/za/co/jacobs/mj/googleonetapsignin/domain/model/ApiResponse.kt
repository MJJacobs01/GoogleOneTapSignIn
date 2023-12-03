package za.co.jacobs.mj.googleonetapsignin.domain.model

import kotlinx.serialization.Serializable
import za.co.jacobs.mj.googleonetapsignin.domain.model.*

@Serializable
data class ApiResponse(
    val isSuccess:Boolean,
    val user: User? = null,
    val message:String = ""
)
