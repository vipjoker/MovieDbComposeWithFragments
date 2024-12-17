package com.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigation.internal.EmptyRouter

val LocalRouter = staticCompositionLocalOf <Router>{
    EmptyRouter
}

@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
    routerMapper: @Composable (Route) -> Unit
) {
        val (router, navigationState) = navigation

    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    CompositionLocalProvider() {
        LocalRouter provides router
    }
    Box(modifier = modifier,contentAlignment = Alignment.Center){
        routerMapper.invoke(navigationState.currentRoute)
    }

}