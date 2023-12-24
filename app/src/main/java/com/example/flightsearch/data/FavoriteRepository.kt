package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    suspend fun add(favoriteFlight: FavoriteFlight)

    fun list(): Flow<List<FavoriteFlight>>
}

class FavoriteRepository(private val favoriteDao: FavoriteDao) : IFavoriteRepository {
    override suspend fun add(favoriteFlight: FavoriteFlight) {
        favoriteDao.add(favoriteFlight)
    }

    override fun list(): Flow<List<FavoriteFlight>> {
        return favoriteDao.list()
    }
}