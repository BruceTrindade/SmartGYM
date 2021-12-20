package com.example.smartgym

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

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
                    loadPostData()
                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        } else {
            loadPostData()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recylerViewList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postListAdapter

    }

    private fun loadPostData() {
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
}