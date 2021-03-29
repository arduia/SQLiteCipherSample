package com.demo.sqliteciphersample

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dto: NoteDto)

    @Query("SELECT * FROM note_sunshine")
    suspend fun getAll(): List<NoteDto>

    @Delete
    suspend fun delete(dto: NoteDto)

    @Query("DELETE FROM note_sunshine")
    suspend fun deleteAll()

    @Update
    suspend fun update(dto: NoteDto)

}