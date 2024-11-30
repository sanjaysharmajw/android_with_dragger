package com.rv.tasktechstalwarts.common.di

import com.rv.tasktechstalwarts.feature_dashboard.data.datasource.GetRecipesDataSourceImpl
import com.rv.tasktechstalwarts.feature_dashboard.data.repository.GetRecipesRepositoryImpl
import com.rv.tasktechstalwarts.feature_dashboard.domain.datasource.GetRecipesDataSource
import com.rv.tasktechstalwarts.feature_dashboard.domain.repository.GetRecipesRepository
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Provides
    fun getRecipesRepository(impl: GetRecipesRepositoryImpl): GetRecipesRepository = impl

    @Provides
    fun getRecipesDataSource(impl: GetRecipesDataSourceImpl): GetRecipesDataSource = impl

    @Provides
    fun providesRecipesUseCase(getRecipesRepository: GetRecipesRepository): GetRecipesUseCase =
        GetRecipesUseCase(getRecipesRepository)

}