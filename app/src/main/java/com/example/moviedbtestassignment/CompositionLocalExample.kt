package com.example.moviedbtestassignment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


data class MyTheme(val buttonColor:Color, val backgroundColor: Color){
    companion object{
        val DARK = MyTheme(Color.Gray,Color.Black)
        val LIGHT = MyTheme(Color.Blue, Color.White)
    }
}



val LocalAppThmee = compositionLocalOf {
    MyTheme.LIGHT
}


@Composable
fun  CompositionLocalExample (){

    CompositionLocalProvider() {
        LocalAppThmee provides MyTheme.DARK
        val theme = LocalAppThmee.current

    }
}