package za.co.jacobs.mj.googleonetapsignin.domain.repository

import kotlinx.coroutines.flow.*

/**
 * Created by MJ Jacobs on 2023/11/18 at 14:50
 */

interface Repository {
    suspend fun saveSignedInState(signedIn:Boolean)

    fun readSignedInState():Flow<Boolean>
}