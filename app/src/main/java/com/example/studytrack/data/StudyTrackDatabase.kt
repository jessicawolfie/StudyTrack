package com.example.studytrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StudySession::class],
    version = 1,
    exportSchema = false
)
abstract class StudyTrackDatabase : RoomDatabase() {
    abstract fun StudySessionDao(): StudySessionDao

    companion object {
        @Volatile
        private var INSTANCE: StudyTrackDatabase? = null

        fun getDatabase(context: Context): StudyTrackDatabase {
            return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudyTrackDatabase::class.java,
                "study_track_database"
            ).build()
            INSTANCE = instance
            instance
        }
        }
    }
}
