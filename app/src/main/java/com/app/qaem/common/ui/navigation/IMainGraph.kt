package com.app.qaem.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

import kotlinx.serialization.Serializable

sealed interface IMainGraph : IDestination {
    @Serializable
    data object RootGraph : IGraph

    @Serializable
    data object Home : IMainGraph

}

fun NavGraphBuilder.buildNavMainGraph() {
    navigation<IMainGraph.RootGraph>(startDestination = IMainGraph.Home) {
        composable<IMainGraph.Home> { }
    }
}