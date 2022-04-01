package com.example.stardewvalleyfishingcompanion.main.inject

import androidx.navigation.NavHostController
import com.example.main.fish.inject.FishScreenComponent
import com.example.main.navigation.MainNavGraphBuilder
import com.example.ui_fishdetails.inject.FishDetailComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(
    modules = [MainNavGraphModule::class]
)
interface MainScreenComponent {
    val navGraphBuilder: MainNavGraphBuilder
    val fishScreenComponent: FishScreenComponent.Builder
    val fishDetailComponent: FishDetailComponent.Builder

    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun navHostController(navHostController: NavHostController) : Builder

        fun build(): MainScreenComponent
    }
}