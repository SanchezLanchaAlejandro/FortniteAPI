package com.example.fortniteapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fortniteapi.Accesorios.AccesoriosActivity
import com.example.fortniteapi.Personajes.PersonajesActivity
import com.example.fortniteapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccesorios.setOnClickListener {
            val intent = Intent(this, AccesoriosActivity::class.java)
            startActivity(intent)
        }

        binding.btnPersonajes.setOnClickListener {
            val intent = Intent(this, PersonajesActivity::class.java)
            startActivity(intent)
        }
    }
}