package com.voxtype.keyboard.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.voxtype.keyboard.R
import com.voxtype.keyboard.database.entities.Snippet

class SnippetsAdapter(
    private val onEditClick: (Snippet) -> Unit,
    private val onDeleteClick: (Snippet) -> Unit,
    private val onUseClick: (Snippet) -> Unit
) : RecyclerView.Adapter<SnippetsAdapter.SnippetViewHolder>() {
    
    private var snippets = listOf<Snippet>()
    
    inner class SnippetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.snippet_title)
        val contentText: TextView = view.findViewById(R.id.snippet_content)
        val useButton: MaterialButton = view.findViewById(R.id.use_button)
        val editButton: ImageButton = view.findViewById(R.id.edit_button)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_button)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnippetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_snippet, parent, false)
        return SnippetViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: SnippetViewHolder, position: Int) {
        val snippet = snippets[position]
        
        holder.titleText.text = snippet.trigger
        holder.contentText.text = snippet.content
        
        // Limit content preview to 2 lines
        holder.contentText.maxLines = 2
        
        holder.useButton.setOnClickListener {
            onUseClick(snippet)
        }
        
        holder.editButton.setOnClickListener {
            onEditClick(snippet)
        }
        
        holder.deleteButton.setOnClickListener {
            onDeleteClick(snippet)
        }
    }
    
    override fun getItemCount() = snippets.size
    
    fun updateSnippets(newSnippets: List<Snippet>) {
        snippets = newSnippets
        notifyDataSetChanged()
    }
    
    fun getSnippetAt(position: Int): Snippet {
        return snippets[position]
    }
}