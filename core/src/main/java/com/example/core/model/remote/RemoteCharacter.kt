package com.example.core.model.remote

import com.example.core.model.domain.CharacterGender
import com.example.core.model.domain.CharacterStatus
import kotlinx.serialization.Serializable

@Serializable

data class RemoteCharacter (

    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    @Serializable
    data class Location(
        val name: String,
        val url: String
    )
    @Serializable
    data class Origin(
        val name: String,
        val url: String
    )
}

fun RemoteCharacter.toDomainCharacter(): com.example.core.model.domain.Character {
    val characterGender = when (gender.lowercase()) {
        "female" -> CharacterGender.Female
        "male" -> CharacterGender.Male
        "genderless" -> CharacterGender.Genderless
        else -> CharacterGender.Unknown
    }
    val characterStatus = when (status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }
    return com.example.core.model.domain.Character(
        created = created,
        episodeUrls = episode,
        gender = characterGender,
        id = id,
        imageUrl = image,
        location = com.example.core.model.domain.Character.Location(name = location.name, url = location.url),
        name = name,
        origin = com.example.core.model.domain.Character.Origin(name = origin.name, url = origin.url),
        species = species,
        status = characterStatus,
        type = type
    )
}