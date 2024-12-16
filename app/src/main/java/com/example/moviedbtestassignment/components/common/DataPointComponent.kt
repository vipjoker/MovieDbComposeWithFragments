package com.example.moviedbtestassignment.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


data class DataPoint(val title:String, val description:String)

@Composable
fun DataPointComponent(dataPoint: DataPoint){
    Column(){
        Text(
            text= dataPoint.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text= dataPoint.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun DataPointPreview(){
    val data = DataPoint("Test titile", "Simple text descrption")
    DataPointComponent(data)
}

