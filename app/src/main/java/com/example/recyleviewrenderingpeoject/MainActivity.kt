package com.example.recyleviewrenderingpeoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Simple composed
//            Text(text = "Hello world!")

            //Create a compasable function to display user name on screen
//            DisplayUserName("Hadeer")
            DisplayAuthorData(MessageAuthor("Ali" , "Sprite away"))
        }
    }
}

data class MessageAuthor(val author:String, val message:String)

@Composable
fun DisplayAuthorData(data : MessageAuthor){
    Row (modifier = Modifier.padding(all = 8.dp).fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.card_image),
            contentDescription = "Author Book Image",
            modifier = Modifier
                .size(50.dp)
                .padding(all = 5.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column{
            Text(text = data.author , color = Color.Magenta)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data.message , color = Color.Magenta)
        }
    }


}

@Preview
@Composable
fun AuthorDataPreview(){
    DisplayAuthorData(data = MessageAuthor("Said" , "Tell me the truth"))
}
//
//@Composable
//fun DisplayUserName(name:String){
//    Text(text = "Hello, $name" , color = Color.Blue)
//}
//
////Preview annotation don't have param, so we can't preview the previous function as it takes a string param.
//@Preview
//@Composable
//fun PreviewDisplayNameFun(){
//    DisplayUserName(name = "Hadeer")
//}

/* Build-in code...
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecyleViewRenderingPeojectTheme {
        Greeting("Android")
    }
}

            RecyleViewRenderingPeojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.alpha(1f),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
 */