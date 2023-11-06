package com.example.notearchcomposeui.domain.repository

import com.example.notearchcomposeui.data.local.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

interface NoteRepository {

   fun getAllNotes(): Flow<List<Note>>
   fun getNoteById(id: Long): Flow<Note>
   suspend fun insertNote(note: Note)
   suspend fun updateNote(note: Note)
   suspend fun delete(id: Long)
   fun getBookMarkedNotes(): Flow<List<Note>>
}