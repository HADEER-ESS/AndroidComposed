package com.example.recyleviewrenderingpeoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class ListMessage :  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var messagesListData = listOf<Message>(Message("Liky" , "Hello friend how are you?") ,
            Message("Nadir" , "We are good fine"),
            Message("Liky" , "What is Lorem Ipsum?\n") ,
            Message("Nadir" , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.") )
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
    //RECOMPOSITION
    //happen when composable re-invoke with different function parameter, or when internal state of function changes
    //(when function be reusable with different parameter)
    Column {
        messages.forEach{
            message ->  RenderCard(data = (message))
        }
    }
//    LazyColumn {
//        items(messages){
//            message ->  RenderCard(message)
//        }
//    }
}

@Composable
fun RenderCard(data : Message){
    //Remember => to store local state in memory, track changes value that passed to mutableStateOf
    //MutableStateOf => integrate within composed run-time, any change in the state will recomposition the composable function.

    //remember / rememberSaveable / mutableStateOf -> are used track composed state and recompose the component if any change in state happen

    //remember will track changes that will occurred on mutableStateOf
    //remember  / rememberSaveable
//    var isExpand by remember { mutableStateOf(false) }
    var isExpand :MutableState<Boolean?> = remember {
        mutableStateOf(false)
    }
    val surfaceColor by animateColorAsState(targetValue =
    //    if (isExpand) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
    if (isExpand.value==true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
    )

    Row (horizontalArrangement = Arrangement.SpaceBetween){
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
            Surface(color = surfaceColor,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.clickable { isExpand.value = !isExpand.value!!  })
            {
                Text(text = data.content ,
                    maxLines = if(isExpand.value==true) Int.MAX_VALUE else 1,
                    modifier = Modifier.padding(all = 7.dp))

            }
        }
    }
}