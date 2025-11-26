package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion.ListOfQuestion
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.Track
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.DeezerViewModel

private data class SongData(val name: String, val author: String)

@Composable
fun MusicPlayerScreen(deezerViewModel: DeezerViewModel) {
    var songName by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var track by remember { mutableStateOf<Track?>(null) }
    var searchStatus by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Entrez le nom d'une musique")
        OutlinedTextField(
            value = songName,
            onValueChange = { songName = it },
            label = { Text("Nom de la musique") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = authorName,
            onValueChange = { authorName = it },
            label = { Text("Nom de l'auteur (optionnel)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            searchStatus = "Recherche..."
            deezerViewModel.searchTrack(songName, authorName) { foundTrack ->
                track = foundTrack
                searchStatus = if (foundTrack != null) "Preview trouvée !" else "Preview non trouvée."
            }
        }) {
            Text("Lire la musique")
        }
        Spacer(modifier = Modifier.height(8.dp))
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
                track = foundTrack
                searchStatus = if (foundTrack != null) "Preview trouvée !" else "Preview non trouvée."
            }
        }) {
            Text("Lecture aléatoire")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (searchStatus.isNotEmpty()) {
            Text(searchStatus)
        }

        track?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Titre: ${it.title}")
            Text("Artiste: ${it.artist}")
            Text("Album: ${it.album}")
        }
    }
}