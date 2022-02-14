package com.example.stardewvalleyfishingcompanion.inject

import android.app.Application
import com.example.core.inject.CoreComponent
import com.example.core.inject.DaggerCoreComponent
import com.example.core.inject.RepositoryComponent
import com.example.core.fish.FishRepository
import com.example.fish.inject.DaggerFishComponent
import com.example.fish.inject.FishComponent

class DaggerComponentHolder(
    private val coreComponent: CoreComponent
) : CoreComponent by coreComponent, RepositoryComponent{

    constructor(context: Application): this(
        DaggerCoreComponent.builder().application(context).build()
    )

    private val fishComponent: FishComponent =
        DaggerFishComponent.builder()
            .coreComponent(coreComponent)
            .repositoryComponent(this)
            .build()

    override val fishRepository: FishRepository
        get() = fishComponent.repositoryImpl

    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .coreComponent(coreComponent)
            .repositoryComponent(this)
            .build()
}