package com.jesscafezeiro.studytrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentGoal: Float,
    onSaveGoal: (Float) -> Unit,
    onBack: () -> Unit
) {
    var goalInput by remember { mutableStateOf(currentGoal.toInt().toString()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
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
            Text(
                text = "Annual Study Goal",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Set your target hours for the year",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = goalInput,
                onValueChange = {
                    if (it.isEmpty() || it.toIntOrNull() != null) {
                        goalInput = it
                    }
                },
                label = { Text("Hours") },
                placeholder = { Text("100") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                suffix = { Text("hours") }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val goal = goalInput.toFloatOrNull() ?: 100f
                    if (goal > 0) {
                        onSaveGoal(goal)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = goalInput.isNotBlank() && (goalInput.toIntOrNull() ?: 0) > 0
            ) {
                Text("Save Goal")
            }
        }
    }
}