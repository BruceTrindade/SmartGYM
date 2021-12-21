package com.example.smartgym

import com.google.firebase.firestore.DocumentId

class Exercises (
    val name: String = "",
    val description: String = "",
    @DocumentId
    var id: String? = null
        )

