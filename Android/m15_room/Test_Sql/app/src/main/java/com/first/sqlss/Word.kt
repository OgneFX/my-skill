package com.first.sqlss


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    @ColumnInfo(name="mainword")
    var mainword: String,
    @ColumnInfo(name="counter")
    var counter: Int
)
