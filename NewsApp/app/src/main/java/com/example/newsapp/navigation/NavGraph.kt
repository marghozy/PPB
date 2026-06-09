package com.example.newsapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.newsapp.ui.screens.DetailScreen
import com.example.newsapp.ui.screens.HomeScreen

@Composable
fun NavGraph() {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(navController)

        }

        composable(
            route = "detail/{title}/{imageUrl}/{description}"
        ) { backStackEntry ->

            DetailScreen(

                title = Uri.decode(
                    backStackEntry.arguments
                        ?.getString("title")
                        ?: ""
                ),

                imageUrl = Uri.decode(
                    backStackEntry.arguments
                        ?.getString("imageUrl")
                        ?: ""
                ),

                description = Uri.decode(
                    backStackEntry.arguments
                        ?.getString("description")
                        ?: ""
                )

            )

        }

    }
}