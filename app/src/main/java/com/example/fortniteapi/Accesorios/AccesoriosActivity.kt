package com.example.fortniteapi.Accesorios

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortniteapi.Accesorios.DetailAccesoriosActivity.Companion.ID
import com.example.fortniteapi.ApiService
import com.example.fortniteapi.CosmeticsResponse
import com.example.fortniteapi.databinding.ActivityAccesoriosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccesoriosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccesoriosBinding
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var adapter: AccesoriosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccesoriosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        initUI()
    }

    private fun initUI() {
        // Cambiar el color del texto en el campo de búsqueda
        val searchEditText = binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(Color.WHITE)  // Texto blanco
        searchEditText.setHintTextColor(Color.WHITE)  // Sugerencias blancas

        // Cambiar el color del icono de la lupa
        val searchIcon = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)  // Icono de la lupa blanco

        // Cambiar el color del icono de borrar (X)
        val closeIcon = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        closeIcon.setColorFilter(Color.WHITE)  // Icono de cerrar blanco

        // Cambiar el color del borde del campo de búsqueda
        val submitArea = binding.searchView.findViewById<android.widget.LinearLayout>(androidx.appcompat.R.id.submit_area)
        submitArea.setBackgroundColor(Color.WHITE)  // Borde blanco

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter = AccesoriosAdapter { irDetail(it) }
        binding.rvAccesorios.setHasFixedSize(true)
        binding.rvAccesorios.layoutManager = LinearLayoutManager(this)
        binding.rvAccesorios.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<CosmeticsResponse> = retrofit.create(ApiService::class.java).getCosmetics(query)
            if (myResponse.isSuccessful) {
                val response: CosmeticsResponse = myResponse.body()!!
                runOnUiThread {
                    binding.progressBar.isVisible = false
                    adapter.updateList(response.data.filter { it.tipo.tipo != "outfit" && it.tipo.tipo != "spray" })
                }
            }else
                Log.i("accesorios", "Error en la API")
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fortnite-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun irDetail(id: String) {
        val intent = Intent(this, DetailAccesoriosActivity::class.java)
        intent.putExtra(ID, id)
        startActivity(intent)
    }
}