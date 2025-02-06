package com.example.fortniteapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortniteapi.databinding.ActivityAccesoriosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccesoriosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccesoriosBinding
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var adapter: AccesoriosAdapter

    private var accesoriosList = mutableListOf<CosmeticItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccesoriosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter = AccesoriosAdapter()
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
                Log.i("accesorios", response.data.toString())
                runOnUiThread {
                    binding.progressBar.isVisible = false
                    adapter.updateList(response.data.filter { it.tipo.tipo != "outfit" && it.tipo.tipo != "spray" })
                }
            }else
                Log.i("Accesorios", "no funciona :(")
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fortnite-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}