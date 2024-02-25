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
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mykeepnotes.MainActivity
import com.example.mykeepnotes.R
import com.example.mykeepnotes.databinding.FragmentHomefragmentBinding
import com.example.mykeepnotes.databinding.FragmentUpdatesBinding
import com.example.mykeepnotes.model.Notes
import com.example.mykeepnotes.noteAdapter.NoteAdapter
import com.example.mykeepnotes.viewModel.NotesViewModel


class UpdatesFragment : Fragment() {
    private var _binding: FragmentUpdatesBinding?=null
    private val binding get()= _binding!!
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var currentNotes: Notes
   private val args:UpdatesFragmentArgs  by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=FragmentUpdatesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).notesViewModel
        currentNotes= args.notes!!

       binding.etNotesTitle.setText(currentNotes.notesTitle)
        binding.etNotesBody.setText(currentNotes.notesBody)

        //if the user click on the done menu icon
        binding.updateFloatingBtn.setOnClickListener {
            val title= binding.etNotesTitle.text.toString().trim()
            val body= binding.etNotesBody.text.toString().trim()

            if(title.isNotEmpty()){
                val notes= Notes(currentNotes.id, title, body)
                notesViewModel.updateNotes(notes)
                view.findNavController().navigate(R.id.action_updatesFragment_to_homefragment)
                Toast.makeText(view.context, "Note is Updated Successfully", Toast.LENGTH_SHORT).show()
        }
            else{
                Toast.makeText(view.context, "Enter a new Note title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote(){
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Delete Notes")
                setMessage("You want to delete Notes")
                setPositiveButton("Delete", { _, _ ->
                    notesViewModel.deleteNotes(currentNotes)
                    view?.findNavController()?.navigate(R.id.action_updatesFragment_to_homefragment)
                }
                )

                setNegativeButton("Cancel", null)
            }.create().show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_notes, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete->{
                deleteNote()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}