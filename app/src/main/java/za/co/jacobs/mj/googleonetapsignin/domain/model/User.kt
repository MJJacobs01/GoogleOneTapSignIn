package za.co.jacobs.mj.googleonetapsignin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val profilePhoto: String
)
