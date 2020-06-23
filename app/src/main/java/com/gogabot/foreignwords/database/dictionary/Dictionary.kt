package com.gogabot.foreignwords.database.dictionary

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dictionary_table")
data class Dictionary(@PrimaryKey val id: Int,
                      val name: String)
