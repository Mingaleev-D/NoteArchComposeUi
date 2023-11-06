package com.example.notearchcomposeui.ui.view.home

import android.content.res.Configuration
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.ui.common.ScreenViewState
import com.example.notearchcomposeui.ui.theme.NoteArchComposeUiTheme
import java.util.Date

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onBookmarkChange: (note: Note) -> Unit,
    onDeleteNote: (noteId: Long) -> Unit,
    onNoteClick: (noteId: Long) -> Unit
) {

   when (state.notes) {
      is ScreenViewState.Error -> {
         Text(text = state.notes.error ?: "unknown error", color = MaterialTheme.colorScheme.error)
      }

      ScreenViewState.Loading -> {
         CircularProgressIndicator()
      }

      is ScreenViewState.Success -> {
         val notes = state.notes.data
         HomeDetail(
             notes = notes,
             modifier = modifier,
             onBookmarkChange = onBookmarkChange,
             onDeleteNote = onDeleteNote,
             onNoteClick = onNoteClick
         )
      }
   }
}

@Preview(
    name = "Ночь-Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "День-Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun HomeScreenPreview() {
   NoteArchComposeUiTheme {
      HomeScreen(
          state = HomeState(notes = ScreenViewState.Success(notes)),
          onBookmarkChange = {},
          onDeleteNote = {},
          onNoteClick = {})
   }
}

val placeHolder = "ЕУче пустышка"
val notes = listOf(
    Note(
        title = "Demo",
        content = placeHolder + placeHolder,
        createdDate = Date()
    ),
    Note(
        title = "Demo",
        content = placeHolder + placeHolder,
        createdDate = Date(),
        isBookMarked = true
    ),
)