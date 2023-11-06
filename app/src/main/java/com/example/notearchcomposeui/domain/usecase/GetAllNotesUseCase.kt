package com.example.notearchcomposeui.domain.usecase

import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

   operator fun invoke(): Flow<List<Note>> {
      return repository.getAllNotes()
   }
}