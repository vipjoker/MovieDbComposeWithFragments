package com.example.moviedbtestassignment.examples.scafold

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme

enum class TabItems(val title:String, val icon: ImageVector){
    Items("Items", Icons.Default.List),
    Settings("Settings", Icons.Default.Settings),
    Profile("Profile", Icons.Default.AccountBox)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, device = Devices.NEXUS_6)
@Composable
fun NavigationBarExample() {

    var selectedItem by remember { mutableStateOf(TabItems.Items) }

    AppTheme {
        Column(Modifier.fillMaxSize()) {


            CenterAlignedTopAppBar(
                title = { Text(text = selectedItem.title, fontSize = 30.sp) }
            )


            Surface(color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize().weight(1.0f)
            ) {
                when (selectedItem) {
                    TabItems.Profile-> ProfileScreen()
                    TabItems.Settings-> SettingsScreen()
                    TabItems.Items-> ItemsScreen()
                }

            }

            NavigationBar {

                TabItems.entries.forEach {
                    NavigationBarItem(
                        selected = selectedItem == it,
                        onClick = {
                            selectedItem = it
                        },
                        label = { Text(it.title) },
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
}

}

@Composable
fun ItemsScreen(){
    Box(modifier = Modifier.fillMaxSize().background(Color.Cyan), contentAlignment = Alignment.Center){
        Text(text = "Items Screen")
    }

}

@Composable
fun SettingsScreen(){
    Box(modifier = Modifier.fillMaxSize().background(Color.Green), contentAlignment = Alignment.Center){
        Text(text = "Settings Screen")
    }

}
@Composable
fun ProfileScreen(){
    Box(modifier = Modifier.fillMaxSize().background(Color.Yellow), contentAlignment = Alignment.Center){
        Text(text = "Profile Screen")
    }

}

@Composable
fun ButtonExample(){
    Text("HELLO")

//    NavigationBarItem(
//        selected = true,
//        label = { Text(text = "Settings") },
//        onClick = {},
//        enabled = false,
//        alwaysShowLabel = false ,
//        icon = {
//            Icon(
//                imageVector = Icons.Default.Settings,
//                contentDescription = "Settings"
//            )
//        },
//        colors = NavigationBarItemDefaults.colors(
//            unselectedIconColor = Color.Red,
//            selectedIconColor = Color.Blue,
//            disabledIconColor = Color.Green,
//
//            unselectedTextColor = Color.Red,
//            selectedTextColor = Color.Blue,
//            disabledTextColor = Color.Cyan,
//            indicatorColor = Color.Yellow //round background color
//
//        )
//    )
}