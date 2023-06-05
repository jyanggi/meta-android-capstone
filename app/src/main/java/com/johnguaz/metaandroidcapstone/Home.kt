package com.johnguaz.metaandroidcapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.johnguaz.metaandroidcapstone.ui.theme.*


@Composable
fun Home(navController: NavHostController?) {
    val context = LocalContext.current
    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var menuItems = databaseMenuItems
    var searchPhrase by remember { mutableStateOf("") }
    val categories = remember { mutableStateListOf<String>() }
    val focusManager = LocalFocusManager.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)){
    TopAppBar(navController = navController, isHome = true)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LittleLemonColor.green)
            .padding(bottom=20.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow,
            fontFamily = Markazi,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column( modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.6f)){
                Text(
                    text = "Chicago",
                    fontSize = 24.sp,
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)

                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start=20.dp, end=20.dp, top=10.dp),
                    color = LittleLemonColor.cloud
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Upper Panel Image",
                modifier = Modifier
                    .size(190.dp)
                    .clip(Shapes.medium)
                    .padding(15.dp)

            )
        }

        TextField (  value=searchPhrase, onValueChange = { searchPhrase = it },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = LittleLemonColor.cloud),
            label = { Text("Enter search phrase") },
            leadingIcon = { Icon(Icons.Default.Search,  "") },
                    modifier = Modifier.align( alignment= Alignment.CenterHorizontally)
                .fillMaxWidth(.85f)
                .clip(Shapes.large)
            ,

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            )

        )
        if(searchPhrase.isNotEmpty()){
            menuItems = menuItems.filter{item -> item.title.contains(searchPhrase, ignoreCase = true)}
        }

    }

        Column(
            modifier=Modifier.fillMaxWidth().padding(5.dp)
        ) {
            Text(
                text = "Order for delivery",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(5.dp)
            )
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                    setOf("starters", "mains", "desserts", "drinks").forEach { category ->
                        CategoryButton(text=category.replaceFirstChar { it.uppercase() }, onClick={
                            if(categories.contains(category)){
                                categories.remove(category)
                            }else{
                                categories.add(category)
                            }
                        }, selected=categories.contains(category))
                    }

            }
        }
        if(categories.isNotEmpty()){
            menuItems = menuItems.filter{item -> categories.contains(item.category)}
            println(categories)
        }
        MenuItems(menuItems = menuItems)

    }
}



@Composable
fun CategoryButton(text:String, onClick: () -> Unit, selected:Boolean = false) {
    Button(
        onClick =onClick,
        modifier = Modifier.padding(2.dp).clip(Shapes.medium),
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = if(selected) LittleLemonColor.green else LittleLemonColor.cloud)
    )
     {
    Text( fontSize = 12.sp, text=text, style = Typography.body1, color=if(selected) LittleLemonColor.cloud else LittleLemonColor.charcoal)
    }
}

@Preview
@Composable
fun HomePreview(){
    LittleLemonTheme() {
        Home(navController = null)
    }
}