package com.example.moviedbtestassignment.examples.constraint



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showSystemUi = true)

@Composable
fun BiasExample() {


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (square1, square2, square3, square4) = createRefs()
        val (boxLeft, boxRight) = createRefs()
        val (redBlock1, redBlock2, redBlock3, redBlock4, redBlock5, redBlock6, redBlock7) = createRefs()
        Box(modifier = Modifier
            .background(Color.Black)
            .size(20.dp, 300.dp)
            .constrainAs(boxLeft) {
                start.linkTo(parent.start, 15.dp)
                centerVerticallyTo(parent)

            }
        )

        Box(modifier = Modifier
            .background(Color.Black)
            .size(20.dp, 300.dp)
            .constrainAs(boxRight) {
                end.linkTo(parent.end, 15.dp)
                centerVerticallyTo(parent)

            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock1) {


                linkTo(
                    start = boxLeft.end,
                    end = boxRight.start,
                    bias = 0.3f


                )

                linkTo(
                    top = parent.top,
                    bottom = parent.bottom,
                    bias = 0.4f
                )

                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )

    }
}