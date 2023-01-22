package com.codingwithjadrey.mynotes.di

import com.codingwithjadrey.mynotes.data.firebase.FirebaseData
import com.codingwithjadrey.mynotes.repository.NoteRepository
import com.codingwithjadrey.mynotes.repository.NoteRepositoryImp
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideNoteRepository(firestore: FirebaseFirestore): NoteRepository {
        return NoteRepositoryImp(firestore)
    }
}