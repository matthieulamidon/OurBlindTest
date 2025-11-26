package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.DeezerApi
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.Track
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeezerViewModel : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null
    private var progressJob: Job? = null

    private val _track = MutableStateFlow<Track?>(null)
    val track = _track.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0)
    val duration = _duration.asStateFlow()

    fun searchTrack(songName: String, author: String? = null, onTrackReady: (Track?) -> Unit) {
        viewModelScope.launch {
            releasePlayer() // Release previous player if any
            val query = if (author != null && author.isNotBlank()) {
                "track:\"$songName\" artist:\"$author\""
            } else {
                songName
            }
            val foundTrack = DeezerApi.searchTrack(query)
            _track.value = foundTrack
            if (foundTrack != null) {
                prepareAndPlay(foundTrack.preview)
            }
            onTrackReady(foundTrack)
        }
    }

    private fun prepareAndPlay(url: String) {
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(url)
                setOnPreparedListener { player ->
                    _duration.value = player.duration
                    player.start()
                    _isPlaying.value = true
                    startProgressUpdates()
                }
                setOnCompletionListener {
                    _isPlaying.value = false
                    _currentPosition.value = _duration.value
                    progressJob?.cancel()
                }
                prepareAsync()
            } catch (e: Exception) {
                e.printStackTrace()
                releasePlayer()
            }
        }
    }
    
    fun playPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _isPlaying.value = false
                progressJob?.cancel()
            } else {
                it.start()
                _isPlaying.value = true
                startProgressUpdates()
            }
        }
    }

    fun seekTo(positionMillis: Int) {
        mediaPlayer?.seekTo(positionMillis)
        _currentPosition.value = positionMillis
    }

    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            while (true) { // Will be cancelled when paused or completed
                _currentPosition.value = mediaPlayer?.currentPosition ?: 0
                delay(100)
            }
        }
    }

    private fun releasePlayer() {
        progressJob?.cancel()
        progressJob = null
        mediaPlayer?.release()
        mediaPlayer = null
        _isPlaying.value = false
        _currentPosition.value = 0
        _duration.value = 0
        _track.value = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }
}