package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf // Added import for mutableStateListOf
import androidx.compose.runtime.mutableStateOf // Added import for mutableStateOf
import androidx.compose.runtime.remember // Added import for remember
import androidx.compose.runtime.snapshots.SnapshotStateList // Added import for SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.PrimaryTextButton
import com.example.lab_week_09.ui.theme.OnBackgroundItemText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    // We use Modifier.fillMaxSize() to make the surface fill the whole screen
                    modifier = Modifier.fillMaxSize(),
                    // We use MaterialTheme.colorScheme.background to get the background color
                    // and set it as the color of the surface
                    color = MaterialTheme.colorScheme.background
                ) {
                    // We call the Home composable without passing any data (Commit 2)
                    Home()
                }
            }
        }
    }
}

// Declare a data class called Student (Commit 2, Step 2)
data class Student(
    var name: String
)

// The old Home composable (from Commit 1) has been removed,
// and a new Home without parameters is declared. (Commit 2, Step 3 & 6)
@Composable
fun Home() {
    // Here, we create a mutable state List of Student
    // We use remember to make the List remember its value
    // This is so that the List won't be recreated when the composable recomposes
    // We use mutableStateListOf to make the List mutable
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }

    // Here, we create a mutable state of Student
    // This is so that we can get the value of the input field
    var inputField = remember { mutableStateOf(Student("")) }

    // We call the HomeContent composable
    // Here, we pass:
    // listData to show the List of items inside HomeContent
    // inputField.value to show the input field value inside HomeContent
    // A Lambda function to update the value of the inputField
    // A Lambda function to add the inputField to the ListData
    HomeContent(
        listData = listData,
        inputField = inputField.value,
        onInputValueChange = { input -> inputField.value = inputField.value.copy(name = input) },
        onButtonClick = {
            if (inputField.value.name.isNotBlank()) {
                listData.add(inputField.value)
                inputField.value = Student("")
            }
        }
    )
}

// HomeContent is used to display the content of the Home composable (Commit 2, Step 4)
@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    // Here, we use LazyColumn to display a list of items Lazily
    LazyColumn {
        //Here, we use item to display an item inside the LazyColumn
        item {
            Column(
                //Modifier.padding(16.dp) is used to add padding to the Column
                //You can also use Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            //to add padding horizontally and vertically
            //or Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            //to add padding to each side
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            //Alignment.CenterHorizontally is used to align the Column horizontally
            //You can also use verticalArrangement = Arrangement.Center to align the Column vertically
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
            //Here, we call the OnBackgroundTitleText UI Element
            OnBackgroundTitleText(text = stringResource(
                id = R.string.enter_item)
            )

            //Here, we use TextField to display a text input field
            TextField(
                //Set the value of the input field
                value = inputField.name,
                //Set the keyboard type of the input field
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                //Set what happens when the value of the input field changes
                onValueChange = {
                    //Here, we call the onInputValueChange lambda function
                    //and pass the value of the input field as a parameter
                    //This is so that we can update the value of the inputField
                    onInputValueChange(it)
                }
            )

                //Here, we call the PrimaryTextButton UI Element
                PrimaryTextButton(text = stringResource(
                    id = R.string.button_click)
                ) {
                    onButtonClick()
                }
            }
        }
        //Here, we use items to display a list of items inside the LazyColumn
        //This is the RecyclerView replacement
        //We pass the listData as a parameter
        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Here, we call the OnBackgroundItemText UI Element
                OnBackgroundItemText(text = item.name)
            }
        }
    }

                }

// Here, we create a preview function of the Home composable (Commit 2 is before the new PreviewHome)
@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme { // Wrapped in theme for proper preview
        // Pass dummy data for preview
        HomeContent(
            listData = remember { mutableStateListOf(Student("Tanu"), Student("Tina"), Student("Tono"))},
            inputField = Student(""),
            onInputValueChange = {},
            onButtonClick = {}
        )
    }
}