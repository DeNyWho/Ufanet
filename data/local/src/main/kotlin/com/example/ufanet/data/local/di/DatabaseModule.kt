package com.example.ufanet.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.ufanet.data.local.UfanetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Singleton
    @Provides
    fun provideUfanetDatabase(
        @ApplicationContext context: Context,
    ): UfanetDatabase = Room.databaseBuilder(
        context,
        UfanetDatabase::class.java,
        "ufanet-database",
    ).build()
}