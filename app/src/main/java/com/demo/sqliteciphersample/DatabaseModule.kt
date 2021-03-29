package com.demo.sqliteciphersample

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabaseHook
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDb {

//        val key = "password"
//
//        val testBytes = key.toCharArray()
//
//        val state = SQLCipherUtils.getDatabaseState(context, NoteDb.NAME)
//        if (state == SQLCipherUtils.State.UNENCRYPTED) {
//            SQLCipherUtils.encrypt(context, NoteDb.NAME, testBytes)
//        }
//        val passphrase = SQLiteDatabase.getBytes(testBytes)
//        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(context, NoteDb::class.java, NoteDb.NAME)
//            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
//            .addMigrations(NoteDb.MIGRATION_2_3, NoteDb.MIGRATION_3_4)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDb): NoteDao = db.noteDao

}