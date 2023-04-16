package com.example.proto_data_store_sample.di

import android.content.Context
import com.example.proto_data_store_sample.data.UserRepositoryImpl
import com.example.proto_data_store_sample.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {
    @Provides
    fun provideUserRepository(@ApplicationContext context: Context): UserRepository {
        return UserRepositoryImpl(context)
    }
}

