package com.example.core.inject

import com.example.core.fish.FishRepository

interface RepositoryComponent {
    val fishRepository: FishRepository
}