package com.shanonim.cardsample.menu

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import java.util.Objects

class GlassMenuItem(val icon: Drawable, val text: String) {

    class ItemDiffComparator : DiffUtil.ItemCallback<GlassMenuItem?>() {
        override fun areItemsTheSame(oldItem: GlassMenuItem, newItem: GlassMenuItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GlassMenuItem, newItem: GlassMenuItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as GlassMenuItem
        return icon == that.icon &&
            text == that.text
    }

    override fun hashCode(): Int {
        return Objects.hash(icon, text)
    }
}
