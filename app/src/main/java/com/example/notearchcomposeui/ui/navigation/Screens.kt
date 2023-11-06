package com.example.notearchcomposeui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notearchcomposeui.ui.view.bookmark.BookMarkScreen
import com.example.notearchcomposeui.ui.view.bookmark.BookMarkViewModel
import com.example.notearchcomposeui.ui.view.detail.DetailAssistedFactory
import com.example.notearchcomposeui.ui.view.detail.DetailsScreen
import com.example.notearchcomposeui.ui.view.home.HomeScreen
import com.example.notearchcomposeui.ui.view.home.HomeViewModel

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

enum class Screens {

   HOME,
   DETAILS,
   Bookmark
}

@Composable
fun NoteNavitagion(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    bookmarkViewModel: BookMarkViewModel,
    assistedFactory: DetailAssistedFactory
) {

   NavHost(navController = navHostController, startDestination = Screens.HOME.name) {
      composable(route = Screens.HOME.name) {
         val state by homeViewModel.state.collectAsState()
         HomeScreen(
             modifier = modifier,
             state = state,
             onBookmarkChange = homeViewModel::onBookMarkChange,
             onDeleteNote = homeViewModel::deleteNote,
             onNoteClick = {
                navHostController.navigateToSingleTop(route = "${Screens.DETAILS.name}?id=$it")
             }
         )
      }
      composable(route = Screens.Bookmark.name) {
         val state by bookmarkViewModel.state.collectAsState()
         BookMarkScreen(
             modifier = modifier,
             state = state,
             onBookmarkChange = bookmarkViewModel::onBookMarkChange,
             onDelete = bookmarkViewModel::deleteNote,
             onNoteClick = {
                navHostController.navigateToSingleTop(route = "${Screens.DETAILS.name}?id=$it")
             }
         )
      }
      composable(route = "${Screens.DETAILS.name}?id={id}", arguments = listOf(navArgument("id") {
         type = NavType.LongType
         defaultValue = -1L
      })) { navBackStackEntry ->
         val id = navBackStackEntry.arguments?.getLong("id") ?: -1L
         DetailsScreen(
             noteId = id,
             assistedFactory = assistedFactory,
             navigateUp = { navHostController.navigateUp() })
      }
   }
}

fun NavHostController.navigateToSingleTop(route: String) {
   navigate(route) {
      popUpTo(graph.findStartDestination().id) { saveState = true }
      launchSingleTop = true
      restoreState = true
   }
}