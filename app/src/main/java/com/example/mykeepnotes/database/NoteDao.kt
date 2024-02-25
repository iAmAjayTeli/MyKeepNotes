package com.example.mykeepnotes.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mykeepnotes.model.Notes

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update
   suspend fun updateNotes(notes: Notes)

    @Delete
   suspend fun deleteNotes(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun  getAllNotes(): LiveData<List<Notes>>


    @Query("SELECT * FROM notes_table WHERE Title LIKE :query OR Body LIKE:query")
    fun searchNote(query: String):LiveData<List<Notes>>


}