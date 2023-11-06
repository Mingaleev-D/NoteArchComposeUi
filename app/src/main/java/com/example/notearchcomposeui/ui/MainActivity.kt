package com.example.notearchcomposeui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.notearchcomposeui.ui.navigation.NoteNavitagion
import com.example.notearchcomposeui.ui.navigation.Screens
import com.example.notearchcomposeui.ui.navigation.navigateToSingleTop
import com.example.notearchcomposeui.ui.theme.NoteArchComposeUiTheme
import com.example.notearchcomposeui.ui.view.bookmark.BookMarkViewModel
import com.example.notearchcomposeui.ui.view.detail.DetailAssistedFactory
import com.example.notearchcomposeui.ui.view.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   @Inject
   lateinit var assistedFactory: DetailAssistedFactory

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         NoteArchComposeUiTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
               MyApp()
            }
         }
      }
   }

   @OptIn(ExperimentalMaterial3Api::class)
   @Composable
   fun MyApp() {
      val homeViewModel: HomeViewModel = viewModel()
      val bookmarkViewModel: BookMarkViewModel = viewModel()
      val navController = rememberNavController()
      var currentTab by remember { mutableStateOf(TabScreen.HOME) }

      Scaffold(
          bottomBar = {
             BottomAppBar(
                 actions = {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                       InputChip(
                           selected = currentTab == TabScreen.HOME,
                           onClick = {
                              currentTab = TabScreen.HOME
                              navController.navigateToSingleTop(route = Screens.HOME.name)
                           },
                           label = { Text(text = "Старт") },
                           trailingIcon = {
                              Icon(imageVector = Icons.Default.Home, contentDescription = null)
                           }
                       )

                       Spacer(modifier = Modifier.size(12.dp))

                       InputChip(
                           selected = currentTab == TabScreen.BOOKMARK,
                           onClick = {
                              currentTab = TabScreen.BOOKMARK
                              navController.navigateToSingleTop(route = Screens.Bookmark.name)
                           },
                           label = { Text(text = "Закладка") },
                           trailingIcon = {
                              Icon(imageVector = Icons.Default.Bookmark, contentDescription = null)
                           }
                       )
                    }

                 },
                 floatingActionButton = {
                    FloatingActionButton(onClick = { navController.navigateToSingleTop(route = Screens.DETAILS.name) }) {
                       Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                 }
             )

          }
      ) {
         NoteNavitagion(
             modifier = Modifier.padding(it),
             navHostController = navController,
             homeViewModel = homeViewModel,
             bookmarkViewModel = bookmarkViewModel,
             assistedFactory = assistedFactory
         )
      }
   }
}

enum class TabScreen {
   HOME,
   BOOKMARK
}

