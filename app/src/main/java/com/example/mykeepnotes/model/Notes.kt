package com.example.mykeepnotes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes_table")
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "Title")
    val notesTitle:String,

    @ColumnInfo(name = "Body")
    val notesBody:String
):Parcelable
