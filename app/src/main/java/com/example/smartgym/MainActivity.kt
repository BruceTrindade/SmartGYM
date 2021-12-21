package com.example.smartgym

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.smartgym.repository.Exercises
import com.example.smartgym.repository.FirebaseRepo
import com.example.smartgym.view.ExerciseFragment
import com.example.smartgym.view.WorkoutFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var postList: List<Exercises> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (firebaseRepo.getUser() == null){
            firebaseRepo.createUser().addOnCompleteListener {
                if (it.isSuccessful){
                    buttonExercises.setOnClickListener{
                        callExerciseFragment()
                    }
                    buttonWorkout.setOnClickListener{
                        callWorkoutFragment()
                    }
                    buttonHome.setOnClickListener {
                        startMain()
                    }
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        } else {
            buttonExercises.setOnClickListener{
                callExerciseFragment()
            }
            buttonWorkout.setOnClickListener{
                callWorkoutFragment()
            }
        }


    }

    private fun startMain(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun callExerciseFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layoutMain, ExerciseFragment())
            .commit()
        containerPhrases.visibility = View.GONE
        containerDicas.visibility = View.GONE

    }
    private fun callWorkoutFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layoutMain, WorkoutFragment())
            .commit()
        containerPhrases.visibility = View.GONE
        containerDicas.visibility = View.GONE
    }
}