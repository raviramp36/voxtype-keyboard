package com.voxtype.keyboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.voxtype.keyboard.database.*
import kotlinx.coroutines.launch
import android.animation.ValueAnimator
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar

class StatsActivity : AppCompatActivity() {
    
    private lateinit var analyticsManager: AnalyticsManager
    
    // Header Views
    private lateinit var greetingText: TextView
    private lateinit var headerTodayWords: TextView
    private lateinit var headerStreak: TextView
    
    // Today Stats Views
    private lateinit var todayWordsText: TextView
    private lateinit var todayTranscriptionsText: TextView
    private lateinit var todayDurationText: TextView
    private lateinit var todayAvgWordsText: TextView
    
    // Weekly Stats Views
    private lateinit var weeklyWordsText: TextView
    private lateinit var weeklyAvgText: TextView
    private lateinit var vocabularySizeText: TextView
    
    // Charts
    private lateinit var weeklyChart: BarChart
    private lateinit var hourlyChart: LineChart
    private lateinit var appsChart: PieChart
    private lateinit var wordsCloudRecycler: RecyclerView
    
    // Tabs
    private lateinit var tabLayout: TabLayout
    private lateinit var todayCard: MaterialCardView
    private lateinit var weeklyCard: MaterialCardView
    private lateinit var correctionsCard: MaterialCardView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        
        analyticsManager = AnalyticsManager(this)
        
