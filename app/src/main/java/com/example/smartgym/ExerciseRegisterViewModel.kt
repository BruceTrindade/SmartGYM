package com.example.smartgym

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciseRegisterViewModel : ViewModel() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private val pvStatus = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = pvStatus

    private  val pvMsg = MutableLiveData<String>()
    val msg: LiveData<String> = pvMsg

    fun addExercises(name: String,
                     description: String) {

        val exercise = Exercises(name, description)
        firebaseRepo.insert(exercise)
            .addOnSuccessListener {
                pvStatus.value = true
            }
            .addOnFailureListener {
                pvMsg.value = it.message
            }

    }
    // TODO: Implement the ViewModel
}