package com.jesscafezeiro.studytrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jesscafezeiro.studytrack.ui.theme.StudyTrackTheme
import com.jesscafezeiro.studytrack.ui.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyTrackTheme {
                NavGraph()
            }
        }
    }
}
