package com.johnguaz.metaandroidcapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.MutableLiveData
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    private val sharedPreferences by lazy {this.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)}
    private val isLoggedInLiveData = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        isLoggedInLiveData.value= sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                Navigation(isLoggedInLiveData)
            }
        }
    }

    companion object{
        const val SHARED_PREF_NAME = "LittleLemonCapstone"
        const val IS_LOGGED_IN = "isLoggedIn"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
    }
}
