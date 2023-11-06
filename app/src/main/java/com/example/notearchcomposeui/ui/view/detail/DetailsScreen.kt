package com.example.notearchcomposeui.ui.view.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    noteId: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit,
) {
   val viewModel = viewModel(
       modelClass = DetailsViewModel::class.java,
       factory = DetailedViewModelFactory(
           noteId = noteId,
           assistedFactory = assistedFactory
       )
   )

   val state = viewModel.state
   DetailItem(
       modifier = modifier,
       isUpdatingNote = state.isUpdatingNote,
       title = state.title,
       content = state.content,
       isBookmark = state.isBookmark,
       onBookMarkChanged = viewModel::onBookMarkChange,
       isFormNoteBlank = viewModel.isForNotBlank,
       onTitleChange = viewModel::onTitleChange,
       onContentChange = viewModel::onContentChange,
       onBtnClick = {
          viewModel.addOrUpdateNote()
          navigateUp()
       },
       onNavigateUp = navigateUp
   )

}

@Composable
private fun DetailItem(
    modifier: Modifier = Modifier,
    isUpdatingNote: Boolean,
    title: String,
    content: String,
    isBookmark: Boolean,
    onBookMarkChanged: (Boolean) -> Unit,
    isFormNoteBlank: Boolean,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBtnClick: () -> Unit,
    onNavigateUp: () -> Unit
) {

   Column(
       modifier = modifier.fillMaxWidth()
   ) {
      TopSelection(
          title = title,
          isBookmark = isBookmark,
          onBookmarkChange = onBookMarkChanged,
          onTitleChange = onTitleChange,
          onNavigateUp = onNavigateUp,
      )
      Spacer(modifier = Modifier.size(12.dp))
      AnimatedVisibility(visible = isFormNoteBlank) {
         Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(12.dp),
             horizontalArrangement = Arrangement.End
         ) {
            IconButton(onClick = onBtnClick) {
               val icon = if (isUpdatingNote) Icons.Default.Update else Icons.Default.Check
               Icon(imageVector = icon, contentDescription = null)
            }
         }
      }
      Spacer(modifier = Modifier.size(12.dp))
      NotesTextField(
          modifier = Modifier.weight(1f),
          value = content,
          onValueChanged = onContentChange,
          label = "Контент",
      )
   }

}

@Composable
fun TopSelection(
    modifier: Modifier = Modifier,
    title: String,
    isBookmark: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    onTitleChange: (String) -> Unit,
    onNavigateUp: () -> Unit
) {

   Row(
       modifier = modifier.fillMaxWidth(),
       horizontalArrangement = Arrangement.Center,
       verticalAlignment = Alignment.CenterVertically
   ) {
      IconButton(onClick = onNavigateUp) {
         Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = null)
      }

      NotesTextField(
          modifier = Modifier.weight(1f),
          value = title,
          onValueChanged = onTitleChange,
          label = "Заголовок",
          labelAlign = TextAlign.Center
      )

      IconButton(onClick = { onBookmarkChange(!isBookmark) }) {
         val icon = if (isBookmark) Icons.Default.BookmarkRemove else Icons.Outlined.BookmarkAdd
         Icon(imageVector = icon, contentDescription = null)
      }
   }

}

@Composable
private fun NotesTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    labelAlign: TextAlign? = null
) {

   OutlinedTextField(
       modifier = modifier,
       value = value,
       onValueChange = onValueChanged,
       colors = TextFieldDefaults.colors(
           disabledContainerColor = Color.Transparent,
           focusedContainerColor = Color.Transparent,
           unfocusedContainerColor = Color.Transparent,
           unfocusedIndicatorColor = Color.Transparent,
           focusedIndicatorColor = Color.Transparent
       ),
       placeholder = {
          Text(text = "Добавьте $label", textAlign = labelAlign, modifier = Modifier.fillMaxWidth())
       }
   )

}
