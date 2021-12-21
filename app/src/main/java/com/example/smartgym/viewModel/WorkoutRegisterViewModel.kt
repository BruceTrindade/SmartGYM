package com.example.smartgym.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartgym.repository.Exercises
import com.example.smartgym.repository.FirebaseRepo
import com.example.smartgym.repository.Workout

class WorkoutRegisterViewModel : ViewModel() {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private val pvStatus = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = pvStatus

    private  val pvMsg = MutableLiveData<String>()
    val msg: LiveData<String> = pvMsg

    fun addWorkout(name: String,
                     description: String) {

        val workout = Workout(name, description)
        firebaseRepo.insertWorkout(workout)
            .addOnSuccessListener {
                pvStatus.value = true
            }
            .addOnFailureListener {
                pvMsg.value = it.message
            }

    }
}