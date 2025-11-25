package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class QuizViewModel : ViewModel() {

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _currentQuestion = mutableStateOf("Quelle est la réponse à cette question ?")
    val currentQuestion: State<String> = _currentQuestion

    fun answerCorrect() {
        _score.value++
    }
}

