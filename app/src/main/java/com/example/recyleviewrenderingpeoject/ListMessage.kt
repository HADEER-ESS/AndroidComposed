package com.example.recyleviewrenderingpeoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class ListMessage :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var messagesListData = listOf<Message>(Message("Liky" , "Hello friend how are you?") , Message("Liky" , "I am free today.")) 
        setContent {
            Surface {
                RenderListMessages(messages = messagesListData)
            }
        }
    }
}

data class Message(val name:String, val content:String)

@Composable
fun RenderListMessages(messages : List<Message>){
    LazyColumn {
        items(messages){
            message ->  RenderCard(message)
        }
    }
}

@Composable
fun RenderCard(data : Message){
    Row {
        Image(painter = painterResource(id = R.drawable.card_image),
            contentDescription = "Chat User Icon",
            Modifier
                .size(50.dp)
                .clip(CircleShape)
                .padding(all = 7.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(text = data.name)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = data.content)
        }
    }
}