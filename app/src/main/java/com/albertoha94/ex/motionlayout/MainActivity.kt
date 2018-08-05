package com.albertoha94.ex.motionlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.view.View
import android.widget.SeekBar
import java.lang.Math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var motionContainer: MotionLayout
    private lateinit var seekbar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motionlayout)
        motionContainer = findViewById(R.id.motion_container)
        seekbar = findViewById(R.id.seekbar)

        motionContainer.setTransitionListener(
                object : MotionLayout.TransitionListener {
                    override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                        seekbar.progress = ceil((progress * 100).toDouble()).toInt()
                    }

                    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                        if (currentId == R.id.ending_set) {
                            // Return to original constraint set
                            motionContainer.transitionToStart()
                        }
                    }
                }
        )
    }

    fun start(v: View) {
        motionContainer.transitionToEnd()
    }
}