package com.example.moviedbtestassignment.constraint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@Composable
@Preview(showSystemUi = true)
fun ConstraintSetExample (){

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        constraintSet = myConstraintSet()
    ) {

        Square(modifier = Modifier.layoutId("blueSquare"),
            color= Color.Blue,
            size =  100.dp)

        Square(modifier = Modifier.layoutId("greenSquare"),
            color= Color.Green, size =  100.dp)

        Square(modifier = Modifier.layoutId("redSquare"))

        Square(modifier = Modifier.layoutId("cyanSquare"),
            color = Color.Cyan,
            size = 100.dp)
    }

}



private fun myConstraintSet() = ConstraintSet {
    val redSquare = createRefFor("redSquare")
    val cyanSquare = createRefFor("cyanSquare")
    val blueSquare = createRefFor("blueSquare")
    val greenSquare = createRefFor("greenSquare")

    constrain(blueSquare){
        bottom.linkTo(parent.bottom)
        end.linkTo(parent.end)
    }


    constrain(greenSquare){
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(redSquare){
        centerVerticallyTo(parent)
        centerHorizontallyTo(parent)
    }

    constrain(cyanSquare){
        end.linkTo(parent.end)
        centerVerticallyTo(parent)
    }
}