package com.example.moviedbtestassignment.examples.navigation

import android.graphics.drawable.Icon
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AppTheme
import com.example.moviedbtestassignment.R
import com.example.moviedbtestassignment.examples.dropdown.DropDownExample
import com.example.moviedbtestassignment.examples.scafold.TabItems

sealed class Route(@StringRes val titleRes: Int = 0) {


    object AddItem : Route(R.string.add_item)

    sealed class Tab(@StringRes titleRes: Int, val icon: ImageVector) : Route(titleRes) {
        object Items : Tab(R.string.items, Icons.Default.List)
        object Settings : Tab(R.string.settings, Icons.Default.Settings)
        object Profile : Tab(R.string.profile, Icons.Default.AccountBox)
    }

}

val RootTabs = listOf(Route.Tab.Items, Route.Tab.Settings, Route.Tab.Profile)


@Composable
@Preview(showSystemUi = true)
fun NavigationExample() {
    AppTheme {
        AppScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(itemsRepository: ItemsRepository = ItemsRepository.get()) {
    val items by itemsRepository.getItems().collectAsStateWithLifecycle()

    val stack = remember { mutableStateListOf<Route>(Route.Tab.Items) }

    val currentRoute = stack.last()
    val isRoot = stack.size == 1

    BackHandler(enabled = !isRoot) {
        stack.removeAt(stack.lastIndex)
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(currentRoute.titleRes)) },
                navigationIcon = {
                    IconButton(onClick = {

                        if (!isRoot) {
                            stack.removeAt(stack.lastIndex)
                        }
                    }) {
                        Icon(
                            imageVector = if (isRoot) Icons.Default.Menu else Icons.Default.ArrowBackIosNew,
                            contentDescription = "menu"
                        )

                    }
                },
                actions = {
                    var showPopupMenu by remember { mutableStateOf(false) }
                    IconButton(onClick = { showPopupMenu = !showPopupMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "menu")
                    }

                    DropdownMenu(
                        expanded = showPopupMenu,
                        onDismissRequest = { showPopupMenu = false }) {
                        val context = LocalContext.current
                        DropdownMenuItem(text = { Text(text = "About") }, onClick = {
                            Toast.makeText(context, "Scaffold app", Toast.LENGTH_SHORT).show()
                            showPopupMenu = false
                        })

                        DropdownMenuItem(text = { Text(text = "Clear all") }, onClick = {
                            itemsRepository.clearItems()
                            showPopupMenu = false
                        })

                    }


                }

            )

        },
        bottomBar = {
            if (isRoot) {
                NavigationBar {

                    RootTabs.forEach {
                        NavigationBarItem(
                            selected = currentRoute == it,
                            onClick = {
                                stack.clear()
                                stack.add(it)

                            },
                            label = { Text(text = stringResource(it.titleRes)) },
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = "home"
                                )
                            }
                        )

                    }
                }
            }
        },

        floatingActionButton = {
            if (currentRoute == Route.Tab.Items) {
                FloatingActionButton(onClick = {
                    stack.add(Route.AddItem)


                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            }
        },


        content = { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                when (currentRoute) {
                    Route.AddItem -> AddItemScreen {
                        ItemsRepository.get().addItem(it)
                        stack.removeAt(stack.lastIndex)
                    }
                    Route.Tab.Items -> ListScreen(items)
                    Route.Tab.Profile -> ProfileScreen()
                    Route.Tab.Settings -> SettingsScreen()
                }
            }
        })
}

@Composable
fun ProfileScreen() {
    Text("Profile")
}

@Composable
fun SettingsScreen() {
    Text("Settings")
}

@Composable
fun ListScreen(items:List<String>) {
    if(items.isEmpty()){
        Text("No items")
    }else {
        LazyColumn(Modifier.fillMaxSize()) {


            items(items) {

                Text(text = it, Modifier.padding(10.dp))
            }
        }
    }


}

@Composable
fun AddItemScreen(callback: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var text by remember { mutableStateOf("") }
        val isAddEnabled by remember { derivedStateOf { text.isNotEmpty() }  }
        OutlinedTextField(
            value = text,
            label = { Text(text = "Add new item") },
            singleLine = true,
            onValueChange = {
                text = it
            })

        Button(
            enabled = isAddEnabled,
            onClick = {
            if (text.isNotEmpty()) {
                callback(text)
            }
        })
        { Text("Add item") }
    }

}



