@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a3.ui.theme._3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {

    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    val context = LocalContext.current

    val options = listOf(
        "Instagram",
        "Youtube",
        "Reddit"
    )
    val optionsImage = listOf(
        R.drawable.instagram,
        R.drawable.youtube,
        R.drawable.reddit
    )
    val optionsURL = listOf(
        "https://www.instagram.com",
        "https://www.youtube.com",
        "https://www.reddit.com"
    )
    var selectedOption by remember { mutableStateOf("") }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilephoto),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
            )
        
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .padding(8.dp),
            singleLine = true,
            label = { Text(text = "Username") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .padding(8.dp),
            singleLine = true,
            label = { Text(text = "Password") }
        )

        Button(
            onClick = {
                val url = optionsURL[options.indexOf(selectedOption)]
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)

                if (intent.resolveActivity(context.packageManager) != null)
                    context.startActivity(intent)
                else
                    Toast.makeText(context,
                        "No browser found",
                        Toast.LENGTH_LONG)
                        .show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Open Website")
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            options.forEach { text ->
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp,),
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .clickable { onSelectionChange(text) }
                            .background(
                                if (text == selectedOption)
                                    Color.Magenta
                                else
                                    Color.LightGray
                            )
                            .padding(
                                vertical = 12.dp,
                                horizontal = 16.dp,
                            )
                    )
                    Image(
                        painter = painterResource(
                            id = optionsImage[options.indexOf(text)]),
                        contentDescription = "Images"
                    )
                }
            }
        }
    }
}