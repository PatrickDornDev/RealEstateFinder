package dev.patrickdorn.realestatefinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class RealEstateFinderDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
