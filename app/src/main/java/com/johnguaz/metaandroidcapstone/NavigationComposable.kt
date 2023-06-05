package com.johnguaz.metaandroidcapstone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(isLoggedInLiveData: MutableLiveData<Boolean>) {
    val navController = rememberNavController()
    val isLoggedIn = isLoggedInLiveData.observeAsState(false)
    NavHost(navController = navController, startDestination = (if(isLoggedIn.value)  Home.route else  Onboarding.route)) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(navController)
        }

    }
}
