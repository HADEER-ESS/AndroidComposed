package com.example.recyleviewrenderingpeoject

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.compose.ui.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyleviewrenderingpeoject.ui.theme.RecyleViewRenderingPeojectTheme

class OnboardingProjectScreen :ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyleViewRenderingPeojectTheme{
                Surface (modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background){

//                    Greeting("Android")
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    onButtonClicked : () -> Unit,
    modifier: Modifier = Modifier
){

    Column (
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome To The Basic CodeLab", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = modifier.height(7.dp))
        ElevatedButton(
            onClick =  onButtonClicked,
            modifier= Modifier.padding(all = 8.dp),
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun MyApp(modifier: Modifier =Modifier){

    //State Hoisting ==> state that is read or modified by multiple functions should live in a common ancestor
    //void duplicate state, The source of truth belongs to whoever creates and controls that state.
    //use (by) with remember instead of using (=), this feature save me from typing ((.value)) each time to access the state

    //we used the (by) property to avoid using value every time.
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    //rememberSaveable => persist the state while any configuration change happen (as rotate phone || process death)

//    val names : List<String> = listOf("Said" , "Sally" , "John" , "Steve" , "Ali")
    val names : List<String> = List(1000){"$it"}

    Surface(
        modifier=modifier,
        color = MaterialTheme.colorScheme.background
    ){
//        Column(modifier){
//            for (name in names){
//                Greeting(name = name)
//            }
//        }
        if(shouldShowOnBoarding){
            OnBoardingScreen(onButtonClicked = {shouldShowOnBoarding = false})
        }else{
            LazyColumn(modifier= Modifier.padding(vertical = 4.dp)){
                items(names){
                        name -> Greeting(name = name)
                }
            }
        }
    }
}

@Composable
fun Greeting(name : String, modifier: Modifier = Modifier){

    //remember is used to guard against recomposition, so the state is not reset.
    //each item in screen has its own version of state

//    val expanded  = remember { mutableStateOf(false) }

    //rememberSaveable => save the state while scrolling,
    //when expand the item, then scroll down and return back it remains expanded.
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

//    val extraPadding = if(expanded) 22.dp else 0.dp

    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 44.dp else 0.dp,
        spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    ){
        Row (modifier.padding(all = 17.dp)){
            Column(
                modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))){
                Text(
                    text = "Hello, ",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.headlineMedium
                )
                //to modify a predefine style use (copy) method
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

//            ElevatedButton(
//                onClick = {expanded = !expanded },
//                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
//                ) {
//                Text(text = if (!expanded) "Show More" else "Show Less")
//            }
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription ="Expand icon"
                )
            }
        }

    }

//    Column (modifier = Modifier.padding(all = 24.dp)){
//        Text(text = "Hello,")
//        Text(text = name)
//    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 640,
    uiMode = UI_MODE_NIGHT_YES,
    name = "SimplePreviewDark"
)
//@Preview(showBackground = true, widthDp = 320 , heightDp = 640)
@Composable
fun SimplePreviewFunction(){
    RecyleViewRenderingPeojectTheme{
            MyApp()
//        OnBoardingScreen()
    }
}