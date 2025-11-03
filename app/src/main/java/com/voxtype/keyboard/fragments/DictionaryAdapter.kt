package com.voxtype.keyboard.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voxtype.keyboard.R
import com.voxtype.keyboard.database.entities.UserDictionaryEntry

class DictionaryAdapter(
    private val onEditClick: (UserDictionaryEntry) -> Unit,
    private val onDeleteClick: (UserDictionaryEntry) -> Unit
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {
    
    private var words = listOf<UserDictionaryEntry>()
    
    inner class DictionaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordText: TextView = view.findViewById(R.id.word_text)
        val replacementText: TextView = view.findViewById(R.id.replacement_text)
        val editButton: ImageButton = view.findViewById(R.id.edit_button)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_button)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dictionary_word, parent, false)
        return DictionaryViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val word = words[position]
        
        holder.wordText.text = word.word
        holder.replacementText.text = if (word.replacement != word.word) {
            "→ ${word.replacement}"
        } else {
            ""
        }
        
        holder.editButton.setOnClickListener {
            onEditClick(word)
        }
        
        holder.deleteButton.setOnClickListener {
            onDeleteClick(word)
        }
        
        // Hide replacement text if it's the same as the word
        holder.replacementText.visibility = if (word.replacement != word.word) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    
    override fun getItemCount() = words.size
    
    fun updateWords(newWords: List<UserDictionaryEntry>) {
        words = newWords
        notifyDataSetChanged()
    }
    
    fun getWordAt(position: Int): UserDictionaryEntry {
        return words[position]
    }
}