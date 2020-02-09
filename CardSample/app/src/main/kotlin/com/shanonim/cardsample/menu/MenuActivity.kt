package com.shanonim.cardsample.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.shanonim.cardsample.infra.BaseActivity
import com.shanonim.cardsample.R
import com.shanonim.cardsample.menu.GlassMenuItem.ItemDiffComparator
import com.shanonim.cardsample.menu.GlassMenuItemViewHolder.OnItemChosenListener
import java.util.ArrayList

class MenuActivity : BaseActivity(), OnItemChosenListener {
    private var adapter: MenuListAdapter? = null
    private val menuItems: MutableList<GlassMenuItem> = ArrayList()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView: RecyclerView = getLayoutInflater()
            .inflate(R.layout.activity_menu, null, false) as RecyclerView
        adapter = MenuListAdapter(
            this, ItemDiffComparator(), menuItems,
            this
        )
        recyclerView
            .setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        recyclerView.setAdapter(adapter)
        recyclerView.setFocusable(true)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        setContentView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuResource: Int =
            getIntent().getIntExtra(MENU_KEY, DEFAULT_MENU_VALUE)
        if (menuResource != DEFAULT_MENU_VALUE) {
            val inflater: MenuInflater = getMenuInflater()
            inflater.inflate(menuResource, menu)
            for (i in 0 until menu.size()) {
                val menuItem = menu.getItem(i)
                menuItems.add(
                    GlassMenuItem(menuItem.icon, menuItem.title.toString())
                )
                adapter?.notifyDataSetChanged()
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemChosen(glassMenuItem: GlassMenuItem?) {
        val intent = Intent()
        intent.putExtra(EXTRA_NAME, glassMenuItem?.text)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val MENU_KEY = "menu_key"
        private const val EXTRA_NAME = "title"
        private const val DEFAULT_MENU_VALUE = -1
    }
}
