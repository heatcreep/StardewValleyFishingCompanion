package com.example.main.navigation

import androidx.navigation.NavHostController
import com.example.core.navigation.NavDelegate

class MainTabNavigator(
    private val navHostController: NavHostController,
    private val route: String
) : NavDelegate {

    override fun goBack() {
        navHostController.popBackStack()
    }

    override fun goToFishDetails(fishId: Int) {
        navHostController.navigate("$route/fish-detail/${fishId}")
    }
}