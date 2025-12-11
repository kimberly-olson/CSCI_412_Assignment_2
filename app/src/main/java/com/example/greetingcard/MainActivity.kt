package com.example.greetingcard

import android.content.Intent
//import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Kimberly Olson",
                        id = 1432484,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, id: Number, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "My Name is $name! \nMy ID number is $id",
            modifier = modifier
        )
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Start Activity Explicitly")
        }

        Button(onClick = {
            val intent = Intent("com.example.ACTION_VIEW_SECOND")
            context.startActivity(intent)
        }) {
            Text("Start Activity Implicitly")
        }

        Button(onClick = {
            val intent = Intent(context, ThirdActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("View Image Activity")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Kimberly Olson", 1432484)
    }
}