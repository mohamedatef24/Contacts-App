package com.example.contactapp


import android.content.Intent
import android.os.Bundle
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.contactapp.ui.theme.ContactappTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Contacts") },
                            actions = {
                                val context = LocalContext.current
                                IconButton(
                                    onClick = {
                                        val callIntent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:0235404856")
                                        }
                                        context.startActivity(callIntent)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Home,
                                        contentDescription = "Home Button"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        ContactApp()
                    }
                }
            }
        }
    }
}


@Composable
fun ContactApp(modifier: Modifier = Modifier) {
    val contacts = listOf(
        Contact("Son", "+201202868440", R.drawable.son),
        Contact("Brother", "+201234567489", R.drawable.brother),
        Contact("Sister", "+201233332222", R.drawable.sister),
        Contact("Auntie", "+201112223333", R.drawable.auntie),
        Contact("Daughter", "+201011111000", R.drawable.daughter),
        Contact("Friend1", "+201221112222", R.drawable.friend_1),
        Contact("Friend2", "+201133334456", R.drawable.friend_2),
        Contact("Granny", "+201189874556", R.drawable.granny),
        Contact("Grandfather", "+201195123211", R.drawable.grandfather),
        Contact("Neigbour", "+201251156849", R.drawable.neigbour),
        Contact("Uncle", "+201511235649", R.drawable.uncle),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {

        items(contacts) { contact ->
            Cards(contact = contact)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun contactappPreview() {
    ContactApp()
}

@Composable
fun Cards(contact: Contact, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        shape = RectangleShape,
        modifier = modifier.clickable {
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contact.phoneNumber}")
            }
            context.startActivity(callIntent)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = contact.photo), contentDescription = "")

            Text(
                text = contact.name,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,

                )
            SelectionContainer {
                Text(
                    text = contact.phoneNumber,
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,

                    )
            }
        }
    }
}

data class Contact(val name: String, val phoneNumber: String, val photo: Int)
