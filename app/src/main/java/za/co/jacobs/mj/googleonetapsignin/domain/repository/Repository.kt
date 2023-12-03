package za.co.jacobs.mj.googleonetapsignin.domain.repository

import kotlinx.coroutines.flow.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*

/**
 * Created by MJ Jacobs on 2023/11/18 at 14:50
 */

interface Repository {
    suspend fun saveSignedInState(signedIn: Boolean)

    fun readSignedInState(): Flow<Boolean>

    suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse

    suspend fun getUserInfo(): ApiResponse

    suspend fun updateUser(userUpdate: UserUpdate): ApiResponse

    suspend fun deleteUser(): ApiResponse

    suspend fun clearSession(): ApiResponse
}