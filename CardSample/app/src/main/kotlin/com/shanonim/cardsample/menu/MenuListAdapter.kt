package com.shanonim.cardsample.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shanonim.cardsample.R
import com.shanonim.cardsample.menu.GlassMenuItemViewHolder.OnItemChosenListener

class MenuListAdapter internal constructor(
    private val context: Context, diffCallback: DiffUtil.ItemCallback<GlassMenuItem?>,
    private val glassMenuItemList: List<GlassMenuItem>, private val onItemChosenListener: OnItemChosenListener
) : ListAdapter<GlassMenuItem?, GlassMenuItemViewHolder?>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassMenuItemViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.fragment_main, parent, false)
        return GlassMenuItemViewHolder(view, onItemChosenListener)
    }

    override fun onBindViewHolder(glassMenuItemViewHolder: GlassMenuItemViewHolder, position: Int) {
        glassMenuItemViewHolder.setGlassMenuItem(glassMenuItemList[position])
    }

    override fun getItemCount(): Int = glassMenuItemList.size
}