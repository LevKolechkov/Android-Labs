package com.example.roomdatabase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertItem(nameEntity: NameEntity)

//  @Update
//  suspend fun updateItem(nameEntity: NameEntity)

  @Delete
  suspend fun deleteItem(nameEntity: NameEntity)

  @Query("SELECT * FROM `table`")
  fun getAllItems(): Flow<List<NameEntity>>
}