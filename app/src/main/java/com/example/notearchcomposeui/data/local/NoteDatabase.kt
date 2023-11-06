package com.example.notearchcomposeui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notearchcomposeui.data.local.model.Note

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@TypeConverters(NoteDateConverter::class)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

   abstract val noteDao: NoteDao
}