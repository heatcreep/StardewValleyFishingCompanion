package com.example.core.inject

import android.content.Context

interface DaggerContext {
    val coreComponent: CoreComponent
    val repositoryComponent: RepositoryComponent
}

fun Context.coreComponent(): CoreComponent =
    (applicationContext as DaggerContext).coreComponent

fun Context.repositoryComponent(): RepositoryComponent =
    (applicationContext as DaggerContext).repositoryComponent

