package com.example.main.fish.inject

import com.example.core.fish.FishRepository
import com.example.main.fish.FishViewModel
import dagger.Module
import dagger.Provides

@Module
class FishScreenModule {

    @Module
    companion object {
        @Provides
        @FishScreenScope
        fun provideViewModel(repository: FishRepository): FishViewModel =
            FishViewModel(repository = repository)

    }
}