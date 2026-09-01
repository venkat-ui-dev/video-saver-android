package com.example.videosaver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { VideoSaverScreen() }
    }

    @Composable
    private fun VideoSaverScreen() {
        var url by remember { mutableStateOf("") }
        var message by remember {
            mutableStateOf("Paste a direct media URL you own or have permission to download.")
        }

        MaterialTheme {
            Surface(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
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
                            val value = url.trim()
                            message = when {
                                !URLUtil.isValidUrl(value) ->
                                    "Please enter a valid URL."
                                value.contains("youtube.com") || value.contains("youtu.be") ||
                                value.contains("instagram.com") ->
                                    "For YouTube/Instagram, use the platform's official download/save features or a direct file URL you have permission to download."
                                else -> {
                                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
                                    "Opened the media URL."
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open / Save")
                    }

                    Spacer(Modifier.height(24.dp))

                    Card(Modifier.fillMaxWidth()) {
                        Text(message, Modifier.padding(16.dp))
                    }

                    Spacer(Modifier.height(24.dp))
                    Text(
                        "Download only content you are authorized to save.",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
