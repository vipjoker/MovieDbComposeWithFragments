package com.example.navigation.internal

import com.example.navigation.Route
import com.example.navigation.Router

internal object EmptyRouter:Router {
    override fun launch(route: Route) {
    }

    override fun pop() {
    }

    override fun restart(route: Route) {
    }
}