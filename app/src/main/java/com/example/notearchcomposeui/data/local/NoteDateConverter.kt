package com.example.notearchcomposeui.data.local

import androidx.room.TypeConverter
import java.util.Date

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

class NoteDateConverter {

   @TypeConverter
   fun toDate(date: Long?): Date? {
      return date?.let { Date(it) }
   }

   @TypeConverter
   fun fromDate(date: Date?): Long? {
      return date?.time
   }
}