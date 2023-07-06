package com.first.sqlss

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getMainWord(): Flow<List<Word>>

    @Query("SELECT * FROM word WHERE mainword IN (:text)")
    fun getMainWord(text:String):Flow<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(word: Word)

    @Update
    suspend fun update(word: Word)

//    @Query("UPDATE word SET counter = counter + 1 WHERE mainword GLOB :test ")
//    suspend fun updateCount(test:String)

//    @Query("SELECT SUM(counter) FROM word")
//    fun getCouter(): Int



    @Query("DELETE FROM word")
    suspend fun deleteTable()



}