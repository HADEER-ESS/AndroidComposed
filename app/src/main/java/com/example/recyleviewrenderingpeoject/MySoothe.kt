package com.example.recyleviewrenderingpeoject

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.recyleviewrenderingpeoject.ui.theme.RecyleViewRenderingPeojectTheme

class MySoothe :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyleViewRenderingPeojectTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onPrimary)
                ){
                    ApplicationProtrait(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun ApplicationProtrait(modifier: Modifier = Modifier){
    Scaffold(
        bottomBar = { BottomNavigationSection()},
        modifier = modifier
    ){
        padding ->  AppView(modifier = Modifier.padding(padding))
    }
}

data class RowData(val image:Int , val title :String)

@Composable
fun AppView(modifier: Modifier = Modifier){

    val rowContentData : List<RowData> = listOf(
        RowData(R.drawable.card_image, "Inversions"),
        RowData(R.drawable.anya_image, "Quick Yoga"),
        RowData(R.drawable.cat_image, "Stretching"),
        RowData(R.drawable.como_image, "Tabata"),
        RowData(R.drawable.gojo_image, "HIIT"),
        RowData(R.drawable.half_image, "Pre-natal Yuga"),
        RowData(R.drawable.pink_image, "Bicycle"),
    )

    //Scaffold is a basic layout for arranging material components (has TopBar / Content / BottomBar
    //Surface is using for arranging layout (has color / shape / border / shadow / tonal)

    //Lazy layout support scrolling, but normal Row, and Column don't have scroll behavior,
    //    and need to add it manually
    Column(
        modifier.verticalScroll(rememberScrollState())
    ){
        SearchBar(modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        ContainerSection(title = "Align Your Body", modifier= Modifier.fillMaxWidth()){
            AlignYouBodyRow(listData = rowContentData,
                modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth())
        }
        Spacer(modifier = Modifier.height(12.dp))
        ContainerSection(title = "Favorite Collections") {
            FavoriteCollectionGrid(rowContentData ,
                modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth())
        }
//        BottomNavigationSection(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier){
        TextField(
            value = "",
            onValueChange ={} ,
            leadingIcon = {
                          Icon(imageVector = Icons.Filled.Search,
                              contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                          Text(text = "Search")
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(horizontal = 19.dp)
        )
}

@Composable
fun ContainerSection(
    title: String,
    modifier: Modifier = Modifier,
    content : @Composable () -> Unit
){

    Column(
        modifier=modifier
    ){
        Text(
            text = title ,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 19.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()

    }
}

@Composable
fun AlignYouBodyRow(listData: List<RowData>, modifier: Modifier = Modifier){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 18.dp),
    ){
        items(listData) {
                item -> AlignYouBody(item)
        }
    }
}

@Composable
fun AlignYouBody(data: RowData, modifier: Modifier = Modifier ){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = data.image),
            contentDescription = "Vertical Card Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = data.title,
            modifier = Modifier.paddingFromBaseline(top = 5.dp , bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
            )
    }
}

@Composable
fun FavoriteCollectionGrid(listData : List<RowData>, modifier: Modifier=Modifier){

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2) ,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(125.dp)
    ) {
        items(listData){
            item -> FavoriteCollection(item)
        }
    }
}

@Composable
fun FavoriteCollection(data:RowData, modifier: Modifier = Modifier){

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(end = 8.dp)
                .width(200.dp)
        ){
            Image(
                painter = painterResource(id = data.image),
                contentDescription ="Horizontal card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 9.dp)
            )
            Text(
                text = data.title,
            )
        }
    }

}

@Composable
fun BottomNavigationSection(modifier: Modifier = Modifier){
//    Navigation-bar => make the navigation at the bottom even user rotate the device
                // has Navigation-bar-item
    //Navigation-rail => provide navigation at the bottom and when user rotate the devies
    // it becomes in the side
                //has Navigation-rail-item
    NavigationBar(
        modifier = modifier,
    ){
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon"
                )
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon"
                )
            },
            label = {
                Text(text = "Profile")
            }
        )
    }
}
@Preview(
    showBackground = true,
    widthDp = 500,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "PreviewAppViewDark"
)
//@Preview(showBackground = true , backgroundColor = 0xFFFFFFFF , widthDp = 500)
@Composable
fun PreviewAppView(){
    RecyleViewRenderingPeojectTheme {
            AppView()
    }
}