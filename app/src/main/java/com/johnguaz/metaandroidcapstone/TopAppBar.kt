package com.johnguaz.metaandroidcapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopAppBar(navController: NavHostController?, isHome: Boolean=false) {
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.fillMaxWidth(.7f)
                .size(50.dp)
                .padding(horizontal = 10.dp)
        )

        if(isHome){
            IconButton(onClick = {navController?.navigate(Profile.route) }, modifier = Modifier.fillMaxWidth(.3f).size(80.dp) ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar(null, isHome = true)
}