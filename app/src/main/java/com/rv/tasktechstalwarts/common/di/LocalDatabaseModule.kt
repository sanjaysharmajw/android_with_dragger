package com.rv.tasktechstalwarts.common.di

import android.content.Context
import androidx.room.Room
import com.rv.tasktechstalwarts.common.MY_LOCAL_DATABASE
import com.rv.tasktechstalwarts.common.localdatabase.MyLocalDao
import com.rv.tasktechstalwarts.common.localdatabase.MyLocalDatabase
import com.rv.tasktechstalwarts.feature_local_storage.data.datasource.LocalStorageDataSourceImpl
import com.rv.tasktechstalwarts.feature_local_storage.data.repository.LocalStorageRepositoryImpl
import com.rv.tasktechstalwarts.feature_local_storage.domain.datasource.LocalStorageDataSource
import com.rv.tasktechstalwarts.feature_local_storage.domain.repository.LocalStorageRepository
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.InsertAddToCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModule {
    @Provides
    @Singleton
    fun providesAppLocalDatabase(@ApplicationContext appContext: Context): MyLocalDatabase =
        Room.databaseBuilder(
            appContext,
            MyLocalDatabase::class.java,
            MY_LOCAL_DATABASE
        ).build()

    @Provides
    fun providesRandomQuotesDao(myLocalDatabase: MyLocalDatabase): MyLocalDao =
        myLocalDatabase.tpScanDao()

    @Provides
    fun provideLocalStorageRepositoryImpl(repositoryImpl: LocalStorageRepositoryImpl): LocalStorageRepository =
        repositoryImpl

    @Provides
    fun provideLocalStorageDataSourceImpl(localStorageDataSourceImpl: LocalStorageDataSourceImpl): LocalStorageDataSource =
        localStorageDataSourceImpl

    @Provides
    fun provideInsertAddToCartUseCase(localStorageRepository: LocalStorageRepository): InsertAddToCartUseCase =
        InsertAddToCartUseCase(localStorageRepository)
}