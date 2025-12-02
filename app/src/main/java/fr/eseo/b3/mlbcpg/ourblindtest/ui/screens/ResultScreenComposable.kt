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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
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
            topBar = {
                OurBlindTestAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                )
            },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    ResultScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onRestart = onRestart,
                        score = score,
                        pseudo = pseudo
                    )
                }
            }
        )
    }

}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        inGameViewModel = InGameViewModel(InGameRepositoryListImpl()),
        ourBlindTestViewModel = OurBlindTestViewModel(FakeRepo()),
        onRestart = {}
    )
}

@Composable
private fun ResultScreenContent(
    modifier: Modifier,
    onRestart: () -> Unit,
    score: Int,
    pseudo: String
){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Résultat 🎉", fontSize = 28.sp)
        Spacer(Modifier.height(16.dp))
        Text("Bravo $pseudo !", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        Text("Score : $score", fontSize = 24.sp)
        Spacer(Modifier.height(24.dp))

        Button(onClick = onRestart) {
            Text("Rejouer")
        }
    }

}