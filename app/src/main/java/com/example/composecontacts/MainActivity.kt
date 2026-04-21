package com.example.composecontacts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecontacts.domain.model.Contact
import com.example.composecontacts.ui.theme.ComposeContactsTheme
import com.example.composecontacts.ui.theme.ContactDetails
import com.example.composecontacts.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Main()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    ComposeContactsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("FirstComposeProject") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Purple40,
                        titleContentColor = Color.White
                    )
                )
            },) { innerPadding ->
                val contact = Contact(
                    name = "Евгений",
                    surname = "Андреевич",
                    familyName = "Лукашин",
                    imageRes = R.drawable.portrait,
                    isFavorite = true,
                    phone = "+7 495 495 95 95",
                    address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                    email = "ELukashin@practicum.ru"
                )
                    ContactDetails(
                        contact = contact,
                        modifier = Modifier.padding(innerPadding)
                    )
        }
    }
}
@Preview(showBackground = true, name = "with photo"
)
@Composable
fun MainPreview() {
    Main()
}