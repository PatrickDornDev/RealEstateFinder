package dev.patrickdorn.realestatefinder.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(@PrimaryKey(autoGenerate = true) val uid: Int = 0, @ColumnInfo(name = "listing_id") val listingId: String)
