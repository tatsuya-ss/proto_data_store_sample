package com.example.proto_data_store_sample.domain

import com.example.proto_data_store_sample.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<UserPreferences>
    suspend fun saveUser(completed: Boolean)
}