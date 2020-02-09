package com.shanonim.cardsample.card

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.shanonim.cardsample.R
import com.shanonim.cardsample.infra.BaseFragment

class MainLayoutFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.main_layout, container, false)
        arguments?.let { arguments ->
            val textView = TextView(getContext())
            textView.setText(
                arguments.getString(
                    TEXT_KEY,
                    getString(R.string.empty_string)
                )
            )
            textView.textSize = BODY_TEXT_SIZE.toFloat()
            textView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL))
            val bodyLayout = view.findViewById<RelativeLayout>(R.id.body_layout)
            bodyLayout.addView(textView)
            val footer = view.findViewById<TextView>(R.id.footer)
            footer.setText(
                arguments.getString(
                    FOOTER_KEY,
                    getString(R.string.empty_string)
                )
            )
            val timestamp = view.findViewById<TextView>(R.id.timestamp)
            timestamp.setText(
                arguments.getString(
                    TIMESTAMP_KEY,
                    getString(R.string.empty_string)
                )
            )
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(
                getActivity(),
                data?.getStringExtra(EXTRA_NAME),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        private const val TEXT_KEY = "text_key"
        private const val FOOTER_KEY = "footer_key"
        private const val TIMESTAMP_KEY = "timestamp_key"
        private const val EXTRA_NAME = "title"
        private const val BODY_TEXT_SIZE = 40

        fun newInstance(
            text: String?, footer: String?, timestamp: String?, menu: Int?
        ): MainLayoutFragment {
            val myFragment = MainLayoutFragment()
            val args = Bundle()
            args.putString(TEXT_KEY, text)
            args.putString(FOOTER_KEY, footer)
            args.putString(TIMESTAMP_KEY, timestamp)
            if (menu != null) {
                args.putInt(MENU_KEY, menu)
            }
            myFragment.setArguments(args)
            return myFragment
        }
    }
}