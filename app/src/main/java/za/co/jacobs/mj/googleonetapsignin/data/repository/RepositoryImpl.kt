package za.co.jacobs.mj.googleonetapsignin.data.repository

import kotlinx.coroutines.flow.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import javax.inject.Inject

/**
 * Created by MJ Jacobs on 2023/11/18 at 14:51
 */

class RepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) : Repository {

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignedInState(signedIn = signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperations.readSignedInState()
    }
}