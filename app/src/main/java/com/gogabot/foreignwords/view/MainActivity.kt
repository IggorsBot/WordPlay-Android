package com.gogabot.foreignwords.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.di.AppModule
import com.gogabot.foreignwords.di.RoomModule
import com.gogabot.foreignwords.adapters.DictionaryListAdapter
import com.gogabot.foreignwords.database.word.WordRepository
import com.gogabot.foreignwords.di.DaggerAppComponent
import com.gogabot.foreignwords.viewModels.DictionaryViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    lateinit var dictionaryViewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dictionary_list_activity)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .roomModule(RoomModule(application))
            .build()
            .inject(this)


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
        dictionaryViewModel = DictionaryViewModel(application)

        dictionaryViewModel.dictionaryList.observe(this, Observer { dictionary ->
            dictionary?.let {
                dictionariesListAdapter.setDictionary(it)
            }
        })
    }
}