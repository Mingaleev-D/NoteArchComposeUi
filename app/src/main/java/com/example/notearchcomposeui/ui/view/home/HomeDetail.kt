package com.example.notearchcomposeui.ui.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notearchcomposeui.data.local.model.Note

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Composable
fun HomeDetail(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onBookmarkChange: (note: Note) -> Unit,
    onDeleteNote: (noteId: Long) -> Unit,
    onNoteClick: (noteId: Long) -> Unit
) {
   LazyVerticalStaggeredGrid(
       columns = StaggeredGridCells.Fixed(2),
       contentPadding = PaddingValues(4.dp),
       modifier = modifier
   ) {
      itemsIndexed(notes) { index, note ->
         NoteCard(
             index = index,
             note = note,
             onBookmarkChange = onBookmarkChange,
             onDeleteNote = onDeleteNote,
             onNoteClick = onNoteClick
         )
      }
   }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    index: Int,
    note: Note,
    onBookmarkChange: (note: Note) -> Unit,
    onDeleteNote: (noteId: Long) -> Unit,
    onNoteClick: (noteId: Long) -> Unit
) {

   val isEventIndex = index % 2 == 0
   val shape = when {
      isEventIndex -> {
         RoundedCornerShape(topStart = 50f, bottomEnd = 50f)
      }

      else -> {
         RoundedCornerShape(
             topEnd = 50f,
             bottomStart = 50f,
         )
      }
   }
   val icon = if (note.isBookMarked) Icons.Default.BookmarkRemove else Icons.Outlined.BookmarkAdd

   Card(
       modifier = Modifier
           .fillMaxWidth()
           .padding(4.dp),
       shape = shape,
       onClick = { onNoteClick(note.id) },
   ) {

      Column(
          modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp)
      ) {
         Text(
             text = note.title,
             fontWeight = FontWeight.Bold,
             maxLines = 1,
             style = typography.titleMedium
         )
         Spacer(modifier = Modifier.size(4.dp))
         Text(
             text = note.content,
             maxLines = 4,
             overflow = TextOverflow.Ellipsis,
             style = typography.bodyMedium
         )
         Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.CenterVertically
         ) {
            IconButton(onClick = { onDeleteNote(note.id) }) {
               Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
            IconButton(onClick = { onBookmarkChange(note) }) {
               Icon(imageVector = icon, contentDescription = null)
            }
         }

      }

   }

}