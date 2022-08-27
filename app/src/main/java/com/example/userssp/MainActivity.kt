package com.example.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers())
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()
        val me = User(0, "Jesus", "Castillo", "")
        //  PET - NAMES
        val machina = User(1, "Machina", "Castillo", "")
        val mimi = User(1, "Mimi", "Castillo", "")
        val gringacho = User(1, "Gringacho", "Jesus", "")
        val riqueza = User(1, "Riqueza", "Jesus", "")

        users.add(me)
        users.add(machina)
        users.add(mimi)
        users.add(gringacho)
        users.add(riqueza)
        return users
    }
}