package com.example.notearchcomposeui.data.repository

import com.example.notearchcomposeui.data.local.NoteDao
import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

   override fun getAllNotes(): Flow<List<Note>> {
      return noteDao.getAllNotes()
   }

   override fun getNoteById(id: Long): Flow<Note> {
      return noteDao.getNoteById(id)
   }

   override suspend fun insertNote(note: Note) {
      noteDao.insertNote(note)
   }

   override suspend fun updateNote(note: Note) {
      noteDao.updateNote(note)
   }

   override suspend fun delete(id: Long) {
      noteDao.delete(id)
   }

   override fun getBookMarkedNotes(): Flow<List<Note>> {
      return noteDao.getBookMarkedNotes()
   }
}