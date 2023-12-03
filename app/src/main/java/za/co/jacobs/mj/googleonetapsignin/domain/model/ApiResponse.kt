package za.co.jacobs.mj.googleonetapsignin.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ApiResponse(
    val isSuccess: Boolean,
    val user: User? = null,
    val message: String = "",
    @Transient
    val error: Exception? = null
)
