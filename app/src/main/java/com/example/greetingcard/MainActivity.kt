package com.example.greetingcard

import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private val PERMISSION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                this,
                "com.example.greetingcard.MSE412"
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf("com.example.greetingcard.MSE412"),
                PERMISSION_REQUEST_CODE
            )
        }

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied. Cannot open SecondActivity.", Toast.LENGTH_SHORT).show()
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
            if (ContextCompat.checkSelfPermission(
                    context,
                    "com.example.greetingcard.MSE412"
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            }
            else {
                Toast.makeText(context, "Permission not granted!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Start Activity Explicitly")
        }

        Button(onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    "com.example.greetingcard.MSE412"
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent("com.example.ACTION_VIEW_SECOND")
                context.startActivity(intent)
            }
            else {
                Toast.makeText(context, "Permission not granted!", Toast.LENGTH_SHORT).show()
            }
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