package com.example.notearchcomposeui.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.domain.usecase.DeleteNoteUseCase
import com.example.notearchcomposeui.domain.usecase.GetAllNotesUseCase
import com.example.notearchcomposeui.domain.usecase.UpdateNoteUseCase
import com.example.notearchcomposeui.ui.common.ScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

   private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
   val state: StateFlow<HomeState> = _state.asStateFlow()

   init {
      getAllNotes()
   }

   private fun getAllNotes() {
      getAllNotesUseCase()
          .onEach {
             _state.value = HomeState(notes = ScreenViewState.Success(it))
          }
          .catch {
             _state.value = HomeState(notes = ScreenViewState.Error(it.message ?: "unknown error"))
          }
          .launchIn(viewModelScope)
   }

   fun deleteNote(noteId: Long) = viewModelScope.launch { deleteNoteUseCase(noteId) }

   fun onBookMarkChange(note:Note){
      viewModelScope.launch { updateNoteUseCase(note.copy(isBookMarked = !note.isBookMarked)) }
   }

}

data class HomeState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading
)