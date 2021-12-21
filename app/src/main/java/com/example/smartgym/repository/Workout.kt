package com.example.smartgym.repository

import com.google.firebase.firestore.DocumentId

class Workout (
    val nameWorkout: String? = null,
    val descriptionWorkout: String? = null,
    @DocumentId
    var id: String? = null
    )