package com.voxtype.keyboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
import com.google.android.material.textfield.TextInputLayout
import com.voxtype.keyboard.R
import com.voxtype.keyboard.database.entities.UserDictionaryEntry
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.viewmodel.DictionaryViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.voxtype.keyboard.repository.UserDictionaryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class DictionaryFragment : Fragment() {
    
    private lateinit var dictionaryRecycler: RecyclerView
    private lateinit var addWordFab: FloatingActionButton
    private lateinit var dictionaryAdapter: DictionaryAdapter
    private lateinit var database: VoxTypeDatabase
    private lateinit var repository: UserDictionaryRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        database = VoxTypeDatabase.getInstance(requireContext())
        repository = UserDictionaryRepository(database)
        
        initializeViews(view)
        setupRecyclerView()
        observeData()
    }
    
    private fun initializeViews(view: View) {
        dictionaryRecycler = view.findViewById(R.id.dictionary_recycler)
        addWordFab = view.findViewById(R.id.add_word_fab)
        
        addWordFab.setOnClickListener {
            showAddWordDialog()
        }
    }
    
    private fun setupRecyclerView() {
        dictionaryAdapter = DictionaryAdapter(
            onEditClick = { word -> showEditWordDialog(word) },
            onDeleteClick = { word -> deleteWord(word) }
        )
        
        dictionaryRecycler.layoutManager = LinearLayoutManager(requireContext())
        dictionaryRecycler.adapter = dictionaryAdapter
        
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
                val word = dictionaryAdapter.getWordAt(position)
                deleteWord(word)
            }
        })
        
        itemTouchHelper.attachToRecyclerView(dictionaryRecycler)
    }
    
    private fun observeData() {
        // Observe the word list from repository
        lifecycleScope.launch {
            repository.getAllWords().collect { words ->
                dictionaryAdapter.updateWords(words)
            }
        }
    }
    
    private fun showAddWordDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_word, null)
        
        val wordInput = dialogView.findViewById<TextInputEditText>(R.id.word_input)
        val replacementInput = dialogView.findViewById<TextInputEditText>(R.id.replacement_input)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Word")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val word = wordInput.text.toString().trim()
                val replacement = replacementInput.text.toString().trim()
                
                if (word.isNotEmpty()) {
                    addWord(word, replacement.ifEmpty { word })
                } else {
                    Toast.makeText(requireContext(), "Word cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showEditWordDialog(wordEntry: UserDictionaryEntry) {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_word, null)
        
        val wordInput = dialogView.findViewById<TextInputEditText>(R.id.word_input)
        val replacementInput = dialogView.findViewById<TextInputEditText>(R.id.replacement_input)
        
        wordInput.setText(wordEntry.word)
        replacementInput.setText(wordEntry.replacement)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Word")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val word = wordInput.text.toString().trim()
                val replacement = replacementInput.text.toString().trim()
                
                if (word.isNotEmpty()) {
                    updateWord(wordEntry.copy(word = word, replacement = replacement.ifEmpty { word }))
                } else {
                    Toast.makeText(requireContext(), "Word cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun addWord(word: String, replacement: String) {
        lifecycleScope.launch {
            try {
                repository.addWord(word, replacement)
                Toast.makeText(requireContext(), "Word added successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to add word: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun updateWord(wordEntry: UserDictionaryEntry) {
        lifecycleScope.launch {
            try {
                repository.updateWord(wordEntry)
                Toast.makeText(requireContext(), "Word updated successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Failed to update word: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun deleteWord(wordEntry: UserDictionaryEntry) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Word")
            .setMessage("Are you sure you want to delete '${wordEntry.word}'?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    try {
                        repository.deleteWord(wordEntry)
                        Toast.makeText(requireContext(), "Word deleted", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Failed to delete word: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}