package com.example.notearchcomposeui.di

import android.content.Context
import androidx.room.Room
import com.example.notearchcomposeui.data.local.NoteDao
import com.example.notearchcomposeui.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   fun provideNoteDao(database: NoteDatabase): NoteDao {
      return database.noteDao
   }

   @Provides
   @Singleton
   fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
      return Room.databaseBuilder(
          context,
          NoteDatabase::class.java,
          "note_database"
      ).build()
   }


}




