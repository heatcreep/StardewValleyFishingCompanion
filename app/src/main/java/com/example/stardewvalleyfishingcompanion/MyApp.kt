package com.example.stardewvalleyfishingcompanion

import android.app.Application
import android.content.Context
import com.example.core.inject.CoreComponent
import com.example.core.inject.DaggerContext
import com.example.core.inject.RepositoryComponent
import com.example.stardewvalleyfishingcompanion.inject.AppComponent
import com.example.stardewvalleyfishingcompanion.inject.DaggerComponentHolder

class MyApp : Application(), DaggerContext, AppComponent.Holder {

    lateinit var daggerComponentHolder: DaggerComponentHolder

    override val coreComponent: CoreComponent
        get() = daggerComponentHolder
    override val repositoryComponent: RepositoryComponent
        get() = daggerComponentHolder

    override val appComponent: AppComponent
        get() = daggerComponentHolder.appComponent

    override fun onCreate() {
        super.onCreate()
        daggerComponentHolder = DaggerComponentHolder(this)
    }

}

fun Context.appComponent() = (applicationContext as AppComponent.Holder).appComponent