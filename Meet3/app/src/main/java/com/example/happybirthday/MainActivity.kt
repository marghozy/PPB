package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GreetingText(
                message = "Happy Birthday Sam!",
                from = "From Emma"
            )
        }

    }

}

@Composable
fun GreetingText(message: String, from: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = message,
            fontSize = 100.sp
        )

        Text(
            text = from,
            fontSize = 36.sp
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingText(
        message = "Happy Birthday Sam!",
        from = "From Emma"
    )
}