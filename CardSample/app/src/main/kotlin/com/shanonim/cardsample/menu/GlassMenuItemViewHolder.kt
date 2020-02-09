package com.shanonim.cardsample.menu

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.shanonim.cardsample.R

class GlassMenuItemViewHolder(itemView: View, private val listener: OnItemChosenListener) :
    RecyclerView.ViewHolder(itemView) {
    private val icon: ImageView
    private val text: TextView
    private var glassMenuItem: GlassMenuItem? = null

    fun setGlassMenuItem(glassMenuItem: GlassMenuItem) {
        this.glassMenuItem = glassMenuItem
        icon.setImageDrawable(glassMenuItem.icon)
        text.text = glassMenuItem.text
    }

    interface OnItemChosenListener {
        fun onItemChosen(glassMenuItem: GlassMenuItem?)
    }

    companion object {
        private const val TEXT_SIZE_SP = 35
        private const val PADDING_16_DP = 16
        private const val PADDING_0_DP = 0
    }

    init {
        itemView.setOnClickListener { listener.onItemChosen(glassMenuItem) }
        val context = itemView.context
        icon = ImageView(context)
        icon.id = ViewCompat.generateViewId()
        text = TextView(context)
        text.textSize = TEXT_SIZE_SP.toFloat()
        text.setTypeface(Typeface.create(context.getString(R.string.light_font), Typeface.NORMAL))
        text.setPadding(
            PADDING_16_DP,
            PADDING_0_DP,
            PADDING_0_DP,
            PADDING_0_DP
        )
        val linearLayout = LinearLayout(context)
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(icon)
        linearLayout.addView(text)
        val relativeLayout = itemView.findViewById<RelativeLayout>(R.id.body_layout)
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.addView(linearLayout)
    }
}
