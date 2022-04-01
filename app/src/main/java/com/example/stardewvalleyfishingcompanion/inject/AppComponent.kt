package com.example.stardewvalleyfishingcompanion.inject

import com.example.core.inject.CoreComponent
import com.example.core.inject.RepositoryComponent
import com.example.stardewvalleyfishingcompanion.main.inject.MainScreenComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        CoreComponent::class,
        RepositoryComponent::class
    ]
)

interface AppComponent {

    interface Holder {
        val appComponent: AppComponent
    }

    val mainScreenComponent: MainScreenComponent.Builder

}