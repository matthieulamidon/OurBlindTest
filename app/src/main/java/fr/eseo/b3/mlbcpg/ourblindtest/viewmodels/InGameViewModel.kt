package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
import fr.eseo.b3.mlbcpg.ourblindtest.model.SubTheme
import fr.eseo.b3.mlbcpg.ourblindtest.model.Theme
import fr.eseo.b3.mlbcpg.ourblindtest.repositories.InGameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InGameViewModel(val repository: InGameRepository): ViewModel() {
    private val _pseudo = MutableStateFlow<String>("")
    var pseudo : StateFlow<String> = _pseudo.asStateFlow()

    private val _score = MutableStateFlow<Int>(0)
    var score : StateFlow<Int> = _score.asStateFlow()

    private val _currentQuestion = MutableStateFlow<Int>(0)
    val currentQuestion : StateFlow<Int> = _currentQuestion.asStateFlow()

    private val _question = MutableStateFlow<QuestionBlindTest?>(null)
    val question : StateFlow<QuestionBlindTest?> = _question.asStateFlow()

    private val _setting = MutableStateFlow(
        Setting(nb = 5, theme = Theme.JEU_VIDEO, subTheme = SubTheme.HOLLOW_KNIGHT)
    )
    val setting = _setting.asStateFlow()

    fun updateSetting(nb: Int, theme: Theme, subTheme: SubTheme?) {
        viewModelScope.launch {
            _setting.value = Setting(nb, theme, subTheme)
            repository.setSetting(_setting.value)
        }
    }

    init {
        loadNextQuestion()
        loadValue()
    }
    fun reload(){
        loadNextQuestion()
        loadValue()
    }

    fun loadNextQuestion() {
        viewModelScope.launch {
            _question.value = repository.getNextQuestion()
        }
    }

    fun loadValue() {
       viewModelScope.launch {
           _pseudo.value = repository.getPseudo()
           _currentQuestion.value = repository.getIdQuestion()
           _score.value = repository.getScore()
       }
    }

    fun nextQuestion(){
        viewModelScope.launch {
            repository.nextQuestion()
            _currentQuestion.value = _currentQuestion.value + 1
        }
    }
    fun getNextQuestion(){
        viewModelScope.launch {
            _question.value = repository.getNextQuestion()
        }
    }
    fun setPseudo(pseudo: String) {
        viewModelScope.launch {
            repository.setPseudo(pseudo)
            _pseudo.value = pseudo
        }
    }
    fun addScore(scoreVal: Int) {
        viewModelScope.launch {
            repository.addScore(scoreVal)
            _score.value = repository.getScore()
        }
    }
    fun setSetting() {
        viewModelScope.launch {
            repository.setSetting(setting.value)
        }
    }

    fun resetGame() {
        viewModelScope.launch {
            repository.resetGame()
            reload()
        }
    }

    fun generateListOfQuestion() {
        viewModelScope.launch {
            // 1. On génère la liste dans le repo
            repository.generateListOfQuestion()
            // 2. On met à jour l'interface avec les nouvelles valeurs (index 0, score 0)
            reload()
        }
    }

}
