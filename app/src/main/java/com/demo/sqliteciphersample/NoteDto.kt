package com.demo.sqliteciphersample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NoteDto.TABLE_NAME)
data class NoteDto(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "note")
    val note: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "title")
    val title: String

) {

    companion object {
        const val TABLE_NAME = "note_sunshine"
    }
}
