package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// for the query
@Dao
interface ItemDao {

    // to inset new row in the DB.
    // onConflict = OnConflictStrategy.IGNORE mean ignore the insert if the PK is already exist.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    // : the same as $
    @Query("SELECT * from item WHERE id = :id ")
    // we don't need suspend because of Flow Type,Room runs query in background
    // fun return one row.
    fun getItem(id:Int): Flow<Item>

    // fun return all the rows
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

}