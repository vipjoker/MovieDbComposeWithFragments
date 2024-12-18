package com.example.moviedbtestassignment.examples.navigation.advanced.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviedbtestassignment.examples.navigation.ItemsRepository


@Composable
fun ListTabScreen(){
    val repository = ItemsRepository.get()
    val stateList by repository.getItems().collectAsStateWithLifecycle()



    val isEmpty by remember {
        derivedStateOf { stateList.isEmpty() }
    }
    ListTabContent(isEmpty,{stateList})

}



@Composable
fun ListTabContent(
    isItemsEmpty: Boolean,
    items:()-> List<String>) {
    if (isItemsEmpty) {
        Text("No items")
    } else {
        LazyColumn(Modifier.fillMaxSize()) {


            items(items.invoke()) {

                Text(text = it, Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewList(){
    ListTabContent(true,{ listOf("ONE","TWO","THREE","FOUR","FIVE")})

}