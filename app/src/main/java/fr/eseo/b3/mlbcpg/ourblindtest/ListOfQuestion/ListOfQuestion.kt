package fr.eseo.b3.mlbcpg.ourblindtest.ListOfQuestion

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.eseo.b3.mlbcpg.ourblindtest.R
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import fr.eseo.b3.mlbcpg.ourblindtest.model.Theme
import java.io.IOException
import kotlin.math.min

private data class SongData(
    val id: Int,
    val name: String,
    val author: String,
    val theme: String,
    val subTheme: String,
    val previewUrl: String?
)

class ListOfQuestion {
    // Charge le fichier JSON dans resources/
    fun generateQuestions(context: Context, settings: Setting): List<QuestionBlindTest> {

        val gson = Gson()
        val itemType = object : TypeToken<List<SongData>>() {}.type

        // Lecture depuis res/raw/songs.json
        val inputStream = context.resources.openRawResource(R.raw.songs)
        val reader = java.io.InputStreamReader(inputStream)

        val allSongs: List<SongData> = gson.fromJson(reader, itemType) ?: emptyList()

        // filtre a bonne reponse
        val filteredSongs = allSongs.filter { song ->
            val reqThemeName = settings.theme.name
            val reqSubThemeLabel = settings.subTheme?.label
            val hasThemeFilter = settings.theme != Theme.TOUT
            val hasSubThemeFilter = settings.subTheme != null

            when {
                hasSubThemeFilter -> song.subTheme == reqSubThemeLabel
                hasThemeFilter -> song.theme == reqThemeName
                else -> true
            }
        }

        // filtre secu si pas asser de bonne réponse et oui j'ai fait ça pour toi barth
        val sameThemeSongs = if (settings.theme != Theme.TOUT) {
            allSongs.filter { it.theme == settings.theme.name }
        } else {
            allSongs
        }

        val shuffledSongs = filteredSongs.shuffled()
        val limit = if (shuffledSongs.isNotEmpty()) min(settings.nb, shuffledSongs.size) else 0
        val selectedSongs = shuffledSongs.take(limit)

        val questions = selectedSongs.map { correctSong ->

            var potentialWrongAnswers = filteredSongs.filter { it.name != correctSong.name }

            if (potentialWrongAnswers.size < 3) {
                potentialWrongAnswers = sameThemeSongs.filter { it.name != correctSong.name }
            }

            if (potentialWrongAnswers.size < 3) {
                potentialWrongAnswers = allSongs.filter { it.name != correctSong.name }
            }

            val wrongAnswers = potentialWrongAnswers
                .shuffled()
                .take(3)
                .map { it.name }

            QuestionBlindTest(
                title = correctSong.name,
                author = correctSong.author,
                falseChose1 = wrongAnswers.getOrElse(0) { "Lumière" },
                falseChose2 = wrongAnswers.getOrElse(1) { "Une vie a t'aimer" },
                falseChose3 = wrongAnswers.getOrElse(2) { "Alicia" }
            )
        }
        // tkt ça marche enfin je crois euh ...
        return questions
    }
}