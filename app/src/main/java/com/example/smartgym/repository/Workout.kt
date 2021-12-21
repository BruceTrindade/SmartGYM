package com.example.smartgym.repository

import com.google.firebase.firestore.DocumentId

class Workout (
    val nameWorkout: String = "",
    val descriptionWorkout: String = "",
    @DocumentId
    var id: String? = null
    )