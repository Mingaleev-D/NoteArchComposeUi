package com.example.notearchcomposeui.domain.usecase

import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

class AddUseCase @Inject constructor(
    private val repository: NoteRepository
) {

   suspend operator fun invoke(note: Note) = repository.insertNote(note)
}