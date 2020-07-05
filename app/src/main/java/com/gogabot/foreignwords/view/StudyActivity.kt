package com.gogabot.foreignwords.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.FirstFragment
import com.gogabot.foreignwords.SecondFragment
import com.gogabot.foreignwords.adapters.WordListAdapter
import com.gogabot.foreignwords.viewModels.StudyViewModel

class StudyActivity : AppCompatActivity() {
    val TAG = "WordListActivity"

    private var dictionaryID: Int = 0
    private lateinit var studyViewModel: StudyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment = FirstFragment()

        fragmentTransaction.add(R.id.container_fragment, firstFragment)
        fragmentTransaction.commit()

        val buttonFragment = findViewById<Button>(R.id.next)

        buttonFragment.setOnClickListener{
            val newFragment = SecondFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, newFragment)
            transaction.commit()
        }
    }

    private fun getWordsOfDictionary(wordListAdapter: WordListAdapter) {
        dictionaryID = intent.getIntExtra("DICTIONARY_ID", 0)
        studyViewModel = ViewModelProvider(this).get(StudyViewModel::class.java)
//        studyViewModel.getWordsOfDictionary(dictionaryID)
    }
}