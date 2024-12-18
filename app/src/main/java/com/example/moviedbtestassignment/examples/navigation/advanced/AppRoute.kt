package com.example.moviedbtestassignment.examples.navigation.advanced

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.moviedbtestassignment.R
import com.example.navigation.Route
import kotlinx.parcelize.Parcelize


sealed class AppRoute(@StringRes val titleRes: Int = 0):Route {
    @Parcelize
    data object AddItem : AppRoute(R.string.add_item)

    sealed class Tab(@StringRes titleRes: Int, val icon: ImageVector) : AppRoute(titleRes) {
        @Parcelize
        data object Items : Tab(R.string.items, Icons.Default.List)
        @Parcelize
        data object Settings : Tab(R.string.settings, Icons.Default.Settings)
        @Parcelize
        data object Profile : Tab(R.string.profile, Icons.Default.AccountBox)
    }

}