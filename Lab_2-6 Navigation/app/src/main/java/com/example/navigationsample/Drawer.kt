package com.example.navigationsample

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Drawer() {
  val items = listOf(
    DrawerItem(
      Icons.Default.AddCircle,
      "First Screen"
    ),
    DrawerItem(
      Icons.Default.Face,
      "Second Screen"
    )
  )
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  val selectedItem = remember {
    mutableStateOf(items[0])
  }

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet {
        items.forEach { item ->
          NavigationDrawerItem(
            label = {
              Text(text = item.title)
            },
            selected = selectedItem.value == item,
            icon = {
              Icon(
                imageVector = item.imageVector,
                contentDescription = item.title
              )
            },
            onClick = {
              scope.launch {
                selectedItem.value = item
                drawerState.close()
              }
            })
        }
      }
    },
    content = {
      scope.launch { drawerState.open() }
      MyApp()
    })

}

data class DrawerItem(
  val imageVector: ImageVector,
  val title: String
)

@Composable
fun MyApp() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "firstscreen") {
    composable("firstscreen") {
      FirstScreen { name ->
        navController.navigate("secondscreen/$name")
      }
    }
    composable("secondscreen/{name}") {
      val name = it.arguments?.getString("name") ?: "no name"
      SecondScreen(name) { name ->
        navController.navigate("firstscreen")
      }
    }
  }
}