package com.example.mykeepnotes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mykeepnotes.model.Notes
import com.example.mykeepnotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val application: Application, private val repository: NoteRepository) : AndroidViewModel(application){

    fun insertNotes(notes: Notes){
        viewModelScope.launch {
            repository.insertNotes(notes)
        }
    }

    fun deleteNotes(notes: Notes){
        viewModelScope.launch {
            repository.deleteNotes(notes)
        }
    }

    fun updateNotes(notes: Notes){
        viewModelScope.launch {
            repository.updateNotes(notes)
        }
    }

    fun getAllNotes() : LiveData<List<Notes>>{
        return repository.getAllNotes()
    }

    fun searchNotes(query:String) : LiveData<List<Notes>>{
        return  repository.searchNotes(query)
    }

}