package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion.ListOfQuestion
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.DeezerViewModel
import java.util.concurrent.TimeUnit

private data class SongData(val name: String, val author: String)

@Composable
fun MusicPlayerScreen(deezerViewModel: DeezerViewModel) {
    var songName by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var searchStatus by remember { mutableStateOf("") }

    val track by deezerViewModel.track.collectAsState()
    val isPlaying by deezerViewModel.isPlaying.collectAsState()
    val currentPosition by deezerViewModel.currentPosition.collectAsState()
    val duration by deezerViewModel.duration.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Entrez le nom d'une musique",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = songName,
                onValueChange = { songName = it },
                label = { Text("Nom de la musique") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = authorName,
                onValueChange = { authorName = it },
                label = { Text("Nom de l'auteur (optionnel)") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(onClick = {
                searchStatus = "Recherche..."
                deezerViewModel.searchTrack(songName, authorName) { foundTrack ->
                    searchStatus = if (foundTrack != null) "Preview trouvée !" else "Preview non trouvée."
                }
            }) {
                Text("Lire la musique")
            }
            Button(onClick = {
                searchStatus = "Recherche..."
                val songsJson = ListOfQuestion.QUESTIONS_JSON_DATA
                val gson = Gson()
                val songListType = object : TypeToken<List<SongData>>() {}.type
                val songs: List<SongData> = gson.fromJson(songsJson, songListType)
                val randomSong = songs.random()
                songName = randomSong.name
                authorName = randomSong.author
                deezerViewModel.searchTrack(randomSong.name, randomSong.author) { foundTrack ->
                    searchStatus = if (foundTrack != null) "Preview trouvée !" else "Preview non trouvée."
                }
            }) {
                Text("Lecture aléatoire")
            }
        }

        if (searchStatus.isNotEmpty() && track == null) {
            Text(searchStatus, modifier = Modifier.padding(top = 16.dp))
        }

        track?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(it.albumArtUrl),
                        contentDescription = "Album Art",
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = it.artist,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = it.album,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Slider(
                        value = currentPosition.toFloat(),
                        onValueChange = { newPosition -> deezerViewModel.seekTo(newPosition.toInt()) },
                        valueRange = 0f..duration.toFloat(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = formatMillis(currentPosition.toLong()))
                        Text(text = formatMillis(duration.toLong()))
                    }

                    IconButton(onClick = { deezerViewModel.playPause() }) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatMillis(millis: Long): String {
    return String.format("%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(millis),
        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
    )
}