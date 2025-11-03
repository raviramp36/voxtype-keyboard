package com.voxtype.keyboard.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voxtype.keyboard.R
import com.voxtype.keyboard.database.TranscriptionEntry
import java.text.SimpleDateFormat
import java.util.*

class TranscriptionHistoryAdapter(
    private val onDeleteClick: (TranscriptionEntry) -> Unit
) : ListAdapter<TranscriptionEntry, TranscriptionHistoryAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timestampText: TextView = itemView.findViewById(R.id.timestamp_text)
        val transcriptionText: TextView = itemView.findViewById(R.id.transcription_text)
        val appName: TextView = itemView.findViewById(R.id.app_name)
        val textMode: TextView = itemView.findViewById(R.id.text_mode)
        val copyButton: ImageButton = itemView.findViewById(R.id.copy_button)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transcription_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        
        // Format timestamp
        holder.timestampText.text = formatTimestamp(item.timestamp)
        
        // Show final text (enhanced) if available, otherwise raw transcription
        holder.transcriptionText.text = item.finalText.ifEmpty { item.rawTranscription }
        
        // Extract app name from package
        holder.appName.text = extractAppName(item.appPackage)
        
        // Show text mode
        holder.textMode.text = item.textMode.uppercase()
        
        // Set up copy button
        holder.copyButton.setOnClickListener {
            copyTextToClipboard(holder.itemView.context, item.finalText.ifEmpty { item.rawTranscription })
        }
        
        // Set up delete button
        holder.deleteButton.setOnClickListener {
            onDeleteClick(item)
        }
        
        // Make the whole item clickable for copying
        holder.itemView.setOnClickListener {
            copyTextToClipboard(holder.itemView.context, item.finalText.ifEmpty { item.rawTranscription })
        }
    }

    private fun formatTimestamp(timestamp: Date): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp.time
        
        return when {
            diff < DateUtils.MINUTE_IN_MILLIS -> "Just now"
            diff < DateUtils.HOUR_IN_MILLIS -> "${diff / DateUtils.MINUTE_IN_MILLIS} min ago"
            diff < DateUtils.DAY_IN_MILLIS -> "${diff / DateUtils.HOUR_IN_MILLIS} hr ago"
            diff < 7 * DateUtils.DAY_IN_MILLIS -> "${diff / DateUtils.DAY_IN_MILLIS} days ago"
            else -> SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(timestamp)
        }
    }

    private fun extractAppName(packageName: String?): String {
        if (packageName.isNullOrEmpty()) return "Unknown App"
        
        return when {
            packageName.contains("whatsapp") -> "WhatsApp"
            packageName.contains("telegram") -> "Telegram"
            packageName.contains("gmail") -> "Gmail"
            packageName.contains("outlook") -> "Outlook"
            packageName.contains("slack") -> "Slack"
            packageName.contains("teams") -> "Teams"
            packageName.contains("linkedin") -> "LinkedIn"
            packageName.contains("chrome") -> "Chrome"
            packageName.contains("firefox") -> "Firefox"
            packageName.contains("message") -> "Messages"
            packageName.contains("docs") -> "Google Docs"
            packageName.contains("sheets") -> "Google Sheets"
            else -> {
                // Extract the last part of package name and capitalize
                packageName.split(".").lastOrNull()?.replaceFirstChar { 
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                } ?: "Unknown App"
            }
        }
    }

    private fun copyTextToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Transcription", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    class DiffCallback : DiffUtil.ItemCallback<TranscriptionEntry>() {
        override fun areItemsTheSame(oldItem: TranscriptionEntry, newItem: TranscriptionEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TranscriptionEntry, newItem: TranscriptionEntry): Boolean {
            return oldItem == newItem
        }
    }
}