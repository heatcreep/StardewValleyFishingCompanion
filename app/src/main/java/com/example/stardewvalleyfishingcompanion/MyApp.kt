package com.example.stardewvalleyfishingcompanion

import android.app.Application
import android.content.Context
import com.example.stardewvalleyfishingcompanion.inject.AppComponent
import com.example.stardewvalleyfishingcompanion.inject.DaggerComponentHolder

class MyApp : Application(), AppComponent.Holder {

    lateinit var daggerComponentHolder: DaggerComponentHolder

    override val appComponent: AppComponent
        get() = daggerComponentHolder.appComponent

    override fun onCreate() {
        super.onCreate()
        daggerComponentHolder = DaggerComponentHolder(this)
    }

}

fun Context.appComponent() = (applicationContext as AppComponent.Holder).appComponent