package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.navigation.internal.InternalNavigationState
import com.example.navigation.internal.ScreenStack

@Stable
data class Navigation (
    val route: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState

)

@Composable
fun rememberNavigation(initialRoute: Route):Navigation{


    val screenStack = rememberSaveable(initialRoute) {
        ScreenStack(mutableStateListOf(initialRoute))
    }

    return remember (initialRoute) {
        Navigation(route = screenStack, navigationState = screenStack, internalNavigationState = screenStack)
    }
}