        initializeViews()
        setupTabs()
        loadStats()
    }
    
    private fun initializeViews() {
        // Header views
        greetingText = findViewById(R.id.greeting_text)
        headerTodayWords = findViewById(R.id.header_today_words)
        headerStreak = findViewById(R.id.header_streak)
        
        // Set greeting based on time
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        greetingText.text = when {
            hour < 12 -> "Good morning!"
            hour < 17 -> "Good afternoon!"
            else -> "Good evening!"
        }
        
        // Today stats
        todayWordsText = findViewById(R.id.today_words_count)
        todayTranscriptionsText = findViewById(R.id.today_transcriptions_count)
        todayDurationText = findViewById(R.id.today_duration)
        todayAvgWordsText = findViewById(R.id.today_avg_words)
        
        // Weekly stats
        weeklyWordsText = findViewById(R.id.weekly_words_count)
        weeklyAvgText = findViewById(R.id.weekly_avg_words)
        vocabularySizeText = findViewById(R.id.vocabulary_size)
        
        // Charts
        weeklyChart = findViewById(R.id.weekly_chart)
        hourlyChart = findViewById(R.id.hourly_chart)
        appsChart = findViewById(R.id.apps_chart)
        wordsCloudRecycler = findViewById(R.id.words_cloud_recycler)
        
        // Cards
        tabLayout = findViewById(R.id.tab_layout)
        todayCard = findViewById(R.id.today_card)
        weeklyCard = findViewById(R.id.weekly_card)
        correctionsCard = findViewById(R.id.corrections_card)
        
        // Setup charts
        setupWeeklyChart()
        setupHourlyChart()
        setupAppsChart()
    }
    
    private fun setupTabs() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showTodayStats()
                    1 -> showWeeklyStats()
                    2 -> showCorrections()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    
    private fun loadStats() {
        lifecycleScope.launch {
            // Load today's stats
            val todayStats = analyticsManager.getTodayStats()
            displayTodayStats(todayStats)
            
            // Load weekly stats
            val weeklyStats = analyticsManager.getWeeklyStats()
            displayWeeklyStats(weeklyStats)
            
            // Load most used words
            val topWords = analyticsManager.getMostUsedWords(30)
            displayWordCloud(topWords)
        }
    }
    
    private fun displayTodayStats(stats: TodayStatsModel) {
        runOnUiThread {
            // Update header
            headerTodayWords.text = "${stats.totalWords}"
            updateStreak()
            
            // Update cards with animation
            todayWordsText.text = "${stats.totalWords}"
            todayTranscriptionsText.text = "${stats.totalTranscriptions}"
            todayDurationText.text = stats.totalDuration.formatDuration()
            todayAvgWordsText.text = "%.1f".format(stats.averageWordsPerTranscription)
            
            // Animate the values
            animateValue(todayWordsText, 0, stats.totalWords)
            animateValue(todayTranscriptionsText, 0, stats.totalTranscriptions)
            
            // Update hourly chart with today's transcriptions
            updateHourlyChart(stats.transcriptions)
        }
    }
    
    private fun animateValue(textView: TextView, from: Int, to: Int) {
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }
    
    private fun updateStreak() {
        lifecycleScope.launch {
            val streak = analyticsManager.getStreakDays()
            runOnUiThread {
                headerStreak.text = "🔥 $streak"
            }
        }
    }
    
    private fun displayWeeklyStats(stats: WeeklyStatsModel) {
        runOnUiThread {
            weeklyWordsText.text = "${stats.totalWords}"
            weeklyAvgText.text = "%.1f".format(stats.averageWordsPerDay)
            vocabularySizeText.text = "${stats.vocabularySize} unique words"
            
            // Update weekly chart
            updateWeeklyChart(stats.dailyStats)
        }
    }
    
    private fun displayWordCloud(words: List<WordFrequency>) {
        runOnUiThread {
            wordsCloudRecycler.layoutManager = LinearLayoutManager(
                this, 
                LinearLayoutManager.HORIZONTAL, 
                false
            )
            wordsCloudRecycler.adapter = WordsAdapter(words)
        }
    }
    
    private fun setupWeeklyChart() {
        weeklyChart.apply {
            description.isEnabled = false
            setDrawValueAboveBar(true)
            setPinchZoom(false)
            setDrawGridBackground(false)
            setTouchEnabled(true)
            isDragEnabled = true
            
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.textColor = Color.parseColor("#757575")
            axisLeft.setDrawGridLines(false)
            axisLeft.textColor = Color.parseColor("#757575")
            axisRight.isEnabled = false
            legend.isEnabled = false
            
            // Add animation
            animateY(1500)
        }
    }
    
    private fun updateWeeklyChart(dailyStats: List<DailyStats>) {
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        val dateFormat = SimpleDateFormat("EEE", Locale.getDefault())
        
        dailyStats.forEachIndexed { index, stat ->
            entries.add(BarEntry(index.toFloat(), stat.totalWords.toFloat()))
            labels.add(dateFormat.format(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(stat.date)))
        }
        
        val dataSet = BarDataSet(entries, "Words per day").apply {
            color = Color.parseColor("#4CAF50")
            valueTextSize = 10f
        }
        
        weeklyChart.data = BarData(dataSet)
        weeklyChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        weeklyChart.invalidate()
    }
    
    private fun setupHourlyChart() {
        hourlyChart.apply {
            description.isEnabled = false
            setDrawGridBackground(false)
            setPinchZoom(false)
            setTouchEnabled(true)
            isDragEnabled = true
            
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.textColor = Color.parseColor("#757575")
            axisLeft.setDrawGridLines(false)
            axisLeft.textColor = Color.parseColor("#757575")
            axisRight.isEnabled = false
            legend.isEnabled = false
            
            // Add animation
            animateY(1500)
        }
    }
    
    private fun updateHourlyChart(transcriptions: List<TranscriptionEntry>) {
        val hourCounts = IntArray(24)
        val hourFormat = SimpleDateFormat("HH", Locale.getDefault())
        
        transcriptions.forEach { entry ->
            val hour = hourFormat.format(entry.timestamp).toInt()
            hourCounts[hour]++
        }
        
        val entries = ArrayList<Entry>()
        hourCounts.forEachIndexed { hour, count ->
            if (count > 0) {
                entries.add(Entry(hour.toFloat(), count.toFloat()))
            }
        }
        
        val dataSet = LineDataSet(entries, "Transcriptions by hour").apply {
            color = Color.parseColor("#2196F3")
            setCircleColor(Color.parseColor("#2196F3"))
            lineWidth = 2f
            circleRadius = 3f
            valueTextSize = 9f
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        
        hourlyChart.data = LineData(dataSet)
        hourlyChart.xAxis.valueFormatter = IndexAxisValueFormatter(
            (0..23).map { "$it:00" }
        )
        hourlyChart.invalidate()
    }
    
    private fun setupAppsChart() {
        appsChart.apply {
            description.isEnabled = false
            isRotationEnabled = true
            setUsePercentValues(true)
            holeRadius = 40f
            transparentCircleRadius = 45f
            setDrawCenterText(true)
            centerText = "Apps"
        }
    }
    
    private fun showTodayStats() {
        todayCard.visibility = View.VISIBLE
        weeklyCard.visibility = View.GONE
        correctionsCard.visibility = View.GONE
    }
    
    private fun showWeeklyStats() {
        todayCard.visibility = View.GONE
        weeklyCard.visibility = View.VISIBLE
        correctionsCard.visibility = View.GONE
    }
    
    private fun showCorrections() {
        todayCard.visibility = View.GONE
        weeklyCard.visibility = View.GONE
        correctionsCard.visibility = View.VISIBLE
        loadCorrections()
        loadWordCloud()
    }
    
    private fun loadWordCloud() {
        lifecycleScope.launch {
            val topWords = analyticsManager.getMostUsedWords(20)
            displayWordCloud(topWords)
        }
    }
    
    private fun loadCorrections() {
        lifecycleScope.launch {
            // Load corrections from database
            val database = VoxTypeDatabase.getInstance(this@StatsActivity)
            val corrections = database.correctionDao().getMostFrequent(50)
            
            // Display in RecyclerView
            runOnUiThread {
                val correctionsRecycler = findViewById<RecyclerView>(R.id.corrections_recycler)
                correctionsRecycler.layoutManager = LinearLayoutManager(this@StatsActivity)
                correctionsRecycler.adapter = CorrectionsAdapter(corrections)
            }
        }
    }
}

// Adapters for RecyclerViews
class WordsAdapter(private val words: List<WordFrequency>) : 
    RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {
    
    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordText: TextView = view.findViewById(R.id.word_text)
        val frequencyText: TextView = view.findViewById(R.id.frequency_text)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.wordText.text = word.word
        holder.frequencyText.text = "${word.frequency}x"
        
        // Adjust text size based on frequency
        val size = 14f + (word.frequency.coerceAtMost(20) * 0.5f)
        holder.wordText.textSize = size
    }
    
    override fun getItemCount() = words.size
}

class CorrectionsAdapter(private val corrections: List<CorrectionEntry>) :
    RecyclerView.Adapter<CorrectionsAdapter.CorrectionViewHolder>() {
    
    class CorrectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val originalText: TextView = view.findViewById(R.id.original_text)
        val correctedText: TextView = view.findViewById(R.id.corrected_text)
        val frequencyText: TextView = view.findViewById(R.id.frequency_text)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorrectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_correction, parent, false)
        return CorrectionViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: CorrectionViewHolder, position: Int) {
        val correction = corrections[position]
        holder.originalText.text = correction.originalText
        holder.correctedText.text = correction.correctedText
        holder.frequencyText.text = "${correction.frequency}x"
    }
    
    override fun getItemCount() = corrections.size
}