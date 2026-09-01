package com.example.videosaver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { VideoSaverApp() }
    }

    @Composable
    fun VideoSaverApp() {
        var url by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("Paste a direct media URL you own or have permission to download.") }

        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(36.dp))
                    Text("Video Saver", style = MaterialTheme.typography.headlineLarge)
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Save permitted video files directly to your device.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(28.dp))

                    OutlinedTextField(
                        value = url,
                        onValueChange = { url = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Video URL") },
                        placeholder = { Text("https://example.com/video.mp4") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val trimmed = url.trim()
                            if (!URLUtil.isValidUrl(trimmed)) {
                                message = "Please enter a valid URL."
                            } else if (trimmed.contains("youtube.com") ||
                                       trimmed.contains("youtu.be") ||
                                       trimmed.contains("instagram.com")) {
                                message = "For YouTube/Instagram, use the platform's official download/save features or a direct file URL you have permission to download."
                            } else {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trimmed))
                                startActivity(intent)
                                message = "Opened the media URL. Your browser/app can save it if the server permits downloading."
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open / Save")
                    }

                    Spacer(Modifier.height(24.dp))

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            message,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.weight(1f))
                    Text(
                        "Personal-use utility • Download only content you are authorized to save.",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
