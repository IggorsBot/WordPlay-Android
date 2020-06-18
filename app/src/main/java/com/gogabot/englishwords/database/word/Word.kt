package com.gogabot.englishwords.database.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gogabot.englishwords.database.dictionary.Dictionary
import java.util.*


@Entity(tableName = "word_table",
    foreignKeys = arrayOf(ForeignKey(entity = Dictionary::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("dictionary"),
                    onDelete = ForeignKey.CASCADE)))
data class Word(@PrimaryKey val id: Int,
                @ColumnInfo(name="word_ru") val ruWord: String,
                @ColumnInfo(name="word_en") val enWord: String,
                @ColumnInfo(name="example") val example: String,
                @ColumnInfo(name="dictionary", index = true) val dictionary: Int)
