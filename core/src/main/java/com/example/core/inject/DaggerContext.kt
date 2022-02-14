package com.example.core.inject

import android.content.Context

interface DaggerContext {
    val coreComponent: CoreComponent
    val repositoryComponent: RepositoryComponent
}

