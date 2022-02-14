package com.example.stardewvalleyfishingcompanion.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.example.main.MainScreen
import com.example.stardewvalleyfishingcompanion.appComponent
import com.example.stardewvalleyfishingcompanion.ui.theme.StardewValleyFishingCompanionTheme

@ExperimentalAnimationApi
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val appComponent = appComponent()
        setContent {
            val component = remember {
                appComponent.mainScreenComponent.build()
            }
            StardewValleyFishingCompanionTheme {
                MainScreen(component.fishScreenComponent)
            }
        }
    }
}