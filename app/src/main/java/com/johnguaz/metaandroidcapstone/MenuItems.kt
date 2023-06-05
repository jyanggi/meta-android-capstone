package com.johnguaz.metaandroidcapstone


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.johnguaz.metaandroidcapstone.ui.theme.LittleLemonColor
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun MenuItems(menuItems: List<MenuItemRoom> = listOf()) {
    Column {

        Divider()
        LazyColumn {
            itemsIndexed(menuItems) { _, item ->
                MenuDish(item)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(menuItem: MenuItemRoom) {
    Card(modifier=Modifier.fillMaxWidth().padding(10.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column() {
                Text(text= menuItem.title, style=MaterialTheme.typography.h2)
                Text(text = menuItem.description , style=MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(fraction = .75f)
                        .padding(top = 5.dp, bottom = 5.dp))
                Text(text= "$${menuItem.price}", style=MaterialTheme.typography.body2)
            }
            GlideImage(model=menuItem.image,
                contentDescription = "Dish image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))

            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.cloud
    )
}