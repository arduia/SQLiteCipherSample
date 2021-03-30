package com.demo.sqliteciphersample

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase

@Database(entities = [NoteDto::class], version =6, exportSchema = false)
abstract class NoteDb : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val NAME = "note.db"

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("NoteDb", "Migrating... from 2 to 3")
                database.execSQL("UPDATE `note` SET `note`= 'Joined!';")

                Log.d("NoteDb", "Migrated to 3")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("NoteDb", "Migrating... from 3 to 4")
                database.execSQL("ALTER TABLE note RENAME TO  note_sunshine")

                Log.d("NoteDb", "Done! Migrated to 4")
            }
        }
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("NoteDb", "Migrating... from 4 to 5")
                database.execSQL("UPDATE `note_sunshine` SET `note`= 'Joined!55555';")

                Log.d("NoteDb", "Migrated to 5")
            }
        }

        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("NoteDb", "Migrating... from 5 to 6")
                database.execSQL("UPDATE `note_sunshine` SET `note`= 'Joined!666666';")

                Log.d("NoteDb", "Done! Migrated to 6")
            }
        }
    }
}