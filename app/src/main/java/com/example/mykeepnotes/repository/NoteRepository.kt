package com.example.mykeepnotes.repository

import androidx.lifecycle.LiveData
import com.example.mykeepnotes.database.NoteDatabase
import com.example.mykeepnotes.model.Notes

class NoteRepository(private val db:NoteDatabase) {

    suspend fun insertNotes(notes: Notes){ db.getNoteDao().insertNotes(notes)}
    suspend fun deleteNotes(notes: Notes){db.getNoteDao().deleteNotes(notes)}
    suspend fun updateNotes(notes: Notes){db.getNoteDao().updateNotes(notes)}
    fun getAllNotes():LiveData<List<Notes>>{
        return db.getNoteDao().getAllNotes()
    }
    fun searchNotes(query:String): LiveData<List<Notes>>
    {
       return db.getNoteDao().searchNote(query)
    }
}