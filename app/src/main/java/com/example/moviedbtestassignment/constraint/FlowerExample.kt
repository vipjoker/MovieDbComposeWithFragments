package com.example.moviedbtestassignment.constraint

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviedbtestassignment.ui.screen.MovieInfo

@Preview(showSystemUi = true)
@Composable
fun FlowerExample() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {



        val (square1,square2,square3, square4,square5) = createRefs()
        val (small1,small2,small3,small4) = createRefs()



        Square(color = Color.Magenta, modifier = Modifier.constrainAs(square1){
            centerTo(parent)

        })



        Square(color = Color.Yellow, size = 70.dp, modifier = Modifier.constrainAs(square2){
            end.linkTo(square1.start)
            centerVerticallyTo(square1)
        })


        Square(color = Color.Gray, size = 70.dp, modifier = Modifier.constrainAs(square4){
            bottom.linkTo(square1.top)
            centerHorizontallyTo(square1)
        })

        Square(color = Color.Green, size = 70.dp, modifier = Modifier.constrainAs(square3){
            start.linkTo(square1.end)
            centerVerticallyTo(square1)
        })

        Square(color = Color.Red, size = 70.dp, modifier = Modifier.constrainAs(square5){
            top.linkTo(square1.bottom)
            centerHorizontallyTo(square1)
        })

    Square(color = Color.Blue, size = 40.dp, modifier = Modifier.constrainAs(small1){
            bottom.linkTo(square1.bottom)
            centerHorizontallyTo(square1)
        })


        Square(color = Color.Black, size = 40.dp, modifier = Modifier.constrainAs(small2){
            top.linkTo(square1.top)
            centerHorizontallyTo(square1)
        })


        Square(color = Color.DarkGray, size = 40.dp, modifier = Modifier.constrainAs(small3){
            start.linkTo(square1.start)
            centerVerticallyTo(square1)
        })

        Square(color = Color.LightGray, size = 40.dp, modifier = Modifier.constrainAs(small4){
            end.linkTo(square1.end)
            centerVerticallyTo(square1)
        })




    }
}