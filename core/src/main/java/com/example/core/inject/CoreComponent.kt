package com.example.core.inject

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@Component
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(applicationContext: Application): Builder

        fun build(): CoreComponent
    }
}