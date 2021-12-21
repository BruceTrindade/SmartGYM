package com.example.smartgym

import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    fun insert(exercises: Exercises) = firebaseFirestore.collection("Exercise").add(exercises)
        //collection.document(exercises.Name).set(exercises)

  /*  fun insert(name: String, description: String) {
        val exercise: MutableMap<String, Any> = HashMap()
        exercise["name"] = name
        exercise["description"] = description

        firebaseFirestore.collection("Treino")
            .add(exercise)
            .addOnSuccessListener {
                //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {

            }
    }*/

    fun getPostList(): Task<QuerySnapshot> = collection.get()

   /* fun getPostList(): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("Funcional")
            .orderBy("Description", Query.Direction.DESCENDING)
            .get()

    }*/

}