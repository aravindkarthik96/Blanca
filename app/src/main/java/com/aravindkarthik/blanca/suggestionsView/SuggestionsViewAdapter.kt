package com.aravindkarthik.blanca.suggestionsView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aravindkarthik.blanca.R
import kotlinx.android.synthetic.main.suggestion_pill.view.*

class SuggestionsViewAdapter(
    private val suggestionClickListener: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var suggestions: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SuggestionsViewHolder.getViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SuggestionsViewHolder).bind(suggestions[position], suggestionClickListener)
    }

    fun setData(suggestions : List<String>) {
        this.suggestions = suggestions.filter { it.isNotBlank() }
        notifyDataSetChanged()
    }

}

class SuggestionsViewHolder(itemView : ViewGroup) : RecyclerView.ViewHolder(itemView) {
    fun bind(suggestionText: String, suggestionClickListener: (String) -> Unit) {
        itemView.suggestionText.text = suggestionText.trim()
        itemView.setOnClickListener {
            suggestionClickListener.invoke(suggestionText)
        }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup): SuggestionsViewHolder {
            return SuggestionsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.suggestion_pill, parent, false) as ViewGroup
            )
        }
    }
}
