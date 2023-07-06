package com.example.finalskillcinema.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seasons_episode")
data class SeasonEpisode(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "season_number") val seriesNumber: Int,
    @ColumnInfo(name = "episode_number") val episodeNumber: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "synopsis") val synopsis: String?
)
data class NewSeasonEpisode(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "film_id") val filmId: Int,
    @ColumnInfo(name = "season_number") val seriesNumber: Int,
    @ColumnInfo(name = "episode_number") val episodeNumber: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "synopsis") val synopsis: String?
)