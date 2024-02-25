package com.example.mykeepnotes.noteAdapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mykeepnotes.databinding.ItemLayoutBinding
import com.example.mykeepnotes.fragments.homeFragmentDirections
import com.example.mykeepnotes.model.Notes
import java.util.Random

class NoteAdapter() :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

        private val differCallback = object : DiffUtil.ItemCallback<Notes>() {
            override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return (oldItem.id == newItem.id &&
                        oldItem.notesTitle == newItem.notesTitle &&
                        oldItem.notesBody == newItem.notesBody)
            }

            override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem == newItem
            }


        }

       val differ= AsyncListDiffer(this, differCallback)






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote= differ.currentList[position]
       holder.binding.tvNotesTitle.text= currentNote.notesTitle
        holder.binding.tvNotesBody.text=currentNote.notesBody

        val random= Random()
        val color= Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
            )

        holder.binding.ibColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
           val directions= homeFragmentDirections.actionHomefragmentToUpdatesFragment(currentNote)

            it.findNavController().navigate(directions)
        }
    }
}