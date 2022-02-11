package com.example.stardewvalleyfishingcompanion.inject

import com.example.core.inject.CoreComponent
import com.example.core.inject.RepositoryComponent
import com.example.main.fish.inject.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        CoreComponent::class,
        RepositoryComponent::class
    ]
)

interface AppComponent {

    interface Holder {
        val appComponent: AppComponent
    }
}