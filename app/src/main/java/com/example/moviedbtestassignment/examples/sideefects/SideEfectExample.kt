package com.example.moviedbtestassignment.examples.sideefects

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
fun SideEfectExample (){
    val ctx = LocalContext.current


    var counter by remember { mutableStateOf(0) }
    ConstraintLayout(modifier = Modifier.fillMaxSize()){


        var (first, second, third) = createRefs()
        Button({
            counter++
        }, modifier = Modifier.constrainAs(first){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            Text("Press me")
        }


        Text(counter.toString(), modifier = Modifier.constrainAs(second){
            top.linkTo(first.bottom)
            centerHorizontallyTo(parent)
        })

        if(counter % 3 != 0){
            SideEffect {

                Log.i("DEBUG6", "SideEfectExample: ${Thread.currentThread().name}")
            }

            LaunchedEffect(0) {
                delay(1000)
                Log.i("DEBUG6", "launchedEffect: ${Thread.currentThread().name}")


            }

            DisposableEffect(0) {

                onDispose {
                    Log.i("DEBUG6", "Disposed: ${Thread.currentThread().name}")


                }
            }

            Box(
                Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .constrainAs(third) {
                        top.linkTo(second.bottom, margin = 15.dp)
                        centerHorizontallyTo(parent)
                    }, contentAlignment = Alignment.CenterStart){


                Text("New counter ${counter.toString()}")
            }
        }
    }
}