package com.aravindkarthik.blanca

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_blanca_home.*

class BlancaHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blanca_home)
    }

    fun handleEditorTitleClick(view: View) {
        loggerContainer.visibility = View.GONE
        editorContainer.visibility = View.VISIBLE
        loggerTitle.setTextColor(getColor(R.color.blanca_text_de_selected))
        editorTitle.setTextColor(getColor(R.color.blanca_text_selected))
    }

    fun handleLoggerTitleClick(view: View) {
        loggerContainer.visibility = View.VISIBLE
        editorContainer.visibility = View.GONE
        loggerTitle.setTextColor(getColor(R.color.blanca_text_selected))
        editorTitle.setTextColor(getColor(R.color.blanca_text_de_selected))

    }
}
