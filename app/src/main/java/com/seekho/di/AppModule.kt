package com.seekho.di

import com.seekho.repository.AnimeDataRepository
import com.seekho.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Ritik on: 10/05/25
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserAccountDataRepository(): AnimeDataRepository = AnimeRepository()
}

