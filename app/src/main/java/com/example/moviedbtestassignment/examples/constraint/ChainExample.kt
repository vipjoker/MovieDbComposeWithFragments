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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showSystemUi = true)
@Composable
fun ChainExample() {


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (square1, square2, square3, square4) = createRefs()
        val (boxLeft, boxRight) = createRefs()
        val (redBlock1, redBlock2, redBlock3, redBlock4, redBlock5) = createRefs()
        val (yelBlock1,yelBlock2,yelBlock3,yelBlock4) = createRefs()
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
                centerVerticallyTo(parent)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock2) {
                centerVerticallyTo(parent)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock3) {
                centerVerticallyTo(parent)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock4) {
                centerVerticallyTo(parent)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock5) {
                centerVerticallyTo(parent)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }
        )


        val chain = createHorizontalChain(redBlock1,redBlock2,redBlock3,redBlock4,
            redBlock5,
            chainStyle = ChainStyle.Spread
        )

        constrain(chain){
            start.linkTo(boxLeft.end,10.dp)
            end.linkTo(boxRight.start,10.dp)
        }


        Box(modifier = Modifier
            .background(Color.Black)
            .constrainAs(yelBlock1) {
                centerVerticallyTo(parent, bias = 0.6f)
                height = Dimension.value(40.dp)
                width = Dimension.fillToConstraints

                horizontalChainWeight = 1f

            })

        Box(modifier = Modifier
            .background(Color.Yellow)
            .constrainAs(yelBlock2) {
                centerVerticallyTo(parent, bias = 0.6f)
                height = Dimension.value(40.dp)
                width = Dimension.fillToConstraints
                horizontalChainWeight = 2f

            })

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(yelBlock3) {
                centerVerticallyTo(parent, bias = 0.6f)
                height = Dimension.value(40.dp)
                width = Dimension.fillToConstraints
                horizontalChainWeight = 3f

            })

        Box(modifier = Modifier
            .background(Color.Blue)
            .constrainAs(yelBlock4) {
                centerVerticallyTo(parent, bias = 0.6f)
                height = Dimension.value(40.dp)
                width = Dimension.fillToConstraints
                horizontalChainWeight = 2f
            })

        val chain2 = createHorizontalChain(yelBlock1,yelBlock2,yelBlock3,yelBlock4)


        constrain(chain2){
            start.linkTo(boxLeft.end)
            end.linkTo(boxRight.start)
        }


    }
}