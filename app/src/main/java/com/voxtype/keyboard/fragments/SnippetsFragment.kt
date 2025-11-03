package com.voxtype.keyboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.voxtype.keyboard.R
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.entities.Snippet
import com.voxtype.keyboard.repository.SnippetRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class SnippetsFragment : Fragment() {
    
    private lateinit var snippetsRecycler: RecyclerView
    private lateinit var addSnippetFab: FloatingActionButton
    private lateinit var snippetsAdapter: SnippetsAdapter
    private lateinit var database: VoxTypeDatabase
    private lateinit var repository: SnippetRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_snippets, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        database = VoxTypeDatabase.getInstance(requireContext())
        repository = SnippetRepository(database)
        
        initializeViews(view)
        setupRecyclerView()
        observeData()
    }
    
    private fun initializeViews(view: View) {
        snippetsRecycler = view.findViewById(R.id.snippets_recycler)
        addSnippetFab = view.findViewById(R.id.add_snippet_fab)
        
        addSnippetFab.setOnClickListener {
            showAddSnippetDialog()
        }
    }
    
    private fun setupRecyclerView() {
        snippetsAdapter = SnippetsAdapter(
            onEditClick = { snippet -> showEditSnippetDialog(snippet) },
            onDeleteClick = { snippet -> deleteSnippet(snippet) },
            onUseClick = { snippet -> useSnippet(snippet) }
        )
        
        snippetsRecycler.layoutManager = LinearLayoutManager(requireContext())
        snippetsRecycler.adapter = snippetsAdapter
        
        // Add swipe to delete
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val snippet = snippetsAdapter.getSnippetAt(position)
                deleteSnippet(snippet)
            }
        })
        
        itemTouchHelper.attachToRecyclerView(snippetsRecycler)
    }
    
    private fun observeData() {
        // Observe the snippet list from repository
        lifecycleScope.launch {
            repository.getAllSnippets().collect { snippets ->
                snippetsAdapter.updateSnippets(snippets)
            }
        }
    }
    
    private fun showAddSnippetDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_snippet, null)
        
        val titleInput = dialogView.findViewById<TextInputEditText>(R.id.title_input)
        val contentInput = dialogView.findViewById<TextInputEditText>(R.id.content_input)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Snippet")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = titleInput.text.toString().trim()
                val content = contentInput.text.toString().trim()
                
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    addSnippet(title, content)
                } else {
                    Toast.makeText(requireContext(), "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showEditSnippetDialog(snippet: Snippet) {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_snippet, null)
        
        val titleInput = dialogView.findViewById<TextInputEditText>(R.id.title_input)
        val contentInput = dialogView.findViewById<TextInputEditText>(R.id.content_input)
        
        titleInput.setText(snippet.trigger)
        contentInput.setText(snippet.content)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Snippet")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val title = titleInput.text.toString().trim()
                val content = contentInput.text.toString().trim()
                
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    updateSnippet(snippet.copy(trigger = title, content = content))
                } else {
                    Toast.makeText(requireContext(), "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun addSnippet(title: String, content: String) {
        lifecycleScope.launch {
            try {
                repository.addSnippet(title, content, "General")
                Toast.makeText(requireContext(), "Snippet added successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to add snippet: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun updateSnippet(snippet: Snippet) {
        lifecycleScope.launch {
            try {
                repository.updateSnippet(snippet)
                Toast.makeText(requireContext(), "Snippet updated successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to update snippet: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun deleteSnippet(snippet: Snippet) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Snippet")
            .setMessage("Are you sure you want to delete '${snippet.trigger}'?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    try {
                        repository.deleteSnippet(snippet)
                        Toast.makeText(requireContext(), "Snippet deleted", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Failed to delete snippet: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun useSnippet(snippet: Snippet) {
        lifecycleScope.launch {
            try {
                // Increment usage count
                repository.incrementUsageCount(snippet.id)
                
                // Copy snippet content to clipboard
                val clipboard = requireContext().getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("VoxType Snippet", snippet.content)
                clipboard.setPrimaryClip(clip)
                
                Toast.makeText(requireContext(), "Snippet copied to clipboard", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to use snippet: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}