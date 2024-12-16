package com.example.moviedbtestassignment.examples.scafold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, device = Devices.NEXUS_6)
@Composable
fun TopBarExample ( ) {
    val scrollbehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(modifier = Modifier.fillMaxSize().nestedScroll(scrollbehaviour.nestedScrollConnection)) {

        LargeTopAppBar(
//        MediumTopAppBar(

//        CenterAlignedTopAppBar(
//        TopAppBar( // same but title in left

            title = { Text(text = "Pure title") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Menu"
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Menu"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Menu"
                    )
                }

            },

            scrollBehavior = scrollbehaviour,
            colors = TopAppBarDefaults.topAppBarColors(

                containerColor = Color.Red,
                titleContentColor = Color.Magenta,
                navigationIconContentColor = Color.Blue,
                actionIconContentColor = Color.Green,
                scrolledContainerColor = Color.Yellow
            ),
//            windowInsets = WindowInsets(0.dp)
            )
        LazyColumn(Modifier.fillMaxSize()) {items(100) {
            Text(text = "Text ${it+1}", fontSize = 20.sp, modifier = Modifier.padding(10.dp))

        }}
    }


}

