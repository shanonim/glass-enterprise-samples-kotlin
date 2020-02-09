package com.shanonim.cardsample

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.shanonim.cardsample.infra.hideSystemUI
import com.shanonim.cardsample.utils.GlassGestureDetector

abstract class BaseActivity : AppCompatActivity(), GlassGestureDetector.OnGestureListener {

    private var glassGestureDetector: GlassGestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.decorView.hideSystemUI()
        glassGestureDetector = GlassGestureDetector(this, this)
    }

    override fun onResume() {
        super.onResume()
        window.decorView.hideSystemUI()
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        glassGestureDetector?.let { glassGestureDetector ->
            return if (glassGestureDetector.onTouchEvent(event)) {
                true
            } else super.dispatchTouchEvent(event)
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onGesture(gesture: GlassGestureDetector.Gesture?): Boolean {
        return when (gesture) {
            GlassGestureDetector.Gesture.SWIPE_DOWN -> {
                finish()
                true
            }
            else -> false
        }
    }
}
