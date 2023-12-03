package za.co.jacobs.mj.googleonetapsignin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val tokenId: String
)
