package za.co.jacobs.mj.googleonetapsignin.data.repository

import kotlinx.coroutines.flow.*
import za.co.jacobs.mj.googleonetapsignin.data.remote.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import javax.inject.Inject

/**
 * Created by MJ Jacobs on 2023/11/18 at 14:51
 */

class RepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val kTorApi: KTorApi
) : Repository {

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignedInState(signedIn = signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperations.readSignedInState()
    }

    override suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse {
        return try {
            kTorApi.verifyTokenOnBackend(request = request)
        } catch (e: Exception) {
            ApiResponse(isSuccess = false, error = e)
        }
    }

    override suspend fun getUserInfo(): ApiResponse {
        return try {
            kTorApi.getUserInfo()
        } catch (e: Exception) {
            ApiResponse(isSuccess = false, error = e)
        }
    }

    override suspend fun updateUser(userUpdate: UserUpdate): ApiResponse {
        return try {
            kTorApi.updateUser(userUpdate = userUpdate)
        } catch (e: Exception) {
            ApiResponse(isSuccess = false, error = e)
        }
    }

    override suspend fun deleteUser(): ApiResponse {
        return try {
            kTorApi.deleteUser()
        }catch (e:Exception){
            ApiResponse(isSuccess = false, error = e)
        }
    }

    override suspend fun clearSession(): ApiResponse {
        return try {
            kTorApi.clearSession()
        }catch (e:Exception){
            ApiResponse(isSuccess = false, error = e)
        }
    }
}