package com.example.notearchcomposeui.domain.usecase

import com.example.notearchcomposeui.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

class GEtNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
   operator fun invoke(id: Long) = repository.getNoteById(id)
}