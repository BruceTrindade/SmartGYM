package com.example.smartgym

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG: String = "HOMEPAGE_LOG"

class MainActivity : AppCompatActivity() {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var postList: List<Exercises> = ArrayList()
    private val postListAdapter: ExercisesListAdapter = ExercisesListAdapter(postList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (firebaseRepo.getUser() == null){
            firebaseRepo.createUser().addOnCompleteListener {
                if (it.isSuccessful){
                    buttonExercises.setOnClickListener{
                        callExerciseFragment()
                        bottomBar.visibility = View.GONE
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
//        buttonExercises.visibility = View.GONE
//        buttonWorkout.visibility = View.GONE

    }
}