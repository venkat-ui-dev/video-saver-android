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
        setContent {
            MaterialTheme {
                var url by remember { mutableStateOf("") }
                var message by remember { mutableStateOf("Paste a direct media URL that you own or are authorized to download.") }
                Surface(Modifier.fillMaxSize()) {
                    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(Modifier.height(40.dp))
                        Text("Video Saver", style = MaterialTheme.typography.headlineLarge)
                        Spacer(Modifier.height(8.dp))
                        Text("Save permitted media files to your device.")
                        Spacer(Modifier.height(28.dp))
                        OutlinedTextField(url, { url = it }, Modifier.fillMaxWidth(), label = { Text("Video URL") }, placeholder = { Text("https://example.com/video.mp4") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri), singleLine = true)
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            val u = url.trim()
                            when {
                                !URLUtil.isValidUrl(u) -> message = "Please enter a valid URL."
                                u.contains("youtube.com") || u.contains("youtu.be") || u.contains("instagram.com") -> message = "Use the platform's official save/download feature, or a direct media URL you have permission to download."
                                else -> { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(u))); message = "Opened the URL. Saving depends on the server/browser permissions." }
                            }
                        }, Modifier.fillMaxWidth()) { Text("Open / Save") }
                        Spacer(Modifier.height(20.dp))
                        Card(Modifier.fillMaxWidth()) { Text(message, Modifier.padding(16.dp)) }
                        Spacer(Modifier.weight(1f))
                        Text("Personal utility • authorized downloads only", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}
