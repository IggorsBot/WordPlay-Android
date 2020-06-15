package com.gogabot.englishwords

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListWordsInDictionary: AppCompatActivity() {

    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_words_in_dictionary)

        if (savedInstanceState == null) {}
        else {
            id = savedInstanceState.getSerializable("DICTIONARY_ID") as Int
        }
        var testText : TextView = findViewById(R.id.testTextView)
        testText.text = "New Text $id"
    }
}