package com.gogabot.foreignwords.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.adapters.WordListAdapter
import com.gogabot.foreignwords.viewModels.WordViewModel


class WordListActivity: AppCompatActivity() {
    val TAG = "WordListActivity"


    private var dictionaryID: Int = 0
    private lateinit var wordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list_activity)


        val recyclerView = findViewById<RecyclerView>(R.id.wordsRecyclerView)
        val wordListAdapter =
            WordListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = wordListAdapter

        initDictionary(wordListAdapter)
        getWordsOfDictionary(wordListAdapter)
    }

    private fun getWordsOfDictionary(wordListAdapter: WordListAdapter) {
        dictionaryID = intent.getIntExtra("DICTIONARY_ID", 0)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.getWordsOfDictionary(dictionaryID).observe(this, Observer { words ->
            words?.let { wordListAdapter.setWords(it) }
        })
    }

    private fun initDictionary(wordListAdapter: WordListAdapter) {
        initDictionaryName()
        setListenerOnButton(wordListAdapter)
    }

    private fun setListenerOnButton(wordListAdapter: WordListAdapter) {
        val buttonLearn = findViewById<Button>(R.id.button_learn)

        buttonLearn.setOnClickListener{
            Log.d(TAG, "Click click")
            if(wordListAdapter.itemCount < 5) {
                Log.d(TAG, "Необходимо не менее 5 слов в словаре для изучения")
            } else {
                Log.d(TAG, "Изучаем слова")
            }
        }
    }

    private fun initDictionaryName() {
        val dictionaryName = findViewById<TextView>(R.id.dictionary_name)
        dictionaryName.text = intent.getStringExtra("DICTIONARY_NAME")
    }
}

