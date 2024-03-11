package com.example.recyleviewrenderingpeoject

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyleviewrenderingpeoject.ui.theme.RecyleViewRenderingPeojectTheme

class MaterialDesign : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //To use Material design must use ComposeTutorialMaterial
            RecyleViewRenderingPeojectTheme{
                Surface(shape = MaterialTheme.shapes.medium , modifier = Modifier.fillMaxWidth().padding(all = 15.dp).shadow(5.dp)) {
                    MessageCard(message = MessageData( "Loice" , "Steve" , "I had a great weekend with my friends"))

                }
            }
        }
    }
}

data class MessageData(val from:String, val to :String , val content:String)

@Composable
fun MessageCard(message : MessageData){
    Row (modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
        .padding(all = 7.dp)
        .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_image),
            contentDescription = "Card Image",
            modifier = Modifier
                .size(50.dp)
                .padding(all = 5.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column{
            Text(text = "From : ${message.from}" )
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = message.content)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Hope you to be well :\t ${message.to}")
        }
    }
}

@Preview
@Composable
fun MessageCardPreview(){
    RecyleViewRenderingPeojectTheme {
        Surface (modifier = Modifier.fillMaxSize()) {
            MessageCard(message = MessageData( "Loice" , "Steve" , "I had a great weekend with my friends"))

        }
    }
}