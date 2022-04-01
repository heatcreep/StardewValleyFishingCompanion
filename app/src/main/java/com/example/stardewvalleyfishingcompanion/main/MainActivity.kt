package com.example.stardewvalleyfishingcompanion.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.example.main.MainScreen
import com.example.stardewvalleyfishingcompanion.appComponent
import com.example.stardewvalleyfishingcompanion.ui.theme.StardewValleyFishingCompanionTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val appComponent = appComponent()
        setContent {
            StardewValleyFishingCompanionTheme {
                Surface {
                    val navController = rememberAnimatedNavController()
                    val component = remember {
                        appComponent.mainScreenComponent
                            .navHostController(navController)
                            .build()
                    }
                    MainScreen(navController) {
                        component.navGraphBuilder.build(this)
                    }
                }
            }


        }
    }
}