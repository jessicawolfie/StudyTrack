package com.example.studytrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// temporary data model
data class StudySession(
    val data: String,
    val duration: Float,
    val subject: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    // temporary data for viewing
    val targetYear = 100f
    val hoursStudied = 45f
    val sessions = listOf(
        StudySession("2026-01-28", 1.5f, "Jetpack Compose"),
        StudySession("2026-01-27", 2.0f, "Kotlin Coroutines"),
        StudySession("2026-01-26", 1.5f, "Room Database")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Study Track") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*open registration screen */}
            ) {
                Icon(Icons.Default.Add, contentDescription = "Register study session")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Target year card
            item { TargetCard(targetYear,hoursStudied) }

            // 2. Statistics card
            item { StatisticsCard(hoursStudied) }

            // 3. Title of the last sessions
            item {
                Text(
                    text = "Last Sessions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // 4. List of sessions
            items(sessions) { session ->
                StudySessionCard(session)
            }
        }
    }
} // end of MainScreen()

@Composable
fun TargetCard(targetYear: Float, hoursStudied: Float) {
    val progress = hoursStudied / targetYear

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Target Year: ${targetYear.toInt()}h",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Progress bar
            LinearProgressIndicator(
              progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hours studied: ${hoursStudied.toInt()}h / ${targetYear.toInt()}h (${(progress * 100).toInt()}%)",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun StatisticsCard(hoursStudied: Float){
    Card(
    modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Statistics",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticItem("Today", "1.5h")
                StatisticItem("Week", "10h")
                StatisticItem("Average/day", "2.1h")
            }
        }
    }
}

@Composable
fun StatisticItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun StudySessionCard(session: StudySession) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = session.subject,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = session.data,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "${session.duration}h",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


