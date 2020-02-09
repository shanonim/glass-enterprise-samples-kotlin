package com.shanonim.cardsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shanonim.cardsample.fragments.BaseFragment
import com.shanonim.cardsample.fragments.ColumnLayoutFragment
import com.shanonim.cardsample.fragments.MainLayoutFragment
import com.shanonim.cardsample.utils.GlassGestureDetector
import java.util.ArrayList

class MainActivity : BaseActivity() {
    private val fragments: MutableList<BaseFragment> = ArrayList<BaseFragment>()
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_pager_layout)
        val screenSlidePagerAdapter = ScreenSlidePagerAdapter(
            getSupportFragmentManager()
        )
        viewPager = findViewById(R.id.viewPager)
        viewPager.setAdapter(screenSlidePagerAdapter)
        fragments.add(
            MainLayoutFragment
                .newInstance(
                    getString(R.string.text_sample), getString(R.string.footnote_sample),
                    getString(R.string.timestamp_sample), R.menu.main_menu
                )
        )
        fragments.add(
            MainLayoutFragment
                .newInstance(
                    getString(R.string.different_options), getString(R.string.empty_string),
                    getString(R.string.empty_string), R.menu.special_menu
                )
        )
        fragments.add(
            ColumnLayoutFragment
                .newInstance(
                    R.drawable.ic_note_50, getString(R.string.columns_sample),
                    getString(R.string.footnote_sample), getString(R.string.timestamp_sample)
                )
        )
        fragments.add(
            MainLayoutFragment
                .newInstance(
                    getString(R.string.like_this_sample), getString(R.string.empty_string),
                    getString(R.string.empty_string), null
                )
        )
        screenSlidePagerAdapter.notifyDataSetChanged()
        val tabLayout: TabLayout = findViewById(R.id.page_indicator)
        tabLayout.setupWithViewPager(viewPager, true)
    }

    override fun onGesture(gesture: GlassGestureDetector.Gesture?): Boolean {
        return when (gesture) {
            GlassGestureDetector.Gesture.TAP -> {
                fragments[viewPager.getCurrentItem()].onSingleTapUp()
                true
            }
            else -> super.onGesture(gesture)
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }
    }
}
