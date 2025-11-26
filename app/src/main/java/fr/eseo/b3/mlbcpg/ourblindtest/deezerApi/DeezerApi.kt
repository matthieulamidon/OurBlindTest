package fr.eseo.b3.mlbcpg.ourblindtest.deezerApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

data class Track(
    val title: String,
    val artist: String,
    val album: String,
    val preview: String
)

object DeezerApi {

    suspend fun searchTrack(query: String): Track? = withContext(Dispatchers.IO) {
        // Ajout de &strict=on pour des résultats plus précis
        val url = URL("https://api.deezer.com/search/track?q=${URLEncoder.encode(query, "UTF-8")}&limit=1&strict=on")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"

        if (conn.responseCode == 200) {
            val response = conn.inputStream.bufferedReader().readText()
            val json = JSONObject(response)
            val items = json.getJSONArray("data")
            if (items.length() > 0) {
                val trackJson = items.getJSONObject(0)
                val title = trackJson.getString("title")
                val artist = trackJson.getJSONObject("artist").getString("name")
                val album = trackJson.getJSONObject("album").getString("title")
                val preview = trackJson.getString("preview")
                return@withContext Track(title, artist, album, preview)
            }
        }
        null
    }
}