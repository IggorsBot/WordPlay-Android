package com.gogabot.foreignwords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.database.word.Word
import kotlinx.android.synthetic.main.word_list_in_dictionary.*


class ListWordsActivity: AppCompatActivity() {

    private var dictionaryID: Int = 0

    private lateinit var wordViewModel: WordViewModel

    var wordsList = ArrayList<com.gogabot.foreignwords.Word>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list_in_dictionary)

        val recyclerView = findViewById<RecyclerView>(R.id.wordsRecyclerView)

        val listWordsAdapter =
            ListWordsAdapter(wordsList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listWordsAdapter

        val intent: Intent = intent
        dictionaryID = intent.getIntExtra("DICTIONARY_ID", 0)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
//        wordViewModel.getWordsOfDictionary(dictionaryID).observe(this, Observer { words ->
//            words?.let {
//                addWordsInList(it)
//            }
//        })

        wordViewModel.wordsFromAllDicionaries.observe(this, Observer { words ->
            words?.let {
                addWordsInList(it)
            }
        })
    }

    fun addWordsInList(words: List<Word>) {
        for(word: Word in words){

            wordsList.add(
                Word(
                    word.enWord,
                    word.example,
                    R.mipmap.ic_launcher_round
                )
            )
        }
    }
}

