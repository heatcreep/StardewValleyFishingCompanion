package com.example.stardewvalleyfishingcompanion.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.example.main.fish.FishScreen
import com.example.main.fish.inject.FishScreenComponent
import com.example.ui_fishdetails.FishDetailScreen
import com.example.ui_fishdetails.inject.FishDetailComponent
import com.google.accompanist.navigation.animation.composable

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.fishGraph(
    route: String,
    fishScreenComponent: FishScreenComponent.Builder,
    fishDetailComponent: FishDetailComponent.Builder
) {
    composable(
        route,
        enterTransition = { fadeIn(animationSpec = tween(5000)) }
    ) {
        FishScreen(componentBuilder = fishScreenComponent)
    }
    composable(
        "$route/fish-detail/{fishId}",
        enterTransition = {
            slideInHorizontally()
        }
    ) {
        val fishId = it.arguments?.getString("fishId")!!
        FishDetailScreen(fishDetailComponent.fishId(fishId.toInt()))
    }

}