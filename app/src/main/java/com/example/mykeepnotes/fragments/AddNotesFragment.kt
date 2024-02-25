package com.example.mykeepnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.example.mykeepnotes.MainActivity
import com.example.mykeepnotes.R
import com.example.mykeepnotes.databinding.FragmentAddNotesBinding
import com.example.mykeepnotes.databinding.FragmentHomefragmentBinding
import com.example.mykeepnotes.model.Notes
import com.example.mykeepnotes.noteAdapter.NoteAdapter
import com.example.mykeepnotes.viewModel.NotesViewModel


class AddNotesFragment : Fragment(R.layout.fragment_add_notes) {
    private var _binding: FragmentAddNotesBinding?=null
    private val binding get()= _binding!!
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NoteAdapter
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAddNotesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).notesViewModel

        mView=view

        }


    private fun saveNote(view:View){
        val noteTitle=binding.etNotesTitle.text.toString()
        val noteBody= binding.etNotesBody.text.toString()

        if(noteTitle.isNotEmpty()){
           val notes= Notes(0, noteTitle, noteBody )
            notesViewModel.insertNotes(notes)
            Toast.makeText(mView.context, "Note saved Successfully", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_addNotesFragment_to_homefragment)
        }
        else{
            Toast.makeText(mView.context, "Please enter the title", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_new_notes, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}