package com.gogabot.foreignwords.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import kotlinx.android.synthetic.main.word_layout.view.*
import com.gogabot.foreignwords.database.word.Word


class WordListAdapter(val context: Context):
    RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    private var wordList = emptyList<Word>()

    class ViewHolder(wordView: View): RecyclerView.ViewHolder(wordView) {
        fun bindItems(word: Word) {
            itemView.txtWord.text = word.enWord
            itemView.example.text = word.example
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.word_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(wordList[position])

        holder.itemView.setOnClickListener{
        }
    }

    fun setWords(wordList: List<Word>) {
        this.wordList = wordList
        notifyDataSetChanged()
    }
}