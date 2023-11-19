package za.co.jacobs.mj.googleonetapsignin.di

import android.content.Context
import androidx.datastore.core.*
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import za.co.jacobs.mj.googleonetapsignin.data.repository.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import za.co.jacobs.mj.googleonetapsignin.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatastorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(Constants.preferencesName) }
        )
    }

    @Provides
    @Singleton
    fun provideDatastoreOperations(dataStore: DataStore<Preferences>): DataStoreOperations {
        return DataStoreOperationsImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dataStoreOperations: DataStoreOperations
    ): Repository {
        return RepositoryImpl(dataStoreOperations = dataStoreOperations)
    }
}