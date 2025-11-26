package fr.eseo.b3.mlbcpg.ourblindtest.deezerApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object DeezerApi {

    suspend fun searchTrack(query: String): String? = withContext(Dispatchers.IO) {
        // Ajout de &strict=on pour des résultats plus précis
        val url = URL("https://api.deezer.com/search/track?q=${URLEncoder.encode(query, "UTF-8")}&limit=1&strict=on")
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"

        if (conn.responseCode == 200) {
            val response = conn.inputStream.bufferedReader().readText()
            val json = JSONObject(response)
            val items = json.getJSONArray("data")
            if (items.length() > 0) {
                return@withContext items.getJSONObject(0).getString("preview")
            }
        }
        null
    }
}