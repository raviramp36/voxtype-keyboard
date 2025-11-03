package com.voxtype.keyboard.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.voxtype.keyboard.AnalyticsManager
import com.voxtype.keyboard.R
import com.voxtype.keyboard.TodayStatsModel
import com.voxtype.keyboard.WeeklyStatsModel
import com.voxtype.keyboard.adapter.TranscriptionHistoryAdapter
import com.voxtype.keyboard.database.TranscriptionEntry
import com.voxtype.keyboard.database.VoxTypeDatabase
import com.voxtype.keyboard.database.WordFrequency
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    
    private lateinit var analyticsManager: AnalyticsManager
    private lateinit var database: VoxTypeDatabase
    private lateinit var greetingText: TextView
    private lateinit var streakText: TextView
    private lateinit var statsCarousel: RecyclerView
    private lateinit var quickActionsCard: MaterialCardView
    private lateinit var recentActivityRecycler: RecyclerView
    
    // Transcription history views
    private lateinit var transcriptionHistoryRecycler: RecyclerView
    private lateinit var emptyHistoryLayout: LinearLayout
    private lateinit var searchHistoryButton: MaterialButton
    private lateinit var clearHistoryButton: MaterialButton
    private lateinit var searchInputLayout: TextInputLayout
    private lateinit var searchInput: TextInputEditText
    private lateinit var historyAdapter: TranscriptionHistoryAdapter
    
    private var allTranscriptions: List<TranscriptionEntry> = emptyList()
    private var isSearchVisible = false
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        analyticsManager = AnalyticsManager(requireContext())
        database = VoxTypeDatabase.getInstance(requireContext())
        
        initializeViews(view)
        setupStatsCarousel()
        setupTranscriptionHistory()
        loadData()
    }
    
    private fun initializeViews(view: View) {
        greetingText = view.findViewById(R.id.greeting_text)
        streakText = view.findViewById(R.id.streak_text)
        statsCarousel = view.findViewById(R.id.stats_carousel)
        quickActionsCard = view.findViewById(R.id.quick_actions_card)
        recentActivityRecycler = view.findViewById(R.id.recent_activity_recycler)
        
        // History views
        transcriptionHistoryRecycler = view.findViewById(R.id.transcription_history_recycler)
        emptyHistoryLayout = view.findViewById(R.id.empty_history_layout)
        searchHistoryButton = view.findViewById(R.id.search_history_button)
        clearHistoryButton = view.findViewById(R.id.clear_history_button)
        searchInputLayout = view.findViewById(R.id.search_input_layout)
        searchInput = view.findViewById(R.id.search_input)
        
        // Set greeting based on time
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour < 12 -> "Good morning!"
            hour < 17 -> "Good afternoon!"
            else -> "Good evening!"
        }
        greetingText.text = greeting
    }
    
    private fun setupStatsCarousel() {
        statsCarousel.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        
        // Add snap helper for carousel effect
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(statsCarousel)
    }
    
    private fun setupTranscriptionHistory() {
        // Initialize adapter
        historyAdapter = TranscriptionHistoryAdapter { transcription ->
            showDeleteConfirmationDialog(transcription)
        }
        
        transcriptionHistoryRecycler.layoutManager = LinearLayoutManager(requireContext())
        transcriptionHistoryRecycler.adapter = historyAdapter
        
        // Setup search button
        searchHistoryButton.setOnClickListener {
            toggleSearch()
        }
        
        // Setup clear history button
        clearHistoryButton.setOnClickListener {
            showClearHistoryDialog()
        }
        
        // Setup search functionality
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterTranscriptions(s.toString())
            }
        })
    }
    
    private fun loadData() {
        lifecycleScope.launch {
            // Load today's stats
            val todayStats = analyticsManager.getTodayStats()
            val weeklyStats = analyticsManager.getWeeklyStats()
            val streak = analyticsManager.getStreakDays()
            val topWords = analyticsManager.getMostUsedWords(10)
            
            // Load transcription history
            allTranscriptions = database.transcriptionDao().getRecent(50)
            
            // Update UI
            displayStats(todayStats, weeklyStats, streak, topWords)
            displayTranscriptionHistory(allTranscriptions)
        }
    }
    
    private fun displayStats(
        todayStats: TodayStatsModel,
        weeklyStats: WeeklyStatsModel,
        streak: Int,
        topWords: List<WordFrequency>
    ) {
        // Update streak
        streakText.text = "🔥 $streak day${if (streak != 1) "s" else ""}"
        
        // Setup stats carousel
        val statsCards = listOf(
            StatsCard(
                "Today",
                "${todayStats.totalWords}",
                "words typed",
                R.drawable.ic_today
            ),
            StatsCard(
                "This Week",
                "${weeklyStats.totalWords}",
                "words total",
                R.drawable.ic_week
            ),
            StatsCard(
                "Transcriptions",
                "${todayStats.totalTranscriptions}",
                "today",
                R.drawable.ic_mic
            ),
            StatsCard(
                "Vocabulary",
                "${weeklyStats.vocabularySize}",
                "unique words",
                R.drawable.ic_dictionary
            )
        )
        
        statsCarousel.adapter = StatsCarouselAdapter(statsCards)
        
        // Setup recent activity
        recentActivityRecycler.layoutManager = LinearLayoutManager(requireContext())
        recentActivityRecycler.adapter = RecentWordsAdapter(topWords.take(5))
    }
    
    private fun displayTranscriptionHistory(transcriptions: List<TranscriptionEntry>) {
        if (transcriptions.isEmpty()) {
            emptyHistoryLayout.visibility = View.VISIBLE
            transcriptionHistoryRecycler.visibility = View.GONE
        } else {
            emptyHistoryLayout.visibility = View.GONE
            transcriptionHistoryRecycler.visibility = View.VISIBLE
            historyAdapter.submitList(transcriptions)
        }
    }
    
    private fun toggleSearch() {
        isSearchVisible = !isSearchVisible
        searchInputLayout.visibility = if (isSearchVisible) View.VISIBLE else View.GONE
        
        if (isSearchVisible) {
            searchInput.requestFocus()
            searchHistoryButton.text = "Hide"
        } else {
            searchInput.text?.clear()
            searchHistoryButton.text = "Search"
            filterTranscriptions("")
        }
    }
    
    private fun filterTranscriptions(query: String) {
        val filteredList = if (query.isBlank()) {
            allTranscriptions
        } else {
            allTranscriptions.filter { transcription ->
                transcription.finalText.contains(query, ignoreCase = true) ||
                transcription.rawTranscription.contains(query, ignoreCase = true) ||
                transcription.appPackage?.contains(query, ignoreCase = true) == true
            }
        }
        displayTranscriptionHistory(filteredList)
    }
    
    private fun showDeleteConfirmationDialog(transcription: TranscriptionEntry) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Transcription")
            .setMessage("Are you sure you want to delete this transcription?")
            .setPositiveButton("Delete") { _, _ ->
                deleteTranscription(transcription)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showClearHistoryDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Clear All History")
            .setMessage("Are you sure you want to delete all transcription history? This action cannot be undone.")
            .setPositiveButton("Clear All") { _, _ ->
                clearAllHistory()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun deleteTranscription(transcription: TranscriptionEntry) {
        lifecycleScope.launch {
            try {
                // Note: We'd need to add a delete method to TranscriptionDao
                // For now, we'll filter it out from the current list
                allTranscriptions = allTranscriptions.filter { it.id != transcription.id }
                displayTranscriptionHistory(allTranscriptions)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    private fun clearAllHistory() {
        lifecycleScope.launch {
            try {
                // Note: We'd need to add a deleteAll method to TranscriptionDao
                // For now, we'll just clear the current list
                allTranscriptions = emptyList()
                displayTranscriptionHistory(allTranscriptions)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

data class StatsCard(
    val title: String,
    val value: String,
    val subtitle: String,
    val iconRes: Int
)

class StatsCarouselAdapter(
    private val statsCards: List<StatsCard>
) : RecyclerView.Adapter<StatsCarouselAdapter.StatsViewHolder>() {
    
    inner class StatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.stats_title)
        val valueText: TextView = view.findViewById(R.id.stats_value)
        val subtitleText: TextView = view.findViewById(R.id.stats_subtitle)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stats_card, parent, false)
        return StatsViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val stats = statsCards[position]
        holder.titleText.text = stats.title
        holder.valueText.text = stats.value
        holder.subtitleText.text = stats.subtitle
    }
    
    override fun getItemCount() = statsCards.size
}

class RecentWordsAdapter(
    private val words: List<WordFrequency>
) : RecyclerView.Adapter<RecentWordsAdapter.WordViewHolder>() {
    
    inner class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordText: TextView = view.findViewById(R.id.word_text)
        val frequencyText: TextView = view.findViewById(R.id.frequency_text)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_word, parent, false)
        return WordViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.wordText.text = word.word
        holder.frequencyText.text = "${word.frequency}x"
    }
    
    override fun getItemCount() = words.size
}