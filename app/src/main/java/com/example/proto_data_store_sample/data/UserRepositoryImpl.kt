package com.example.proto_data_store_sample.data

import android.content.Context
import com.example.datastore.UserDataStoreManager
import com.example.proto_data_store_sample.datastore.UserPreferences
import com.example.proto_data_store_sample.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    context: Context,
) : UserRepository {
    private val userDataStoreManager = UserDataStoreManager(context)

    override suspend fun getUser(): Flow<UserPreferences> {
        return userDataStoreManager.getUser()
    }

    override suspend fun saveUser(completed: Boolean) {
        userDataStoreManager.saveUser(completed)
    }
}