package com.rxs.moneymanager.di

import com.google.firebase.database.FirebaseDatabase
import com.rxs.moneymanager.data.repository.Firebase
import com.rxs.moneymanager.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository = Firebase()
}