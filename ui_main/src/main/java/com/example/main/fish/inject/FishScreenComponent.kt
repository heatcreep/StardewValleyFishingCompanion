package com.example.main.fish.inject

import com.example.core.fish.FishRepository
import com.example.core.navigation.NavDelegate
import com.example.main.fish.FishViewModel
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent
interface FishScreenComponent {
    val viewModel: FishViewModel
    val navDelegate: NavDelegate
    val fishRepository: FishRepository

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun navigator(navDelegate: NavDelegate): Builder
        fun build(): FishScreenComponent
    }
}