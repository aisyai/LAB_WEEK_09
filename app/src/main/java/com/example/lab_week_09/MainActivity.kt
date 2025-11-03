package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn // Added import
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField // Added import
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Update: The module's Step 13 (Page 7) changes this section.
                    // To follow the module's final state for Commit 1:
                    val list = listOf("Tanu", "Tina", "Tono")
                    Home(list)
                }
            }
        }
        // Removed the problematic second Surface block from your original file:
        /*
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val list = listOf("Tanu", "Tina", "Tono")
            Home(list)
        }
        */
    }
}

@Composable
fun Home(
    // Here, we define a parameter called items
    items: List<String>,
) {
    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.enter_item
                    )
                )
                // Here, we use TextField to display a text input field
                TextField(
                    // Set the value of the input field
                    value = "",
                    // Set the keyboard type of the input field
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    // Set what happens when the value of the input field changes
                    onValueChange = {
                    }
                )
                // Here, we use Button to display a button
                // the onClick parameter is used to set what happens when the button is clicked
                Button(onClick = { }) {
                    // Set the text of the button
                    Text(
                        text = stringResource(
                            id = R.string.button_click
                        )
                    )
                }
            }
        }

        // Here, we use items to display a list of items inside the LazyColumn
        // This is the RecyclerView replacement
        items(items) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item)
            }
        }
    }
}

// Here, we create a preview function of the Home composable
// This function is specifically used to show a preview of the Home composable
// This is only for development purpose
@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme { // Wrapped in theme for proper preview
        Home(listOf("Tanu", "Tina", "Tono"))
    }
}

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
    LAB_WEEK_09Theme {
        Greeting("Android")
    }
}