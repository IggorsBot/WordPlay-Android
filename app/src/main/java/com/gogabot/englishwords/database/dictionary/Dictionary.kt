package com.gogabot.englishwords.database.dictionary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "dictionary_table")
data class Dictionary(@PrimaryKey val id: Int,
                      val name: String)
