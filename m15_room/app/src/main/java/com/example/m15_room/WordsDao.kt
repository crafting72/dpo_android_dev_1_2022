package com.example.m15_room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {

    @Query("SELECT * FROM words")
    fun getAll(): Flow<List<Words>>

    @Query("SELECT count FROM words WHERE word LIKE :word")
    suspend fun getCountWords(word: String): Int?

    @Insert
    suspend fun insert(words: Words)

    @Update
    suspend fun update(words: Words)

    @Delete
    suspend fun delete(words: Words)

}