package com.example.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.main.fish.FishScreen
import com.example.main.fish.FishViewModel
import com.example.main.fish.inject.FishScreenComponent
//import com.example.main.fish.inject.FishScreenComponent
import daggerViewModel


@ExperimentalAnimationApi
@Composable
fun MainScreen (fishScreenBuilder:FishScreenComponent.Builder) {
    Scaffold(
        content = { FishScreen(fishScreenBuilder) }
    )
}