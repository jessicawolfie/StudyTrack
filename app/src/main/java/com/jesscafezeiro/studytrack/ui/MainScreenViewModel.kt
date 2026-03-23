package com.jesscafezeiro.studytrack.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jesscafezeiro.studytrack.data.StudySession
import com.jesscafezeiro.studytrack.data.StudyTrackDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel (application: Application) : AndroidViewModel(application) {

    private val dao = StudyTrackDatabase.getDatabase(application).StudySessionDao()

    // Sessions list
    private val _sessions =
        MutableStateFlow<List<StudySession>>(emptyList()) // PRIVATE - only the ViewModel can change this
    val sessions: StateFlow<List<StudySession>> =
        _sessions.asStateFlow() // PUBLIC - anyone can read this

    // Total hours studied
    private val _totalHours = MutableStateFlow(0f)
    val totalHours: StateFlow<Float> = _totalHours.asStateFlow()

    init {
        loadingData()
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

    fun addSession(session: StudySession) {
        viewModelScope.launch {
            dao.insert(session)
        }
    }

}

