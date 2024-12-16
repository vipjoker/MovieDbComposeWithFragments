package com.example.core.model.domain


sealed class CharacterGender(val displayName: String) {
    object Male: CharacterGender("Male")
    object Female: CharacterGender("Female")
    object Genderless: CharacterGender("No gender")
    object Unknown: CharacterGender("Not specified")
}