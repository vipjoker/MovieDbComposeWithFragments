package com.example.moviedbtestassignment.constraint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showSystemUi = true)
fun ConstraintLayoutExample (){

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (square1, square2)  = createRefs()





        Square(modifier = Modifier.constrainAs(square1){
            centerTo(parent)
        }, color= Color.Blue, size =  100.dp)

        Square(modifier = Modifier.constrainAs(square2){
            centerVerticallyTo(parent)
        })


    }

}

@Composable
fun Square(modifier: Modifier = Modifier,color: Color = Color.Red, size: Dp = 150.dp){
     Box(modifier = modifier.background(color).size(size)){

    }
}