package com.example.moviedbtestassignment.examples.constraint

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
fun ConstraintLink (){

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (square1, square2,square3, square4)  = createRefs()





        Square(modifier = Modifier.constrainAs(square1){

            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)

        }, color= Color.Blue, size =  100.dp)

        Square(modifier = Modifier.constrainAs(square3){

            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)

        }, color= Color.Green, size =  100.dp)






        Square(modifier = Modifier.constrainAs(square2){
            centerVerticallyTo(parent)
        })

        Square(modifier = Modifier.constrainAs(square4){
            end.linkTo(parent.end)
            centerVerticallyTo(parent)


        }, color = Color.Cyan, size = 100.dp)


    }

}

