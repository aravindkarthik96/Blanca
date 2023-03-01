package com.aravindkarthik.blanca.help

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aravindkarthik.blanca.R
import com.aravindkarthik.blanca.databinding.BlancaHelpItemBinding
import com.aravindkarthik.blanca.lang.core.Function


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
        val binding = BlancaHelpItemBinding.bind(itemView)
        val documentation = function.documentation
        binding.title.text = documentation.title
        binding.description.text = documentation.description
        binding.exampleCode.text = documentation.exampleCode

        binding.copyFabButton.setOnClickListener {
            val clipboardManager = itemView.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val textToCopy = documentation.exampleCode
            val clip = ClipData.newPlainText(documentation.title,textToCopy)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(itemView.context, "text copied to the clipboard", Toast.LENGTH_SHORT).show()
        }

        binding.compileFabButton.setOnClickListener {
            binding.canvasView.clear()
            documentation.runExample.invoke(
                binding.canvasView,
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