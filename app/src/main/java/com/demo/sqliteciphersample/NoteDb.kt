package com.demo.sqliteciphersample

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.sqlcipher.database.SQLiteDatabase

@Database(entities = [NoteDto::class], version = 4, exportSchema = false)
abstract class NoteDb : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        const val NAME= "note.db"

        val MIGRATION_2_3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("NoteDb","Migrating... from 2 to 3")
                database.execSQL("ALTER TABLE note RENAME TO note_sunshine")

                Log.d("NoteDb","Done! Migrated to 3")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4){
            override fun migrate(database: SupportSQLiteDatabase) {

                Log.d("NoteDb","Migrating... from 3 to 4")
                database.execSQL("ALTER TABLE note_sunshine RENAME TO  tmp")
                database.execSQL("CREATE TABLE `note_sunshine` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `note` TEXT, `date` INTEGER NOT NULL)")
                database.execSQL("INSERT INTO `note_sunshine`(`id`, `title`, `note`, `date` ) " +
                        "SELECT `id`, `None`, `note`, `date` FROM tmp; ")
                database.execSQL("DROP TABLE tmp;")

                Log.d("NoteDb","Done! Migrated to 4")
            }
        }
    }
}