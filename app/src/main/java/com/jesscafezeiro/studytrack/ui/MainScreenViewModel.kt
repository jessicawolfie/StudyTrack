package com.jesscafezeiro.studytrack.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jesscafezeiro.studytrack.data.PreferencesManager
import com.jesscafezeiro.studytrack.data.StudySession
import com.jesscafezeiro.studytrack.data.StudyTrackDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel (application: Application) : AndroidViewModel(application) {

    private val dao = StudyTrackDatabase.getDatabase(application).StudySessionDao()
    private val preferencesManager = PreferencesManager(application)

    private val _sessions = MutableStateFlow<List<StudySession>>(emptyList())
    val sessions: StateFlow<List<StudySession>> = _sessions.asStateFlow()

    private val _totalHours = MutableStateFlow(0f)
    val totalHours: StateFlow<Float> = _totalHours.asStateFlow()

    private val _annualGoal = MutableStateFlow(100f)
    val annualGoal: StateFlow<Float> = _annualGoal.asStateFlow()

    init {
        loadingData()
        loadAnnualGoal()
    }

    private fun loadingData() {
        viewModelScope.launch {
            dao.getAllSessions().collect { list ->
                _sessions.value = list
            }
        }

        viewModelScope.launch {
            dao.getTotalDuration().collect { total ->
                _totalHours.value = total ?: 0f
            }
        }
    }

    private fun loadAnnualGoal() {
        _annualGoal.value = preferencesManager.getAnnualGoal()
    }

    fun addSession(session: StudySession) {
        viewModelScope.launch {
            dao.insert(session)
        }
    }

    fun deleteSession(session: StudySession) {
        viewModelScope.launch {
            dao.delete(session)
        }
    }

    fun saveAnnualGoal(goal: Float) {
        preferencesManager.saveAnnualGoal(goal)
        _annualGoal.value = goal
    }
}
