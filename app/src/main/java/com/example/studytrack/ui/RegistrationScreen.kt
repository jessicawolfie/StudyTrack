package com.example.studytrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onSave: (date: String, duration: Float, subject: String) -> Unit,
    onBack: () -> Unit
) {
    var subject by remember { mutableStateOf("") }
    var durationHours by remember { mutableStateOf("") }
    var durationMinutes by remember { mutableStateOf("") }

    // Current date as default
    val currentDate = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
    var selectedDate by remember { mutableStateOf(currentDate) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register Study Session") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Date field
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = { Text("Date") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Subject field
            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Subject studied") },
                placeholder = { Text("Ex: Jetpack Compose") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Duration Fields (Hours and Minutes)
            Text(
                text = "Duration",
                style = MaterialTheme.typography.titleMedium
            )

            // Duration Fields (Hours and Minutes)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Hours field
                OutlinedTextField(
                    value = durationHours,
                    onValueChange = {
                        if (it.isEmpty() || it.toIntOrNull() != null) {
                            durationHours = it
                        }
                    },
                    label = { Text("Hours") },
                    placeholder = { Text("0") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Minutes field
                OutlinedTextField(
                    value = durationMinutes,
                    onValueChange = {
                        if (it.isEmpty() || (it.toIntOrNull() != null && it.toInt() < 60)) {
                            durationMinutes = it
                        }
                    },
                    label = { Text("Minutes") },
                    placeholder = { Text("0") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Cancel button
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }

                // Save button
                Button(
                    onClick = {
                        // Basic validation
                        if (subject.isNotBlank()) {
                            val hours = durationHours.toIntOrNull() ?: 0
                            val minutes = durationMinutes.toIntOrNull() ?: 0
                            val totalDuration = hours + (minutes / 60f)

                            if (totalDuration > 0) {
                                onSave(selectedDate, totalDuration, subject)
                            }
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = subject.isNotBlank() &&
                            ((durationHours.toIntOrNull() ?: 0) > 0 || (durationMinutes.toIntOrNull() ?: 0) > 0)
                ) {
                    Text("Save")
                }
            }

        }
    }
}
