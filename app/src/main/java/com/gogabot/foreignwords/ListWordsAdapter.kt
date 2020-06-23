package com.gogabot.foreignwords


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import kotlinx.android.synthetic.main.word_layout.view.*

class ListWordsAdapter(private val arrayList: ArrayList<Word>, val context: Context):
    RecyclerView.Adapter<ListWordsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(word: Word) {
            itemView.txtWord.text = word.title
            itemView.example.text = word.description
            itemView.imageWord.setImageResource(word.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.word_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener{
        }
    }
}