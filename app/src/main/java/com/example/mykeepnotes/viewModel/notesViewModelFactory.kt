package com.example.mykeepnotes.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mykeepnotes.repository.NoteRepository

class NoteViewModelFactory(val application: Application, private val noteRepository: NoteRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(application, noteRepository) as T
    }
}