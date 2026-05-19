package com.example.lapangin_booksportapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lapangin_booksportapp.ui.screens.HomeScreen
import com.example.lapangin_booksportapp.ui.screens.LoginScreen
import com.example.lapangin_booksportapp.ui.screens.LogoScreen
import com.example.lapangin_booksportapp.ui.screens.RegisterScreen
import com.example.lapangin_booksportapp.ui.screens.VenueDetailScreen
import com.example.lapangin_booksportapp.ui.screens.BookingFormScreen
import com.example.lapangin_booksportapp.ui.screens.BookingConfirmationScreen
import com.example.lapangin_booksportapp.ui.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Logo : Screen("logo")
    object Register : Screen("register")
    object Login : Screen("login")
    object Home : Screen("home")
    object VenueDetail : Screen("venue_detail")
    object BookingForm : Screen("booking_form")
    object BookingConfirmation : Screen("booking_confirmation")
}

@Composable
fun BookSportNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // SPLASH
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Logo.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // LOGO
        composable(Screen.Logo.route) {
            LogoScreen(
                onGetStarted = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        // REGISTER
        composable(Screen.Register.route) {
            RegisterScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Logo.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Logo.route)
                    }
                }
            )
        }

        // LOGIN
        composable(Screen.Login.route) {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Logo.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Logo.route)
                    }
                }
            )
        }

        // HOME
        composable(Screen.Home.route) {
            HomeScreen(
                onVenueClick = {
                    navController.navigate(Screen.VenueDetail.route)
                }
            )
        }

        //  VENUE DETAIL
        composable(Screen.VenueDetail.route) {
            VenueDetailScreen(
                venueName = "GOR Manyar",
                location = "Sukolilo",
                pricePerSlot = 80000,
                onBackClick = {
                    navController.popBackStack()
                },
                onBookClick = { _ ->
                    navController.navigate(Screen.BookingForm.route)
                }
            )
        }

        // BOOKING FORM
        composable(Screen.BookingForm.route) {
            BookingFormScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSubmitClick = {
                    navController.navigate(Screen.BookingConfirmation.route)
                }
            )
        }

        // BOOKING CONFIRMATION
        composable(Screen.BookingConfirmation.route) {
            BookingConfirmationScreen(
                venueName = "GOR Manyar",
                userName = "Admin",
                date = "26 Apr 2025",
                time = "19:00 - 21:00",
                total = "Rp160.000",
                onBackToHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}