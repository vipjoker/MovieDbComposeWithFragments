package com.example.moviedbtestassignment.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//https://m3.material.io/
@Composable
@Preview(showSystemUi = true)
fun MaterialExample() {

    MaterialTheme() {
        Surface() {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {


                var state by remember { mutableIntStateOf(0) }

                Column {

                    Text("Count is $state" )
                    Spacer(Modifier.height(10.dp))
                    Button({state++}) { Text("h world") }


                }
            }

        }
    }

}