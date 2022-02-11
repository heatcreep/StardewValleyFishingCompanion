package com.example.main.navigation

import androidx.navigation.NavHostController

interface NavDelegate {
    fun goBack()
}

class MainTabNavigator(
    private val navHostController: NavHostController,
    private val route: String
) : NavDelegate {

    override fun goBack() {
        navHostController.popBackStack()
    }
}