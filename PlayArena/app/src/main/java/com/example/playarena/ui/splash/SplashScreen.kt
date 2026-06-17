package com.example.playarena.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.playarena.navigation.Screen
import com.example.playarena.ui.components.PlayArenaLogo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {

        delay(2500)

        navController.navigate(
            Screen.Login.route
        ) {
            popUpTo(
                Screen.Splash.route
            ) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        PlayArenaLogo()

    }
}