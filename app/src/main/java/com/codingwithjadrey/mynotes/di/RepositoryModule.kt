package com.codingwithjadrey.mynotes.di

import com.codingwithjadrey.mynotes.repository.note.NoteRepository
import com.codingwithjadrey.mynotes.repository.note.NoteRepositoryImp
import com.codingwithjadrey.mynotes.repository.quote.QuoteRepository
import com.codingwithjadrey.mynotes.repository.quote.QuoteRepositoryImp
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

    @Provides
    @Singleton
    fun provideQuoteRepository(firestore: FirebaseFirestore): QuoteRepository {
        return QuoteRepositoryImp(firestore)
    }
}