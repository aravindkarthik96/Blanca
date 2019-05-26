package com.aravindkarthik.blanca

import android.app.Activity
import android.app.ActivityManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

private const val MIN_OPENGL_VERSION = 3.0

class HomeActivity : AppCompatActivity() {

    private var testModel: ModelRenderable? = null
    private var arFragment: ArFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return
        }

        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.canvasView) as ArFragment


        ModelRenderable.builder()
            .setSource(this, R.raw.andy)
            .build()
            .thenAccept { renderable -> testModel = renderable }
            .exceptionally { _ -> null }


        run_code.setOnClickListener {
            interpretCode()
        }

//        canvasView.setOnTapArPlaneListener(
//            { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
//                if (testModel == null) {
//                    return@arFragment.setOnTapArPlaneListener
//                }
//
//                // Create the Anchor.
//                val anchor = hitResult.createAnchor()
//                val anchorNode = AnchorNode(anchor)
//                anchorNode.setParent(arFragment.getArSceneView().getScene())
//
//                // Create the transformable andy and add it to the anchor.
//                val andy = TransformableNode(arFragment.getTransformationSystem())
//                andy.setParent(anchorNode)
//                andy.renderable = testModel
//                andy.select()
//            })

//        canvasView.setupSession()
    }

    private fun interpretCode() {
        val codeEditable = code_editor.text
        val codeString = codeEditable.toString()

        codeEditable.lines().forEachIndexed { index, codeLine ->
            when {
                codeLine.startsWith("drawLine") -> handleDraw(index,codeLine)
                codeLine.startsWith("//") -> handleComments()
                codeLine.startsWith("delay") -> handleDelay(codeLine, index)
                codeLine.startsWith("$") -> handleVariables()
                else -> showError(index, codeLine)
            }
        }

    }

    private fun handleVariables() {

    }

    private fun handleDelay(codeLine: String, index: Int) {
        when {
            codeLine.startsWith("delay(") && codeLine.endsWith(")") -> {
                Log.d("BLANCA INTERPRETER", "pausing for")
            }
            else -> showError(index, codeLine)
        }
    }

    private fun handleComments() {
        Log.d("BLANCA INTERPRETER", "ignoring comment")
    }

    private fun showError(index: Int, codeLine: String) {
        Log.d("BLANCA INTERPRETER", "UNKNOWN SYNTAX AT $index : $codeLine")
    }

    private fun handleDraw(index: Int, codeLine: String) {
        Log.d("BLANCA INTERPRETER", "Draw handler: drawing")
        when {
            codeLine.startsWith("drawLine(") && codeLine.endsWith(")") -> drawLine(codeLine)
            else -> showError(index, codeLine)
        }
    }

    private fun drawLine(codeLine: String) {
        //canvasView.drawLine()
    }
}

fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
    val openGlVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
        .deviceConfigurationInfo
        .glEsVersion
    if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
        Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
        Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
            .show()
        activity.finish()
        return false
    }
    return true
}