package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabase.data.NameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertItem(nameEntity: NameEntity)

  @Delete
  suspend fun deleteItem(nameEntity: NameEntity)

  @Query("SELECT * FROM my_table")
  fun getAllItems(): Flow<List<NameEntity>>
}