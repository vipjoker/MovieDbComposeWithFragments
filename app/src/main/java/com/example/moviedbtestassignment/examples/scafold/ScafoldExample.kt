package com.example.moviedbtestassignment.examples.scafold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, device = Devices.NEXUS_6)
@Composable
fun ScafoldExample() {
    AppTheme {
        Scaffold(

            topBar = {
                CenterAlignedTopAppBar(title = { Text(text = "Top bar", fontSize = 30.sp) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary

                    ),
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "settings"
                            )
                        }

                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                        }
                    }


                )


            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        label = { Text(text = "Home") },
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "home"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        label = { Text(text = "Settings") },
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        label = { Text(text = "Home") },
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "list"
                            )
                        }
                    )
                }


            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            snackbarHost = {}

        ) { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Text(text = "Main content ", fontSize = 30.sp)
            }
        }
    }
}


@Composable
fun ScafoldPure() {
    Scaffold(

        topBar = { Text(text = "Top bar", fontSize = 30.sp) },
        bottomBar = { Text(text = "Bottom bar", fontSize = 30.sp) },
        floatingActionButton = { Text(text = "Floating button", fontSize = 30.sp) },
        floatingActionButtonPosition = FabPosition.Center,
        snackbarHost = {}

    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Text(text = "Main content ", fontSize = 30.sp)
        }
    }
}