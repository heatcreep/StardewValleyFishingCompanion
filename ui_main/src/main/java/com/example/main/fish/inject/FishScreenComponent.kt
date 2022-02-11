package com.example.main.fish.inject

import com.example.main.fish.FishViewModel
import dagger.Subcomponent

@Subcomponent(
    modules = [FishScreenModule::class]
)

@FishScreenScope
interface FishScreenComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FishScreenComponent
    }

    fun getViewModel(): FishViewModel
}