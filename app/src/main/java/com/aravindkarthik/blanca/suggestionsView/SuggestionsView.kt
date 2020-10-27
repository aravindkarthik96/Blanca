package com.aravindkarthik.blanca.suggestionsView

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aravindkarthik.blanca.lang.core.Function
import java.util.*

class SuggestionsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attributeSet, defStyle) {


    fun init(codeEditor: EditText, functions: MutableList<Function>) {
        val suggestionsViewAdapter = SuggestionsViewAdapter(
            suggestionClickListener = { suggestionText : String ->
                val userCursorPosition = codeEditor.selectionEnd
                codeEditor.text = codeEditor.text.insert(userCursorPosition,suggestionText)

                if (
                    userCursorPosition > 0 &&
                    userCursorPosition < codeEditor.text.length
                ) {
                    codeEditor.setSelection(userCursorPosition + suggestionText.length)
                }
            }
        )

        this.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        this.adapter = suggestionsViewAdapter

        val defaultList = mutableListOf("()",",")
        val functionsList = functions.map {
            it.getSuggestionText()
        }.sortedBy {
            it.toLowerCase(Locale.ROOT)
        }

        defaultList.addAll(functionsList)

        suggestionsViewAdapter.setData(defaultList)
    }

}