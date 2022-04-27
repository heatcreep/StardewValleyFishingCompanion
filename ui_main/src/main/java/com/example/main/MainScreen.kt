package com.example.main

//import com.example.main.fish.inject.FishScreenComponent
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost

sealed class MainScreen(val route: String, @StringRes val name: Int) {
    object Fish : MainScreen("fish", R.string.fish_screen)
    object Calculator: MainScreen("calculator", R.string.calculator)
}

val screens = listOf(
    MainScreen.Fish,
    MainScreen.Calculator
)


@ExperimentalAnimationApi
@Composable
fun MainScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder.() -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.value?.destination
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
                        label = { Text(stringResource(id = screen.name)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
    ) { innerPadding ->
        AnimatedNavHost(
            navController,
            startDestination = MainScreen.Fish.route,
            Modifier.padding(innerPadding),
            builder = navGraphBuilder,
        )
    }


}