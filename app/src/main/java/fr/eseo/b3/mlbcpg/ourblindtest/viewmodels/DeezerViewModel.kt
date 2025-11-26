package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.DeezerApi
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.Track
import kotlinx.coroutines.launch

class DeezerViewModel : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    fun searchTrack(songName: String, author: String? = null, onTrackReady: (Track?) -> Unit) {
        viewModelScope.launch {
            val query = if (author != null && author.isNotBlank()) {
                "track:\"$songName\" artist:\"$author\""
            } else {
                songName
            }
            val track = DeezerApi.searchTrack(query)
            if (track != null) {
                playPreview(track.preview)
            }
            onTrackReady(track)
        }
    }

    private fun playPreview(url: String) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(url)
                setOnPreparedListener { start() }
                prepareAsync()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}