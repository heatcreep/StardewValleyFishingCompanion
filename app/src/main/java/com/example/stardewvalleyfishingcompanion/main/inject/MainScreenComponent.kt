package com.example.stardewvalleyfishingcompanion.main.inject

import com.example.main.fish.inject.FishScreenComponent
import dagger.Subcomponent


@Subcomponent
interface MainScreenComponent {
    val fishScreenComponent: FishScreenComponent.Builder

    @Subcomponent.Builder
    interface Builder{
        fun build(): MainScreenComponent
    }
}