package com.example.smartgym

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.smartgym.adapter.ExercisesListAdapter
import com.example.smartgym.repository.Exercises
import com.example.smartgym.repository.FirebaseRepo
import com.example.smartgym.repository.Workout

class WorkoutViewModel : ViewModel() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    var postWorkoutList: List<Workout> = ArrayList()
    val postWorkoutListAdapter: WorkoutListAdapter = WorkoutListAdapter(postWorkoutList)

    init {
        firebaseRepo.getPostWorkoutList().addOnCompleteListener {
            if (it.isSuccessful){
                postWorkoutList = it.result!!.toObjects(Workout::class.java)
                postWorkoutListAdapter.postWorkoutListItems = postWorkoutList
                postWorkoutListAdapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }

    }

    companion object {
        private const val TAG: String = "HOMEPAGE_LOG"
    }
}