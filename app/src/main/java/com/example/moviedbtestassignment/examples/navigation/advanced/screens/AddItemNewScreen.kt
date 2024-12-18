package com.example.moviedbtestassignment.examples.navigation.advanced.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviedbtestassignment.examples.navigation.ItemsRepository
import com.example.moviedbtestassignment.examples.navigation.advanced.AppRoute
import com.example.navigation.LocalRouter


@Composable
fun AddItemNewScreen(){
    val itemRepository = ItemsRepository.get()
    val router = LocalRouter.current
    AddItemContent (callback = {
        itemRepository.addItem(it)
        router.pop()
    }, lauchSettings = {
        router.launch(AppRoute.Tab.Settings)
    })
}

@Composable
fun AddItemContent(callback: (String) -> Unit, lauchSettings: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        var text by rememberSaveable { mutableStateOf("") }
        val isAddEnabled by remember { derivedStateOf { text.isNotEmpty() } }
        OutlinedTextField(
            value = text,
            label = { Text(text = "Add new item") },
            singleLine = true,
            onValueChange = {
                text = it
            })

        Button(enabled = isAddEnabled, onClick = {
                if (text.isNotEmpty()) {
                    callback(text)
                }
            })
        { Text("Add item") }

        Button( onClick = lauchSettings)
        { Text("Launch Settings") }
    }

}

@Composable
@Preview(showSystemUi = true)
private fun AddItemContentPreview(){
    AddItemContent(callback = {}, lauchSettings = {})
}