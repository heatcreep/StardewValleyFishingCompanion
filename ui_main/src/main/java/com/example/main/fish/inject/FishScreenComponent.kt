package com.example.main.fish.inject

import com.example.core.inject.CoreComponent
import com.example.main.fish.FishViewModel
import dagger.Component
import dagger.Subcomponent



@Subcomponent
interface FishScreenComponent {
    val viewModel: FishViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): FishScreenComponent
    }
}