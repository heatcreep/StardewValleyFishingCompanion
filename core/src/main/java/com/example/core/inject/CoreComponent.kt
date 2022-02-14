package com.example.core.inject

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface CoreComponent {
    val applicationContext: Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(applicationContext: Application): Builder

        fun build(): CoreComponent
    }
}