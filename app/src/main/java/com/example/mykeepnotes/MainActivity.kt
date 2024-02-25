package com.example.mykeepnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mykeepnotes.database.NoteDatabase
import com.example.mykeepnotes.databinding.ActivityMainBinding
import com.example.mykeepnotes.repository.NoteRepository
import com.example.mykeepnotes.viewModel.NoteViewModelFactory
import com.example.mykeepnotes.viewModel.NotesViewModel

class MainActivity : AppCompatActivity() {
    lateinit var notesViewModel: NotesViewModel
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpViewModel()






    }

    private fun setUpViewModel() {
       val noteRepository= NoteRepository(NoteDatabase(this))
       val factory= NoteViewModelFactory(application, noteRepository)
        notesViewModel=ViewModelProvider(this, factory).get(NotesViewModel::class.java)


    }
}