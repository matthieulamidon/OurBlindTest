package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import fr.eseo.b3.mlbcpg.ourblindtest.R
import fr.eseo.b3.mlbcpg.ourblindtest.model.Score
import fr.eseo.b3.mlbcpg.ourblindtest.model.SubTheme
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepository
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoriesRoomImp
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepository
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.OurBlindTestRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.ui.screens.OurBlindTestAppBar
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModelFactory
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModel
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.OurBlindTestViewModelFactory
import java.time.LocalDateTime

@Composable
fun MainScreen(onStartQuiz: () -> Unit,
   onMusicPlayer: () -> Unit,
   onSettingsBtn: () -> Unit,
   inGameVm: InGameViewModel,
   ourBlindTestVm : OurBlindTestViewModel) {
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
                    MainScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onStartQuiz = onStartQuiz,
                        onSettingsBtn = onSettingsBtn,
                        onMusicPlayer = onMusicPlayer,
                        inGameVm =  inGameVm,
                        ourBlindTestVm = ourBlindTestVm
                    )
                }
            }
        )
    }
}

@Composable
private fun MainScreenContent(
    modifier: Modifier,
    onStartQuiz: () -> Unit,
    onMusicPlayer: () -> Unit,
    onSettingsBtn: () -> Unit,
    inGameVm: InGameViewModel,
    ourBlindTestVm: OurBlindTestViewModel
) {
    var pseudo by remember { mutableStateOf("") }
    val scoreList by ourBlindTestVm.scores.collectAsState()
    val currentSetting by inGameVm.setting.collectAsState()

    val nb by remember { mutableIntStateOf(currentSetting.nb) }
    val theme by remember { mutableStateOf(currentSetting.theme) }
    val subTheme by remember { mutableStateOf<SubTheme?>(currentSetting.subTheme) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()), // sert à ajouter du défilement si l'écran est trop petit
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(Modifier.height(16.dp))

        //Carte pour les scores
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.scores_table),
                    style = MaterialTheme.typography.titleLarge, // Utilisation de la typographie du thème
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(12.dp))
                ScoreTable(scoreList)
            }
        }

        Spacer(Modifier.height(40.dp))

        // Titre du Jeu
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.blind_test),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.width(12.dp))

        }

        Spacer(Modifier.height(24.dp))

        // Champ Pseudo
        OutlinedTextField(
            value = pseudo,
            onValueChange = { pseudo = it },
            label = { Text(stringResource(id = R.string.pseudo)) },
            // On le rend un peu moins large pour un meilleur équilibre visuel
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(Modifier.height(32.dp))

        //Boutons d'Action

        // Bouton Principal
        ElevatedButton(
            onClick = {
                inGameVm.setPseudo(pseudo)
                inGameVm.generateListOfQuestion()
                onStartQuiz()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.begin_quiz), fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))

        // Boutons Secondaires
        FilledTonalButton(
            onClick = { onMusicPlayer() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.music_player))
        }

        Spacer(Modifier.height(16.dp))

        FilledTonalButton(
            onClick = { onSettingsBtn() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.settings))
        }

        Spacer(Modifier.height(40.dp))

        //Affichage des Settings
        Text(
            "Paramètres Actuels :",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // chip en flowRow (si ça prends trop de place, ça passe à la ligne suivante)
            AssistChip(onClick = {}, label = { Text("$nb Questions") })
            Spacer(Modifier.width(8.dp))
            AssistChip(onClick = {}, label = { Text("Thème : ${theme.label}") })

            // le sous thème ne s'affiche que s'il est défini
            if (subTheme != null) {
                Spacer(Modifier.width(8.dp))
                AssistChip(onClick = {}, label = { Text("Sous-thème : ${subTheme?.label}") })
            }
        }

        Spacer(Modifier.height(24.dp)) // Padding final
    }
}

@Composable
fun ScoreTable(scores: List<Score>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pseudo", style = MaterialTheme.typography.labelMedium)
            Text("Score", style = MaterialTheme.typography.labelMedium)
        }
        // ligne sous Pseudo et Score
        HorizontalDivider()

        scores.forEachIndexed { index, score ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // ça permet d'alterner entre deux couleurs grâce à l'index
                    .background(if (index % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f) else Color.Transparent)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // les trois premiers scores sont mis en valeur (toujours grâce à l'index)
                Text(
                    text = score.name,
                    fontWeight = if (index < 3) FontWeight.SemiBold else FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${score.score} pts",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

class FakeRepo : OurBlindTestRepository {
    private val fakeData = listOf(
        Score(name = "Test", score = 12),
        Score(name = "Preview", score = 5)
    )

    override suspend fun getAllScore() = fakeData

    override suspend fun addOrUpdateScore(score: Score) {}
    override suspend fun deleteScore(score: Score) {}
    override suspend fun getScoreById(id: Int) = null
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeViewModel = OurBlindTestViewModel(repository = FakeRepo())
    val fakeViewModelInGame = InGameViewModel(repository = InGameRepositoryListImpl())

    // Force l'init directe
    fakeViewModel.refreshScores()

    OurBlindTestTheme {
        MainScreen(
            onStartQuiz = {},
            onMusicPlayer = {},
            onSettingsBtn = {},
            inGameVm = fakeViewModelInGame,
            ourBlindTestVm = fakeViewModel
        )
    }
}


/*
    @Composable
    @Preview(
        showBackground = true,
        showSystemUi = true
    )
    private fun preview() {
        OurBlindTestTheme {  {
            val viewModel : OurBlindTestViewModel = viewModel(
                factory = NoteTakerViewModelFactory(NoteTakerRepositoryListImpl())
            )
            DetailsScreen(rememberNavController(), "1", viewModel)
        }
    }
*/

























