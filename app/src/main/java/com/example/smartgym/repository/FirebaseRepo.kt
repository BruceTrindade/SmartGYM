package com.example.smartgym.repository

import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.model.Document


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
   /*fun deleteWorkout(workout: Workout){
       firebaseFirestore.collection("Workout")
           .whereEqualTo("nameWorkout", workout)
           .get().addOnCompleteListener {
               val document: DocumentSnapshot = it.result
                   .documents
                   .get(0)
               val documentId = document.id
               firebaseFirestore.collection("Workout")
                   .document(documentId)
                   .delete()
           }
   }*/

    fun getPostList(): Task<QuerySnapshot> = collection.get()

    fun getPostWorkoutList(): Task<QuerySnapshot> = collectionWorkout.get()



}