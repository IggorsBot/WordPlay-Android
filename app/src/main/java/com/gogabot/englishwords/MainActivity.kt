package com.gogabot.englishwords

import android.R.attr.x
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

val TAG = "MainActivity"


private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


class MainActivity : AppCompatActivity() {

    lateinit var dictListScrollView : ScrollView
    lateinit var dictListGridView: GridLayout
    lateinit var menuTip : RelativeLayout

    var dictListArray: Array<CardView> = emptyArray()

    private val deltaAlpha: Float = 0.02f

    private val maxAlphaValue: Float = 1f
    private val minAlphaValue: Float = 0.0f

    var yAtTheTimePress: Float = 0.0f
    var yScrollAtTheTimePress: Int = 0

    var previousCoordinateY: Float = 0.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dictListScrollView = findViewById(R.id.dictListScrollView)
        dictListGridView = findViewById(R.id.dictListView)
        menuTip = findViewById(R.id.menuTip)

        dictListScrollView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        yAtTheTimePress = event.y
                        yScrollAtTheTimePress = dictListScrollView.scrollY
                    }
                    MotionEvent.ACTION_UP -> {
                        smoothScrolling(event.y)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        hideMenuTip(event.y)
                    }
                }
                return false
            }
        })

        createDictionary()
        createDictionary()

    }

    private fun createDictionaryText(): TextView {
        // Initialize a new TextView to put in LinearLayout

        val dictionaryText: TextView = TextView(this)

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        params.setMargins(
            0,
            resources.getDimension(R.dimen.card_text_margin).dp,
            0,
            0
        )
        dictionaryText.layoutParams = params

        dictionaryText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.dictionary_text_size)
        )

        dictionaryText.setTextColor(Color.parseColor("#6f6f6f"));
        dictionaryText.text = "Calendar"
        return dictionaryText
    }
    private fun createDictionaryImage(): ImageView {
        // Initialize a new ImageView to put in LinearLayout

        val dictionaryImage: ImageView = ImageView(this)

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                resources.getDimension(R.dimen.card_image_size).dp,
                resources.getDimension(R.dimen.card_image_size).dp
            )

        dictionaryImage.layoutParams = params
        dictionaryImage.setImageResource(R.mipmap.ic_launcher_round)

        return dictionaryImage
    }

    private fun createLinearLayoutForDictionary(): LinearLayout {
        // Initialize a new LinearLayout to put in CardView

        val dictionaryLinearLayout: LinearLayout = LinearLayout(this)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        dictionaryLinearLayout.layoutParams = params

        dictionaryLinearLayout.setPadding(
            0,
            resources.getDimension(R.dimen.dictionary_padding).dp,
            0,
            0
        )

        dictionaryLinearLayout.orientation = LinearLayout.VERTICAL
        dictionaryLinearLayout.gravity = Gravity.CENTER

        dictionaryLinearLayout.addView(createDictionaryImage())
        dictionaryLinearLayout.addView(createDictionaryText())

        return dictionaryLinearLayout
    }

    private fun createDictionary() {
        // For converting dp to pixels

        val dictionary: CardView = CardView(this)

        val params: GridLayout.LayoutParams = GridLayout.LayoutParams(
            GridLayout.spec(
                GridLayout.UNDEFINED, GridLayout.FILL, 1f
            ),
            GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
        )
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.width  = LinearLayout.LayoutParams.WRAP_CONTENT;

        params.setMargins(
            resources.getDimension(R.dimen.card_margin_full).dp,
            resources.getDimension(R.dimen.card_margin_full).dp,
            resources.getDimension(R.dimen.card_margin_full).dp,
            resources.getDimension(R.dimen.card_margin_full).dp
        )

        dictionary.layoutParams = params
        dictionary.cardElevation = resources.getDimension(R.dimen.card_elevation)
        dictionary.radius = resources.getDimension(R.dimen.card_corner_radius)

        dictionary.addView(createLinearLayoutForDictionary())

        dictListGridView.addView(dictionary)
    }

    fun smoothScrolling(yAtTheTimeReleasing: Float = 0.0f) {
        if(yAtTheTimePress - yAtTheTimeReleasing < menuTip.height && this.yScrollAtTheTimePress < 100) {
            dictListScrollView.post(Runnable { dictListScrollView.smoothScrollTo(x, menuTip.height) })
        }

        if (yAtTheTimeReleasing > yAtTheTimePress && dictListScrollView.scrollY < menuTip.height) {
            dictListScrollView.post(Runnable { dictListScrollView.smoothScrollTo(x, 0) })
            menuTip.alpha = 1f
        }
    }

    fun hideMenuTip(currentCoordinateY: Float) {
        if (currentCoordinateY < previousCoordinateY) {
            if (menuTip.alpha > minAlphaValue) {
                menuTip.alpha -= deltaAlpha
            } else menuTip.alpha = minAlphaValue
        }
        if (currentCoordinateY > previousCoordinateY) {
            if (menuTip.alpha < maxAlphaValue) {
                menuTip.alpha += deltaAlpha
            } else menuTip.alpha = maxAlphaValue
        }

        previousCoordinateY = currentCoordinateY
    }
}