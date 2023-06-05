package com.johnguaz.metaandroidcapstone

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonColor
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonTheme
import com.johnguaz.metaandroidcapstone.ui.theme.Shapes


@Composable
fun Onboarding(navController: NavHostController?) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.White)){
        TopAppBar(navController = navController)
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .size(150.dp)
        ) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.h1,
                color = LittleLemonColor.cloud,
                textAlign = TextAlign.Center

                )
        }
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
            val focusManager = LocalFocusManager.current
            var firstName by remember { mutableStateOf("") }
            Text(
                text = "First name",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start
            )
            TextField(
                value = firstName, onValueChange = { firstName = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            var lastName by remember { mutableStateOf("") }
            Text(
                text = "Last name",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start,
            )
            TextField(
                value = lastName, onValueChange = { lastName = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            var email by remember { mutableStateOf("") }
            Text(
                text = "Email",
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.charcoal,
                textAlign = TextAlign.Start
            )
            TextField(
                value = email, onValueChange = { email = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.medium)
                    .border(width = 1.dp, color = LittleLemonColor.cloud, shape = Shapes.medium),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                )
            )
                    Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow,
                        contentColor = LittleLemonColor.charcoal),
                    onClick = {
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                            Toast.makeText(context,"Registration unsuccessful. Please enter all data.",Toast.LENGTH_SHORT).show()
                        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT)
                                .show()
                        }else{
                            val sharedPref = context.getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE)
                            sharedPref.edit()
                                .putString(MainActivity.FIRST_NAME, firstName)
                                .putString(MainActivity.LAST_NAME, lastName)
                                .putString(MainActivity.EMAIL, email)
                                .putBoolean(MainActivity.IS_LOGGED_IN, true)
                                .commit()
                            navController?.navigate(Home.route)
                        }
                    }
                ) {
                    Text(
                        text = "Register",
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
fun OnboardingPreview() {
    LittleLemonTheme() {
        Onboarding(navController=null)
    }
}


