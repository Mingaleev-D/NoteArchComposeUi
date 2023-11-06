package com.example.notearchcomposeui.ui.view.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.domain.usecase.DeleteNoteUseCase
import com.example.notearchcomposeui.domain.usecase.FilteredBookmarkNotesUseCase
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
class BookMarkViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val filteredBookMarkedNotesUseCase: FilteredBookmarkNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

   private val _state: MutableStateFlow<BookmarkState> = MutableStateFlow(BookmarkState())
   val state: StateFlow<BookmarkState> = _state.asStateFlow()

   init {
      getBookMarkNotes()
   }

   private fun getBookMarkNotes() {
      filteredBookMarkedNotesUseCase()
          .onEach {
             _state.value = BookmarkState(notes = ScreenViewState.Success(it))
          }
          .catch {
             _state.value = BookmarkState(notes = ScreenViewState.Error(it.message ?: "unknown error"))
          }
          .launchIn(viewModelScope)
   }

   fun onBookMarkChange(note: Note) {
      viewModelScope.launch {
         updateNoteUseCase(note.copy(isBookMarked = !note.isBookMarked))
      }
   }
   fun deleteNote(noteId:Long){
      viewModelScope.launch {
         deleteNoteUseCase(noteId)
      }
   }
}

data class BookmarkState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading
)