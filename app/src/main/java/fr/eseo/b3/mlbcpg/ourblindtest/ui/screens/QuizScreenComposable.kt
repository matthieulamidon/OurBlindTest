package fr.eseo.b3.mlbcpg.ourblindtest.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepositoryListImpl
import fr.eseo.b3.mlbcpg.ourblindtest.ui.theme.OurBlindTestTheme
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.InGameViewModel
import kotlinx.coroutines.delay
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.style.TextOverflow
import fr.eseo.b3.mlbcpg.ourblindtest.viewmodels.DeezerViewModel

/*
@Composable
fun QuizScreen(onFinish: () -> Unit ,onStartQuiz: () -> Unit, InGameVM : InGameViewModel)
    {
        //val ViewModelInGame = InGameViewModel(InGameRepositoryListImpl())
        val question: QuestionBlindTest? by InGameVM.question.collectAsState()

        val shuffledAnswers = remember(question) {
            if (question != null) {
                listOf(
                    question?.title,
                    question?.falseChose1,
                    question?.falseChose2,
                    question?.falseChose3
                ).shuffled()
            } else {
                emptyList()
            }
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
                    QuizScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onStartQuiz = onStartQuiz,
                        onFinish = onFinish,

                        answers = shuffledAnswers as List<String>,
                        InGameVM = InGameVM
                    )
                }
            }
        )
    }
}

@Composable
private fun QuizScreenContent(
    modifier: Modifier,
    answers: List<String>,
    onStartQuiz: () -> Unit,
    onFinish: () -> Unit,
    InGameVM: InGameViewModel
) {
    val question: QuestionBlindTest? by InGameVM.question.collectAsState()
    val goodAnswer: String? = question?.title
    val currentQuestion = InGameVM.currentQuestion


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Zone Timer + Animation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "score : ${InGameVM.score.collectAsState().value}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Animation ici", fontSize = 18.sp)
                GameTimer(30, onTimeFinished = {
                    //viewModel.answerCorrect()
                    onStartQuiz()
                })
            }
        }

        // Question
        Text(
            text = "quelle est le nom de cette musique",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(Modifier.height(40.dp))

        // Réponses
        answers.forEach { answer ->
            AnswerButton(
                text = answer,
                onClick = {
                    response(onStartQuiz,onFinish, InGameVM, goodAnswer, answer)
                }
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}


@Composable
fun AnswerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(text, fontSize = 24.sp)
    }
}
*/
@Composable
fun QuizScreen(onFinish: () -> Unit, onStartQuiz: () -> Unit, DeezerVM: DeezerViewModel, InGameVM: InGameViewModel) {

    val question by InGameVM.question.collectAsState()

    val shuffledAnswers = remember(question) {
        if (question != null) {
            listOf(
                question?.title,
                question?.falseChose1,
                question?.falseChose2,
                question?.falseChose3
            ).shuffled()
        } else {
            emptyList()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = { OurBlindTestAppBar(modifier = Modifier.fillMaxWidth()) },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    QuizScreenContent(
                        modifier = Modifier.fillMaxWidth(),
                        onStartQuiz = onStartQuiz,
                        onFinish = onFinish,
                        answers = shuffledAnswers as List<String>,
                        InGameVM = InGameVM,
                        DeezerVM = DeezerVM,
                    )
                }
            }
        )
    }
}

