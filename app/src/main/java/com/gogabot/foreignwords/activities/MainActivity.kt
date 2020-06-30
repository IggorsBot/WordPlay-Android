package com.gogabot.foreignwords.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.adapters.DictionaryListAdapter
import com.gogabot.foreignwords.viewModels.DictionaryViewModel


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    private lateinit var dictionaryViewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dictionary_list_activity)


        val dictionaryRecyclerView = findViewById<RecyclerView>(R.id.dictionary_list_recycler_view)
        val dictionariesListAdapter =
            DictionaryListAdapter(
                this,
                savedInstanceState
            )

        dictionaryRecyclerView.adapter = dictionariesListAdapter
        dictionaryRecyclerView.layoutManager = LinearLayoutManager(this)

        getDictionaryList(dictionariesListAdapter)
    }

    private fun getDictionaryList(dictionariesListAdapter: DictionaryListAdapter) {
        dictionaryViewModel = ViewModelProvider(this).get(DictionaryViewModel::class.java)
        dictionaryViewModel.dictionaryList.observe(this, Observer { dictionary ->
            dictionary?.let {
                dictionariesListAdapter.setDictionary(it)
            }
        })
    }
}