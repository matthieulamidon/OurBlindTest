package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModel

@Composable

fun ResultScreen(
    inGameViewModel: InGameViewModel,
    ourBlindTestViewModel: OurBlindTestViewModel,
    onRestart: () -> Unit
) {

    val score by inGameViewModel.score.collectAsState()
    val pseudo by inGameViewModel.pseudo.collectAsState()
    val settings by inGameViewModel.setting.collectAsState()

    val MAX_SCORE by remember { mutableIntStateOf(settings.nb) }

    LaunchedEffect(Unit) {
        ourBlindTestViewModel.addOrUpdatescore(Score(name = pseudo, score = score))
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold (
            topBar = { OurBlindTestAppBar(modifier = Modifier.fillMaxWidth()) },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    ResultScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onRestart = onRestart,
                        score = score,
                        MAX_SCORE = MAX_SCORE,
                        pseudo = pseudo
                    )
                }
            }
        )
    }
}

@Composable
private fun ResultScreenContent(
    modifier: Modifier,
    onRestart: () -> Unit,
    score: Int,
    MAX_SCORE : Int,
    pseudo: String
){
    val percentage = (score.toFloat() / MAX_SCORE.toFloat()) * 100

    val (feedbackText, feedbackIcon) = when {
        percentage == 100f -> "Félicitations, Génie !" to "🏆"
        percentage >= 60f -> "Bravo $pseudo, c'est réussi !" to "🎉"
        else -> "Dommage $pseudo, essaye encore." to "🎶"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carte d'affichage des résultats
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Titre et Icône
                Text(
                    text = "Résultats du Quiz $feedbackIcon",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(24.dp))

                // Message de Feedback
                Text(
                    text = feedbackText,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(32.dp))

                // Affichage du Score
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Score :",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "$score / $MAX_SCORE", // Score sur Max
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(Modifier.height(48.dp))

        // Bouton de Recommencer
        ExtendedFloatingActionButton(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth(0.8f),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = "Rejouer")
            Spacer(Modifier.width(8.dp))
            Text("Rejouer", style = MaterialTheme.typography.titleMedium)
        }
    }
}
/*
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        inGameViewModel = InGameViewModel(InGameRepositoryListImpl()),
        ourBlindTestViewModel = OurBlindTestViewModel(FakeRepo()),
        onRestart = {}
    )
}*/