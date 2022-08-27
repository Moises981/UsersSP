package com.example.userssp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), IOnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTIme = preferences.getBoolean(getString(R.string.sp_first_time), true)
        val spUsername = preferences.getString(getString(R.string.sp_username), "")

        Log.i("SP", "${getString(R.string.sp_first_time)} : $isFirstTIme")
        Log.i("SP", "${getString(R.string.sp_username)} : $spUsername")

        if (isFirstTIme) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this).setTitle(R.string.dialog_title).setView(dialogView)
                .setPositiveButton(
                    R.string.dialog_confirm
                )
                { _, _ ->
                    val username =
                        dialogView.findViewById<TextInputEditText>(R.id.etUsername).text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                            .commit()
                        putString(getString(R.string.sp_username), username).apply()
                    }
                }.setCancelable(false)
                .show()
        }


        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()
        val me = User(
            0,
            "Jesus",
            "Castillo",
            "https://icon-library.com/images/person-png-icon/person-png-icon-29.jpg"
        )
        //  PET - NAMES
        val machina = User(
            1,
            "Machina",
            "Castillo",
            "data:image/jpeg;base64,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"
        )
        val mimi = User(
            1,
            "Mimi",
            "Castillo",
            "https://cdn.pixabay.com/photo/2019/12/01/00/02/cat-4664535_1280.jpg"
        )
        val gringacho = User(
            1,
            "Gringacho",
            "Jesus",
            "https://www.feelcats.com/wp-content/uploads/2018/10/gato-atigrado-naranja-1.jpg"
        )
        val riqueza = User(
            1,
            "Riqueza",
            "Jesus",
            "https://thumbs.dreamstime.com/b/retrato-del-perro-cruzado-adorable-de-la-raza-colli-schnauzer-y-frontera-117449479.jpg"
        )

        users.add(me)
        users.add(machina)
        users.add(mimi)
        users.add(gringacho)
        users.add(riqueza)
        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.fullName}", Toast.LENGTH_SHORT).show()
    }
}