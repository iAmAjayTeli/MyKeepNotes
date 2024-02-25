package com.example.mykeepnotes.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.BaseObservable
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mykeepnotes.MainActivity
import com.example.mykeepnotes.R
import com.example.mykeepnotes.databinding.FragmentHomefragmentBinding
import com.example.mykeepnotes.model.Notes
import com.example.mykeepnotes.noteAdapter.NoteAdapter
import com.example.mykeepnotes.viewModel.NotesViewModel


class homeFragment : Fragment(R.layout.fragment_homefragment), SearchView.OnQueryTextListener {
    private var _binding:FragmentHomefragmentBinding?=null
    private val binding get()= _binding!!
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=FragmentHomefragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).notesViewModel

        setUpRecyclerview()

        binding.floatingBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homefragment_to_addNotesFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun setUpRecyclerview() {
      notesAdapter= NoteAdapter()
        binding.recylerview.apply {
           layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter=notesAdapter
        }

        activity?.let {
        notesViewModel.getAllNotes().observe(viewLifecycleOwner, {note->
            notesAdapter.differ.submitList(note)
            updateUI(note)
        })

        }
    }

    private fun updateUI(note: List<Notes>?){
        if (note!!.isNotEmpty()) {
            binding.cardview.visibility = View.GONE
            binding.recylerview.visibility = View.VISIBLE
        } else {
            binding.cardview.visibility = View.VISIBLE
            binding.recylerview.visibility = View.GONE
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        val menuSearch= menu.findItem(R.id.searchNote).actionView as SearchView
        menuSearch.isSubmitButtonEnabled=false
        menuSearch.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//       SearchNote(query)
        return  false
    }


    override fun onQueryTextChange(newText: String?): Boolean {
       if(newText!=null){
           SearchNote(newText)
       }
        return true
    }

    private fun SearchNote(query: String?) {
        val searchQuery= "%$query"
        notesViewModel.searchNotes(searchQuery).observe(this, {list-> notesAdapter.differ.submitList(list)})
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}