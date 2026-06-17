package com.example.playarena.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Login : Screen("login")

    object Register : Screen("register")

    object Home : Screen("home")

    object AddMember : Screen("add_member")

    object MemberCard : Screen("member_card")

    object AddSession : Screen("add_session")

    object SessionHistory : Screen("session_history")

    object Reward : Screen("reward")
}