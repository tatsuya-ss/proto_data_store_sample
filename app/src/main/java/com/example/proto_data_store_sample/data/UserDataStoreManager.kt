package com.example.proto_data_store_sample.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.proto_data_store_sample.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserDataStoreManager(
    context: Context
) {
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer,
    )
    private val dataStore = context.userPreferencesStore

    suspend fun getUser(): Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("proto", "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun saveUser(completed: Boolean) {
        dataStore.updateData { userPreferences ->
            userPreferences.toBuilder().setShowCompleted(completed).build()
        }
    }

    companion object {
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
    }

}