@Composable
private fun QuizScreenContent(
    modifier: Modifier,
    answers: List<String>,
    onStartQuiz: () -> Unit,
    onFinish: () -> Unit,
    InGameVM: InGameViewModel,
    DeezerVM: DeezerViewModel
) {
    val questionState by InGameVM.question.collectAsState()
    val goodAnswer = questionState?.title
    val score by InGameVM.score.collectAsState()
    val currentQuestion by InGameVM.currentQuestion.collectAsState()
    val setting by InGameVM.setting.collectAsState()
    val nbOfQuestion = setting.nb
    InGameVM.getNextQuestion()
    Log.d("ReponseBT", questionState?.title ?: "eeeeee")

    LaunchedEffect(Unit) {
        DeezerVM.searchTrack(questionState?.title ?: "", /*questionState?.author ?: */null) {}
    }



    // NOUVEAUX ÉTATS POUR GÉRER L'UI ET LE DÉLAI
    var isAnswerRevealed by remember { mutableStateOf(false) } // Est-ce qu'on a cliqué ?
    var selectedAnswer by remember { mutableStateOf<String?>(null) } // Quel bouton a été cliqué ?

    // Logique de transition (Délai de 3 secondes)
    if (isAnswerRevealed) {
        LaunchedEffect(Unit) {
            delay(3000L) // On attend 3 secondes

            // Vérification : Est-ce la fin du jeu ?
            // On suppose que setting.nb est le nombre TOTAL de questions
            // et currentQuestion est l'index actuel (commençant souvent à 0 ou 1, à adapter selon ta logique VM)
            val totalQuestions = InGameVM.setting.value.nb
            val currentIndex = InGameVM.currentQuestion.value

            // Réinitialiser l'état pour la prochaine question
            isAnswerRevealed = false
            selectedAnswer = null

            if ( (currentIndex + 1) < totalQuestions) {
                // Il reste des questions
                InGameVM.nextQuestion()
                DeezerVM.playPause()
                onStartQuiz() // Recharge la vue ou navigue vers soi-même
            } else {
                // C'est fini

                DeezerVM.playPause()
                onFinish()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Utilisé pour pousser le contenu en haut/bas
    ) {
        // --- 1. EN-TÊTE : Score et Question ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Score et Numéro de Question (Haut de l'écran)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Score : $score",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Question $currentQuestion/$nbOfQuestion",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(32.dp))

            // Timer (Pièce maîtresse)
            // On le cache si la réponse est révélée
            if (!isAnswerRevealed) {
                GameTimer(30, onTimeFinished = {
                    if (!isAnswerRevealed) {
                        isAnswerRevealed = true
                        selectedAnswer = null
                    }
                })
            } else {
                // Espace pour ne pas faire sauter le layout
                Spacer(Modifier.height(100.dp))
            }

            Spacer(Modifier.height(32.dp))

            // Texte de la question
            Text(
                text = "Quelle est cette musique ?",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(40.dp))
        }

        // --- 2. BOUTONS DE RÉPONSE ---
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            answers.forEach { answer ->
                val containerColor = if (isAnswerRevealed) {
                    when {
                        answer == goodAnswer -> Color(0xFF4CAF50) // Vert (Succès)
                        answer == selectedAnswer -> MaterialTheme.colorScheme.error // Rouge (Erreur)
                        else -> MaterialTheme.colorScheme.surfaceVariant // Gris/Neutre
                    }
                } else {
                    MaterialTheme.colorScheme.primaryContainer // Couleur par défaut (plus claire que primary)
                }

                val contentColor = if (isAnswerRevealed) {
                    if (answer == goodAnswer) Color.White else MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onPrimaryContainer
                }


                AnswerButton(
                    text = answer,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    enabled = !isAnswerRevealed,
                    onClick = {
                        selectedAnswer = answer
                        isAnswerRevealed = true

                        if (answer == goodAnswer) {
                            InGameVM.addScore(1)
                        }
                    }
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun AnswerButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        ),

        modifier = Modifier.fillMaxWidth().height(64.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
/*
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    OurBlindTestTheme {
        val ViewModelInGame = InGameViewModel(InGameRepositoryListImpl())
        val question: QuestionBlindTest? by ViewModelInGame.question.collectAsState()


        val shuffledAnswers = remember(question) {
            if (question != null) {
                listOf(
                    question?.title,
                    question?.falseChose1,
                    question?.falseChose2,
                    question?.falseChose3
                ).shuffled()
            } else {
                emptyList()
            }
        }


        OurBlindTestAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )
        QuizScreenContent(
            modifier = Modifier.fillMaxWidth(),
            onStartQuiz = {},
            answers = shuffledAnswers as List<String>,
            InGameVM = ViewModelInGame,
            onFinish = {},
            DeezerVM = DeezerViewModel()
        )
    }
}*/

@Composable
fun GameTimer(
    totalTime: Int = 30,
    onTimeFinished: () -> Unit
) {
    // ... Logique du LaunchedEffect inchangée ...
    var timeLeft by remember { mutableIntStateOf(totalTime) }
    val progress = timeLeft / totalTime.toFloat()
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "ProgressAnimation")

    val themeColor = MaterialTheme.colorScheme.primary
    val urgentColor = MaterialTheme.colorScheme.error

    val color = if (timeLeft > 10) themeColor else urgentColor

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else {
            onTimeFinished()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(120.dp)
    ) {
        CircularProgressIndicator(
            progress = { 1f },
            modifier = Modifier.size(120.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            strokeWidth = 10.dp,
        )

        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.size(120.dp),
            color = color,
            strokeWidth = 10.dp,
        )

        Text(
            text = "$timeLeft",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}





