package com.jesscafezeiro.studytrack.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("studytrack_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ANNUAL_GOAL = "annual_goal"
        private const val DEFAULT_GOAL = 100f
    }

    fun saveAnnualGoal(goal: Float) {
        sharedPreferences.edit().putFloat(KEY_ANNUAL_GOAL, goal).apply()
    }

    fun getAnnualGoal(): Float {
        return sharedPreferences.getFloat(KEY_ANNUAL_GOAL, DEFAULT_GOAL)
    }
}