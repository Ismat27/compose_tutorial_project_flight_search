package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(favoriteFight: FavoriteFlight)

    @Query("SELECT * FROM favorite")
    fun list():Flow<List<FavoriteFlight>>


}