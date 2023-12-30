package za.co.jacobs.mj.googleonetapsignin.data.repository

import androidx.datastore.core.*
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import za.co.jacobs.mj.googleonetapsignin.util.*
import java.io.IOException
import javax.inject.Inject

/**
 * Created by MJ Jacobs on 2023/11/18 at 14:43
 */

class DataStoreOperationsImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {

    private object PreferencesKey {
        val signedInKey = booleanPreferencesKey(name = Constants.preferencesSignedInKey)
    }

    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.signedInKey] = signedIn
        }
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStore.data.catch {exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val signedInState = preferences[PreferencesKey.signedInKey] ?: false
            signedInState
        }
    }
}