package com.example.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigation.internal.EmptyRouter
import com.example.navigation.internal.NavigationEvent
import kotlinx.coroutines.flow.filterIsInstance

val LocalRouter = staticCompositionLocalOf<Router> {
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

    val saveableStateHolder = rememberSaveableStateHolder()

    saveableStateHolder.SaveableStateProvider(key = navigationState.currentRoute) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalRouter provides router) {
                routerMapper.invoke(navigationState.currentRoute)
            }
        }
    }

    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect { event->
                saveableStateHolder.removeState(event.route)
            }
    }

}