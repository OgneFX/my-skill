package com.example.finalskillcinema.db.model

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithDetailInfo(
    @Embedded
    val personFilms: PersonShortInfo,
    @Relation(
        entity = PersonFilms::class,
        parentColumn = "id",
        entityColumn = "person_id"
    )
    val films: List<PersonFilms>?
)