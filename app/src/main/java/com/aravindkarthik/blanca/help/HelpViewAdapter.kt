package com.aravindkarthik.blanca.help

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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