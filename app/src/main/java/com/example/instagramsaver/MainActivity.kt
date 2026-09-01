package com.example.instagramsaver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SaverScreen() }
    }

    @Composable
    private fun SaverScreen() {
        var url by remember { mutableStateOf("") }
        var status by remember { mutableStateOf("Paste a public Instagram link.") }

        MaterialTheme {
            Surface(Modifier.fillMaxSize()) {
                Column(
                    Modifier.fillMaxSize().padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(Modifier.height(24.dp))
                    Text("Instagram Saver", style = MaterialTheme.typography.headlineLarge)
                    Text("Public posts, Reels and media you are authorized to save.")

                    OutlinedTextField(
                        value = url,
                        onValueChange = { url = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Instagram URL") },
                        placeholder = { Text("https://www.instagram.com/reel/...") },
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            val clean = url.trim()
                            if (!clean.contains("instagram.com")) {
                                status = "Please paste an Instagram URL."
                            } else {
                                status = "Opening the permitted media page..."
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(clean)))
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Continue") }

                    Card(Modifier.fillMaxWidth()) {
                        Text(status, Modifier.padding(16.dp))
                    }

                    Spacer(Modifier.weight(1f))
                    Text(
                        "This app does not request Instagram credentials or bypass private-account access.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
