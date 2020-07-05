package com.gogabot.foreignwords.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import com.gogabot.foreignwords.view.WordListActivity
import kotlinx.android.synthetic.main.dictionary_layout.view.*
import com.gogabot.foreignwords.database.dictionary.Dictionary


class DictionaryListAdapter(val context: Context, val options: Bundle?):

    RecyclerView.Adapter<DictionaryListAdapter.ViewHolder>(){
    private var dictionaryList = emptyList<Dictionary>()

    class ViewHolder(dictionaryItem: View): RecyclerView.ViewHolder(dictionaryItem) {
        fun bindItems(dictionary: Dictionary) {
            itemView.id = dictionary.id
            itemView.name_dictionary.text = dictionary.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dictionary_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dictionaryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dictionaryList[position])

        holder.itemView.setOnClickListener{
            val intent = Intent(context, WordListActivity::class.java)
            intent.putExtra("DICTIONARY_ID", holder.itemView.id)
            intent.putExtra("DICTIONARY_NAME", holder.itemView.name_dictionary.text)
            startActivity(context, intent, options)
        }
    }

    fun setDictionary(dictionaryList: List<Dictionary>) {
        this.dictionaryList = dictionaryList

        notifyDataSetChanged()


    }



}