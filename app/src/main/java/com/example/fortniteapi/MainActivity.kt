package com.example.fortniteapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fortniteapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Manejar el clic del botón para ir a AccesoriosActivity
        binding.btnAccesorios.setOnClickListener {
            val intent = Intent(this, AccesoriosActivity::class.java)
            startActivity(intent)
        }
    }
}