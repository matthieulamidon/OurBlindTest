package fr.eseo.b3.mlbcpg.ourblindtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.b3.mlbcpg.ourblindtest.model.QuestionBlindTest
import fr.eseo.b3.mlbcpg.ourblindtest.model.Setting
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

    private val _idQuestion = MutableStateFlow<Int>(0)
    val idQuestion : StateFlow<Int> = _idQuestion.asStateFlow()

    private val _question = MutableStateFlow<QuestionBlindTest?>(null)
    val question : StateFlow<QuestionBlindTest?> = _question.asStateFlow()
    
    private val _setting = MutableStateFlow(Setting(nb = 5, theme = "null", sbTheme = "null"))
    val setting : StateFlow<Setting> = _setting.asStateFlow()


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
           _idQuestion.value = repository.getIdQuestion()
           _score.value = repository.getScore()
       }
    }

    fun nextQuestion(){
        viewModelScope.launch {
            repository.nextQuestion()
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
        }
    }
    fun addScore(scoreVal: Int) {
        viewModelScope.launch {
            repository.addScore(scoreVal)
            score = MutableStateFlow<Int>(repository.getScore())
        }
    }
    fun setSetting() {
        viewModelScope.launch {
            repository.setSetting(setting.value)
        }
    }

}
