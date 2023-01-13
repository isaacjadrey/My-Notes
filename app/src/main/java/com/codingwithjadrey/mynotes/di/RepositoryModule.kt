package com.codingwithjadrey.mynotes.di

import com.codingwithjadrey.mynotes.data.firebase.FirebaseData
import com.codingwithjadrey.mynotes.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(firebaseData: FirebaseData): NoteRepository {
        return NoteRepository(firebaseData)
    }
}