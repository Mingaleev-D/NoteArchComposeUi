package com.example.notearchcomposeui.di

import com.example.notearchcomposeui.data.repository.NoteRepositoryImpl
import com.example.notearchcomposeui.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

   @Binds
   @Singleton
   abstract fun bindRepository(repositoryImpl: NoteRepositoryImpl): NoteRepository
}