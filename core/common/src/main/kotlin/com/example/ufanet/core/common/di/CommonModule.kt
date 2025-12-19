package com.example.ufanet.core.common.di

import android.content.Context
import com.example.ufanet.core.common.util.deeplink.DeepLink
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CommonModule {
    @Singleton
    @Provides
    fun provideDeepLink(
        @ApplicationContext context: Context,
    ): DeepLink {
        return DeepLink(context)
    }
}