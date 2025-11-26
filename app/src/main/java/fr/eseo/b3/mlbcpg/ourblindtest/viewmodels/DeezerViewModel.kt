package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.deezerApi.DeezerApi
import kotlinx.coroutines.launch

class DeezerViewModel : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    //query nom de la musique voulu
    fun searchTrack(query: String, onPreviewReady: (String?) -> Unit) {
        viewModelScope.launch {
            val url = DeezerApi.searchTrack(query)
            if (url != null) {
                playPreview(url)
            }
            onPreviewReady(url)
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