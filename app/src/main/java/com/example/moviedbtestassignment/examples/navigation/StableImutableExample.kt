package com.example.moviedbtestassignment.examples.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.Label
import java.util.UUID

@Composable
fun StableImutableExample() {
    var label by remember { mutableStateOf("Hello") }
    var counter by remember { mutableIntStateOf(0) }
    val lbl = Label(counter.toString())
    val lbl2 = Label(label)

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CounterText(lbl)
        LabelText(lbl2)
        Spacer(Modifier.height(100.dp))
        OutlinedButton(onClick = {
            counter++
        }) { Text("Increment") }
        OutlinedButton(onClick = {
            label = UUID.randomUUID().toString().substringBefore("-")

        }) { Text("Random Label") }
    }

}


@Composable
fun CounterText(counter: Label){
    Text(text = counter.text,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )

}

@Composable
fun LabelText(label:Label){
    Text(text = label.text,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )

}

