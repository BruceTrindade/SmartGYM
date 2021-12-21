package com.example.smartgym.repository

import com.google.firebase.firestore.DocumentId

class Exercises (
    val name: String = "",
    val description: String = "",
    @DocumentId
    var id: String? = null
        )

