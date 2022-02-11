package com.example.fish.inject

import com.example.core.inject.CoreComponent
import com.example.core.inject.RepositoryComponent
import com.example.fish.FishRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FishModule::class
    ],
    dependencies = [
        CoreComponent::class,
        RepositoryComponent::class
    ]
)

interface FishComponent {
    val repositoryImpl: FishRepositoryImpl
}