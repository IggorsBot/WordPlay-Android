package com.gogabot.foreignwords.dictionary

import android.widget.TextView
import androidx.cardview.widget.CardView
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.dp


interface IDictionary {

    var id: Int
    var name: String
    var dictionaryLinearLayout: LinearLayout
    var dictionaryImage: ImageView
    var dictionaryText: TextView
    var dictionaryView: CardView

    fun initialLinearLayoutForDictionary()
    fun initialDictionaryImage()
    fun initialDictionaryText()
    fun initialDictionary()
}

private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


class Dictionary(private val context: Context): IDictionary {

    override var id: Int = 0
    override var name: String = "Новый словарь"

    override var dictionaryImage: ImageView = ImageView(context)
    override var dictionaryText: TextView = TextView(context)
    override var dictionaryLinearLayout: LinearLayout = LinearLayout(context)
    override var dictionaryView: CardView = CardView(context)

    init {
        initialDictionaryImage()
        initialDictionaryText()
        initialLinearLayoutForDictionary()
        initialDictionary()
    }

    override fun initialLinearLayoutForDictionary() {
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        dictionaryLinearLayout.layoutParams = params

        dictionaryLinearLayout.setPadding(
            0,
            context.resources.getDimension(R.dimen.dictionary_padding).dp,
            0,
            0
        )

        dictionaryLinearLayout.orientation = LinearLayout.VERTICAL
        dictionaryLinearLayout.gravity = Gravity.CENTER

        dictionaryLinearLayout.addView(dictionaryImage)
        dictionaryLinearLayout.addView(dictionaryText)
    }

    override fun initialDictionaryImage() {
        // Initialize a new ImageView to put in LinearLayout

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                context.resources.getDimension(R.dimen.card_image_size).dp,
                context.resources.getDimension(R.dimen.card_image_size).dp
            )

        dictionaryImage.layoutParams = params
        dictionaryImage.setImageResource(R.mipmap.ic_launcher_round)
    }

    override fun initialDictionaryText() {
        // Initialize a new TextView to put in LinearLayout

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        params.setMargins(
            0,
            context.resources.getDimension(R.dimen.card_text_margin).dp,
            0,
            0
        )
        dictionaryText.layoutParams = params

        dictionaryText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            context.resources.getDimension(R.dimen.dictionary_text_size)
        )

        dictionaryText.setTextColor(Color.parseColor("#6f6f6f"));
        dictionaryText.text = name
    }

    override fun initialDictionary() {

        val params: GridLayout.LayoutParams = GridLayout.LayoutParams(
            GridLayout.spec(
                GridLayout.UNDEFINED, GridLayout.FILL, 1f
            ),
            GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
        )
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.width  = LinearLayout.LayoutParams.WRAP_CONTENT;

        params.setMargins(
            context.resources.getDimension(R.dimen.card_margin_full).dp,
            context.resources.getDimension(R.dimen.card_margin_full).dp,
            context.resources.getDimension(R.dimen.card_margin_full).dp,
            context.resources.getDimension(R.dimen.card_margin_full).dp
        )

        dictionaryView.layoutParams = params
        dictionaryView.cardElevation = context.resources.getDimension(R.dimen.card_elevation)
        dictionaryView.radius = context.resources.getDimension(R.dimen.card_corner_radius)

        dictionaryView.addView(dictionaryLinearLayout)
    }
}