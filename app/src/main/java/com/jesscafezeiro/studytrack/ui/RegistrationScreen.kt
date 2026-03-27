package com.jesscafezeiro.studytrack.ui

import androidx.compose.foundation.clickable
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

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = remember { sdf.format(Date()) }
    var selectedDate by remember { mutableStateOf(currentDate) }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        selectedDate = sdf.format(Date(it))
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register Study Session") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
            // Date field (clickable to open DatePicker)
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Date") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker = true },
                enabled = false, // Makes it look like a button/clickable field
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
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

            // Duration Fields
            Text(
                text = "Duration",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
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
