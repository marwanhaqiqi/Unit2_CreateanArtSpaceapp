@file:JvmName("ArtworkKt")

package com.example.createanartspaceapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.createanartspaceapp.ui.theme.ArtGalleryTheme

// Data class untuk menyimpan informasi karya seni
data class Artwork(val title: String, val artist: String, val year: Int, val imageResId: Int)

// Daftar karya seni
val artworks = listOf(
    Artwork("Sailing Under the Bridge", "Kat Kuan", 2017, R.drawable.bridge_image),
    Artwork("Sunset Over the Ocean", "John Doe", 2020, R.drawable.sunset_image),
    Artwork("Mountain Landscape", "Jane Smith", 2018, R.drawable.mountain_image)
)

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtGalleryApp()
                }
            }
        }
    }
}

@Composable
fun ArtGalleryApp() {
    var currentIndex by remember { mutableStateOf(0) }
    val artwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = artwork.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artwork.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${artwork.artist} (${artwork.year})",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) currentIndex--
                },
                enabled = currentIndex > 0
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentIndex < artworks.size - 1) currentIndex++
                },
                enabled = currentIndex < artworks.size - 1
            ) {
                Text("Next")
            }
        }
    }
}

