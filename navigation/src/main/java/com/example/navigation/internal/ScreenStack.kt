package com.example.navigation.internal

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.os.ParcelCompat
import com.example.navigation.NavigationState
import com.example.navigation.Route
import com.example.navigation.Router
import com.example.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class ScreenStack (
    private val routes:SnapshotStateList<Route>):NavigationState, Router, Parcelable ,InternalNavigationState{
    override val isRoot: Boolean
        get() = routes.size == 1
    override val currentRoute: Route
        get() = routes.last()

    private val eventsFlow: MutableSharedFlow<NavigationEvent> = MutableSharedFlow(extraBufferCapacity = Int.MAX_VALUE)

    override val currentScreen:Screen by derivedStateOf {
        currentRoute.screenProducer()
    }
    constructor(parcel: Parcel) : this(SnapshotStateList<Route>().also{
        newlist->
        ParcelCompat.readList(parcel,newlist,Route::class.java.classLoader,Route::class.java)
    }) {

    }


    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        val removedRoute = routes.removeLastOrNull()
        if(removedRoute != null){
            eventsFlow.tryEmit(NavigationEvent.Removed(removedRoute))
        }

    }

    override fun restart(route: Route) {
        routes.apply {
            routes.forEach {
                eventsFlow.tryEmit(NavigationEvent.Removed(it))
            }

            clear()
            add(route)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(routes)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun listen(): Flow<NavigationEvent> {
       return eventsFlow
    }

    companion object CREATOR : Parcelable.Creator<ScreenStack> {
        override fun createFromParcel(parcel: Parcel): ScreenStack {
            return ScreenStack(parcel)
        }

        override fun newArray(size: Int): Array<ScreenStack?> {
            return arrayOfNulls(size)
        }
    }
}