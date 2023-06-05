package com.johnguaz.metaandroidcapstone

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(isLogggedIn: Boolean = false) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =(if(isLogggedIn)  Home.route else  Onboarding.route) ) {

        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController)
            BackHandler(true) {
                //prevent back
            }
        }
        composable(Profile.route) {
            Profile(navController)
        }

    }
}
