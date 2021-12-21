package com.example.smartgym

import android.util.Log
import androidx.lifecycle.ViewModel

class ExerciseViewModel : ViewModel() {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    var postList: List<Exercises> = ArrayList()
    val postListAdapter: ExercisesListAdapter = ExercisesListAdapter(postList)

    init {
        firebaseRepo.getPostList().addOnCompleteListener {
            if (it.isSuccessful){
                postList = it.result!!.toObjects(Exercises::class.java)
                postListAdapter.postListItems = postList
                postListAdapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }

    }

    companion object {
        private const val TAG: String = "HOMEPAGE_LOG"
    }

}