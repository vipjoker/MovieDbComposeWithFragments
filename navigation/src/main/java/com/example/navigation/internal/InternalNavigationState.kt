package com.example.navigation.internal

import com.example.navigation.Navigation
import com.example.navigation.Route
import kotlinx.coroutines.flow.Flow

sealed class NavigationEvent {
    data class Removed(val route:Route):NavigationEvent()
}

interface InternalNavigationState{
    fun listen(): Flow<NavigationEvent>
}


