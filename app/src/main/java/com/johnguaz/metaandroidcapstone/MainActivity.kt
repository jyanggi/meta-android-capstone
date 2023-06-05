package com.johnguaz.metaandroidcapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val sharedPreferences by lazy {this.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)}
    private val isLoggedInLiveData = MutableLiveData<Boolean>()
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }
    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val menuItemList:  MenuNetwork = httpClient.get(url).body()
        return menuItemList.menu

    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLoggedInLiveData.value= sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        setContent {
            LittleLemonTheme {
                var isLoggedIn: Boolean by remember { mutableStateOf(false) }
                isLoggedIn = isLoggedInLiveData.value!!
                Navigation(isLoggedIn)
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems: List<MenuItemNetwork> = fetchMenu()
                saveMenuToDatabase(menuItems)
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
