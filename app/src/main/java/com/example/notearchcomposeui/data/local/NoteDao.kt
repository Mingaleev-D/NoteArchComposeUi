package com.example.notearchcomposeui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notearchcomposeui.data.local.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Dao
interface NoteDao {

   @Query("SELECT * FROM Note ORDER BY createdDate")
   fun getAllNotes(): Flow<List<Note>>

   @Query("SELECT * FROM Note WHERE id=:id ORDER BY createdDate")
   fun getNoteById(id: Long): Flow<Note>

   @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
   suspend fun insertNote(note: Note)

   @Update(onConflict = androidx.room.OnConflictStrategy.REPLACE)
   suspend fun updateNote(note: Note)

   @Query("DELETE FROM Note WHERE id=:id")
   suspend fun delete(id: Long)

   @Query("SELECT * FROM Note WHERE isBookMarked=1 ORDER BY createdDate DESC")
   fun getBookMarkedNotes(): Flow<List<Note>>
}