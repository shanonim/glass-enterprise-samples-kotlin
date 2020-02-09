package com.shanonim.cardsample.infra

import android.content.Intent
import androidx.fragment.app.Fragment
import com.shanonim.cardsample.menu.MenuActivity

abstract class BaseFragment : Fragment(), OnSingleTapUpListener {
    override fun onSingleTapUp() {
        arguments?.let {
            val menu: Int =
                it.getInt(
                    MENU_KEY,
                    MENU_DEFAULT_VALUE
                )
            if (menu != MENU_DEFAULT_VALUE) {
                val intent = Intent(getActivity(), MenuActivity::class.java)
                intent.putExtra(MENU_KEY, menu)
                startActivityForResult(intent,
                    REQUEST_CODE
                )
            }
        }
    }

    companion object {
        const val MENU_KEY = "menu_key"
        const val MENU_DEFAULT_VALUE = 0
        const val REQUEST_CODE = 205
    }
}
