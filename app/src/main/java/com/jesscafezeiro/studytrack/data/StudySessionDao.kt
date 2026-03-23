package com.jesscafezeiro.studytrack.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {

    @Insert
    suspend fun insert(session: StudySession)

    @Query("SELECT * FROM study_sessions ORDER BY id DESC")
    fun getAllSessions(): Flow<List<StudySession>>

    @Delete
    suspend fun delete(session: StudySession)

    @Query("SELECT SUM(duration) FROM study_sessions")
    fun getTotalDuration(): Flow<Float?>
}
