package com.gogabot.englishwords

import android.R.attr.x
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.gogabot.englishwords.dictionary.Dictionary
import com.gogabot.englishwords.dictionary.IDictionary


val TAG = "MainActivity"

private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


class MainActivity : AppCompatActivity() {
    lateinit var dictListScrollView : ScrollView
    lateinit var dictListGridView: GridLayout
    lateinit var menuTip : RelativeLayout

    var dictListArray: MutableList<IDictionary> = mutableListOf<IDictionary>()

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

        createDictionaries(this)
        showDictionaries()
    }

    fun createDictionaries(context: Context) {
        for (i in 1..10) {
            val dictionary: IDictionary = Dictionary(context)
            dictionary.id = i

            dictionary.dictionaryView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View) {
                    val intent = Intent(this@MainActivity, ListWordsInDictionary::class.java)
                    intent.putExtra("DICTIONARY_ID", dictionary.id)
                    startActivity(intent)
                }
            })

            dictListArray.add(dictionary)
        }
    }
    fun showDictionaries() {
        for (i in 0..9) {
            dictListGridView.addView(dictListArray[i].dictionaryView)
        }
    }

    fun smoothScrolling(yAtTheTimeReleasing: Float = 0.0f) {
        if(yAtTheTimePress - yAtTheTimeReleasing < menuTip.height
            && this.yScrollAtTheTimePress < 100
            && yAtTheTimePress != yAtTheTimeReleasing) {
            dictListScrollView.post(Runnable { dictListScrollView.smoothScrollTo(x, menuTip.height) })
        }

        if (yAtTheTimeReleasing > yAtTheTimePress && dictListScrollView.scrollY < menuTip.height) {
            dictListScrollView.post(Runnable { dictListScrollView.smoothScrollTo(x, 0) })
            menuTip.alpha = maxAlphaValue
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