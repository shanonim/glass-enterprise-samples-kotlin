package com.shanonim.cardsample.card

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.shanonim.cardsample.R
import com.shanonim.cardsample.infra.BaseFragment

class ColumnFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_column, container, false)
        arguments?.let { arguments ->
            val imageView = ImageView(getActivity())
            imageView.setImageResource(arguments.getInt(IMAGE_KEY))
            val leftColumn = view.findViewById<RelativeLayout>(R.id.left_column)
            leftColumn.addView(imageView)
            val textView = TextView(getActivity())
            textView.setText(arguments.getString(TEXT_KEY))
            textView.textSize = TEXT_SIZE.toFloat()
            textView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL))
            val rightColumn = view.findViewById<RelativeLayout>(R.id.right_column)
            rightColumn.addView(textView)
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

    override fun onSingleTapUp() {}

    companion object {
        private const val IMAGE_KEY = "image_key"
        private const val TEXT_KEY = "text_key"
        private const val FOOTER_KEY = "footer_key"
        private const val TIMESTAMP_KEY = "timestamp_key"
        private const val TEXT_SIZE = 30

        fun newInstance(
            image: Int, text: String?, footer: String?,
            timestamp: String?
        ): ColumnFragment {
            val myFragment = ColumnFragment()
            val args = Bundle()
            args.putInt(IMAGE_KEY, image)
            args.putString(TEXT_KEY, text)
            args.putString(FOOTER_KEY, footer)
            args.putString(TIMESTAMP_KEY, timestamp)
            myFragment.setArguments(args)
            return myFragment
        }
    }
}