package com.example.playarena.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playarena.ui.splash.SplashScreen
import com.example.playarena.ui.login.LoginScreen
import com.example.playarena.ui.login.RegisterScreen
import com.example.playarena.ui.home.HomeScreen
import com.example.playarena.ui.member.AddMemberScreen
import com.example.playarena.ui.member.MemberCardScreen
import com.example.playarena.ui.reward.RewardScreen
import com.example.playarena.ui.session.AddSessionScreen
import com.example.playarena.ui.session.SessionHistoryScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.AddMember.route) {
            AddMemberScreen(navController)
        }

        composable(Screen.MemberCard.route) {
            MemberCardScreen(navController)
        }

        composable(Screen.AddSession.route) {
            AddSessionScreen(navController)
        }

        composable(Screen.SessionHistory.route) {
            SessionHistoryScreen(navController)
        }

        composable(Screen.Reward.route) {
            RewardScreen(navController)
        }
    }
}