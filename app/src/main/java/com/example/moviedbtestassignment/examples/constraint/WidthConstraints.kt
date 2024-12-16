package com.example.moviedbtestassignment.examples.constraint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showSystemUi = true)

@Composable
fun WidthConstraints() {


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
            .size(40.dp)
            .constrainAs(redBlock1) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 20.dp)
                height = Dimension.value(15.dp)
                width = Dimension.fillToConstraints
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .size(40.dp)
            .constrainAs(redBlock2) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 60.dp)
                height = Dimension.value(15.dp)
                width = Dimension.matchParent
            }
        )

        Box(modifier = Modifier
            .background(Color.Red)
            .size(40.dp)
            .constrainAs(redBlock3) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 100.dp)
                height = Dimension.value(15.dp)
                width = Dimension.percent(.5f) //50% of parrent
            }
        )



        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock4) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 150.dp)
                height = Dimension.value(15.dp)
                width = Dimension.wrapContent
            }
        ) {

            Text("Tefeofeifeofijfefejfeofieeoffeoijeoifjeoifjeoifjoeiajfoffeije")
        }

        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock5) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 200.dp)
                height = Dimension.value(15.dp)
                width = Dimension.preferredWrapContent
            }
        ) {

            Text("Tefeofeifeofijfefejfeofieeoffeoijeoifjeoifjeoifjoeiajfoffeije")
        }




        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock6) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 240.dp)
                height = Dimension.value(15.dp)
                width = Dimension.preferredValue(500.dp) // 500 is ignored
            }
        ) {

        }
        Box(modifier = Modifier
            .background(Color.Red)
            .constrainAs(redBlock7) {
                start.linkTo(boxLeft.end)
                end.linkTo(boxRight.start)
                top.linkTo(boxLeft.top, 280.dp)
                height = Dimension.value(50.dp)
                width = Dimension.ratio("16:9") // 500 is ignored
            }
        ) {

        }


    }
}