package com.example.notearchcomposeui.ui.view.bookmark

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notearchcomposeui.data.local.model.Note
import com.example.notearchcomposeui.ui.common.ScreenViewState
import com.example.notearchcomposeui.ui.theme.NoteArchComposeUiTheme
import com.example.notearchcomposeui.ui.view.home.HomeScreen
import com.example.notearchcomposeui.ui.view.home.HomeState
import com.example.notearchcomposeui.ui.view.home.NoteCard
import com.example.notearchcomposeui.ui.view.home.notes

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Composable
fun BookMarkScreen(
    modifier: Modifier = Modifier,
    state: BookmarkState,
    onBookmarkChange: (note: Note) -> Unit,
    onDelete: (noteId: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {

   when (state.notes) {
      is ScreenViewState.Error -> {
         Text(text = state.notes.error ?: "unknown error", color = MaterialTheme.colorScheme.error)
      }
      ScreenViewState.Loading -> CircularProgressIndicator()

      is ScreenViewState.Success -> {
         val notes = state.notes.data
         LazyColumn(contentPadding = PaddingValues(4.dp), modifier = modifier) {
            itemsIndexed(notes) { index, note ->
               NoteCard(
                   index = index,
                   note = note,
                   onBookmarkChange = onBookmarkChange,
                   onDeleteNote = onDelete,
                   onNoteClick = onNoteClick,
               )
            }
         }
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
private fun BookMarkScreenPreview() {
   NoteArchComposeUiTheme {
      BookMarkScreen(
          state = BookmarkState(notes = ScreenViewState.Success(notes)),
          onBookmarkChange = {},
          onDelete = {},
          onNoteClick = {})
   }
}