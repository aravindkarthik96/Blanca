package com.aravindkarthik.blanca.help

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.lang.core.Function
import kotlinx.android.synthetic.main.blanca_help_item.view.*


class HelpViewAdapter(private val functions: List<Function>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return HelpViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HelpViewHolder).bind(functions[position])
    }

    override fun getItemCount(): Int {
        return functions.size
    }
}

private class HelpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(function: Function) {
        val documentation = function.documentation
        itemView.title.text = documentation.title
        itemView.description.text = documentation.description
        itemView.exampleCode.text = documentation.exampleCode

        itemView.copyFabButton.setOnClickListener {
            val clipboardManager = itemView.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val textToCopy = documentation.exampleCode
            val clip = ClipData.newPlainText(documentation.title,textToCopy)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(itemView.context, "text copied to the clipboard", Toast.LENGTH_SHORT).show()
        }

        itemView.compileFabButton.setOnClickListener {
            documentation.runExample.invoke(
                itemView.canvasView,
                documentation.exampleCode
            )
        }
    }

    companion object {
        fun getViewHolder(parent: ViewGroup): HelpViewHolder {
            return HelpViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.blanca_help_item, parent, false) as ViewGroup
            )
        }
    }

}