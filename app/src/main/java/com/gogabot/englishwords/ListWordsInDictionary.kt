package com.gogabot.englishwords

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_words_in_dictionary.*


class ListWordsInDictionary: AppCompatActivity() {
    var id: Int = 0
    private lateinit var wordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_words_in_dictionary)

        var arrayList = ArrayList<Model>()
        arrayList.add(Model("News Feed", "This is news feed description", R.mipmap.ic_launcher_round))
        arrayList.add(Model("Business", "This is news feed description", R.mipmap.ic_launcher_round))
        arrayList.add(Model("People", "This is news feed description", R.mipmap.ic_launcher_round))
        arrayList.add(Model("Notes", "This is news feed description", R.mipmap.ic_launcher_round))
        arrayList.add(Model("Feedback", "This is news feed description", R.mipmap.ic_launcher_round))

        val listWordsAdapter = ListWordsAdapter(arrayList, this)

        recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listWordsAdapter


        val intent: Intent = intent
        id = intent.getIntExtra("DICTIONARY_ID", 0)

//        val testText : TextView = findViewById(R.id.testTextView)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

//        wordViewModel.wordHello.observe(this, Observer { words ->
//            // Update the cached copy of the words in the adapter.
//            words?.let { testText.text = it }
//        })
    }
}

