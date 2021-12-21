package com.example.smartgym.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRepo {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private  val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun createUser(): Task<AuthResult> {
        return  firebaseAuth.signInAnonymously()
    }

    private val collection = Firebase.firestore.collection("Exercise")
    private val collectionWorkout = Firebase.firestore.collection("Workout")

    fun insert(exercises: Exercises) = firebaseFirestore.collection("Exercise").add(exercises)
    fun insertWorkout(workout: Workout) = firebaseFirestore.collection("Workout").add(workout)

    fun getPostList(): Task<QuerySnapshot> = collection.get()

    fun getPostWorkoutList(): Task<QuerySnapshot> = collectionWorkout.get()

}