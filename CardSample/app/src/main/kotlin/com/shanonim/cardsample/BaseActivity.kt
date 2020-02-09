package com.shanonim.cardsample

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shanonim.cardsample.utils.GlassGestureDetector

abstract class BaseActivity : AppCompatActivity(), GlassGestureDetector.OnGestureListener {
    private var decorView: View? = null
    private var glassGestureDetector: GlassGestureDetector? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        decorView = getWindow().getDecorView()
        decorView!!
            .setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    hideSystemUI()
                }
            }
        glassGestureDetector = GlassGestureDetector(this, this)
    }

    protected override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        glassGestureDetector?.let { glassGestureDetector ->
            return if (glassGestureDetector.onTouchEvent(ev)) {
                true
            } else super.dispatchTouchEvent(ev)
        }
        return super.dispatchTouchEvent(ev)
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

    private fun hideSystemUI() {
        decorView!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}