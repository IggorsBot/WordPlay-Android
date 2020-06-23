package com.gogabot.foreignwords

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gogabot.englishwords.R
import kotlinx.android.synthetic.main.dictionary_layout.view.*


class DictionaryListAdapter(private val arrayList: ArrayList<Dictionary>, val context: Context, val options: Bundle?):
    RecyclerView.Adapter<DictionaryListAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(dictionary: Dictionary) {
            itemView.id = dictionary.id
            itemView.nameDictionary.text = dictionary.name
            itemView.imageDictionary.setImageResource(dictionary.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dictionary_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ListWordsActivity::class.java)
            intent.putExtra("DICTIONARY_ID", holder.itemView.id)
            startActivity(context, intent, options)
        }
    }

}