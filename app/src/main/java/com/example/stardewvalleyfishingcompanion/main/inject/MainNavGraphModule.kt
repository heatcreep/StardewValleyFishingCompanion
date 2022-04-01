package com.example.stardewvalleyfishingcompanion.main.inject

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.main.MainScreen
import com.example.main.fish.inject.FishScreenComponent
import com.example.main.navigation.MainNavGraphBuilder
import com.example.main.navigation.MainTabNavigator
import com.example.stardewvalleyfishingcompanion.main.fishGraph
import com.example.ui_fishdetails.inject.FishDetailComponent
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
abstract class MainNavGraphModule {

    @ExperimentalAnimationApi
    companion object {
        @Provides
        @Reusable
        fun provideMainNavGraph(
            navController: NavHostController,
            fishScreenComponent: FishScreenComponent.Builder,
            fishDetailComponent: FishDetailComponent.Builder
        ): MainNavGraphBuilder = object : MainNavGraphBuilder {
            override fun build(navGraphBuilder: NavGraphBuilder) = navGraphBuilder.apply {
                val homeNavigator = MainTabNavigator(navController, MainScreen.Fish.route)
                fishGraph(
                    route = MainScreen.Fish.route,
                    fishScreenComponent = fishScreenComponent
                        .navigator(homeNavigator),
                    fishDetailComponent = fishDetailComponent
                        .navigator(homeNavigator)
                )
            }
        }

    }
}