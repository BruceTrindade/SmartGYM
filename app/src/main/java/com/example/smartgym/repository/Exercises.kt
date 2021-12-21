package com.example.smartgym.repository

import com.google.firebase.firestore.DocumentId

class Exercises (
    val name: String? = null,
    val description: String? = null,
    @DocumentId
    var id: String? = null
        )

