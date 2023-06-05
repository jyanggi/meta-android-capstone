package com.johnguaz.metaandroidcapstone

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonColor
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonTheme
import com.johnguaz.metaandroidcapstone.ui.theme.Shapes


@Composable
fun Profile(navController: NavHostController?) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE)
    val firstName = sharedPref.getString(MainActivity.FIRST_NAME,"").orEmpty()
    val lastName = sharedPref.getString(MainActivity.LAST_NAME,"").orEmpty()
    val email = sharedPref.getString(MainActivity.EMAIL,"").orEmpty()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)){
        TopAppBar(navController=navController)
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
        ){
            Text(
                text = "Personal Information",
                style = MaterialTheme.typography.h2,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
            Text(
                text = "First name",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start,
                )
            Text(text=firstName,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium)
                    .padding(10.dp)
                )
            Text(
                text = "Last name",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start,
            )
            Text(text=lastName,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium)
                    .padding(10.dp)
                )
            Text(
                text = "Email",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start
            )
            Text(text=email,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium)
                    .padding(10.dp)


            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow,
                    contentColor = LittleLemonColor.charcoal),
                onClick = {
                        sharedPref.edit()
                            .clear()
                            .commit()
                        navController?.navigate(Onboarding.route)
                }
            ) {
                Text(
                    text = "Log out",
                    style = MaterialTheme.typography.body1,
                    color = LittleLemonColor.charcoal,
                    textAlign = TextAlign.Center
                )
            }


        }

    }

}

@Preview
@Composable
fun ProfilePreview() {
    LittleLemonTheme() {
        Profile(navController=null)
    }
}


