package com.example.fortniteapi.Personajes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fortniteapi.ApiService
import com.example.fortniteapi.CosmeticsResponse
import com.example.fortniteapi.databinding.ActivityDetailPersonajesBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPersonajesActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
    }

    private lateinit var binding: ActivityDetailPersonajesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(ID).orEmpty()
        getPersonajeDetail(id)
    }

    private fun getPersonajeDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val personajeDetail = getRetrofit().create(ApiService::class.java).getCosmeticDetail(id)
            if (personajeDetail.body() != null) {
                runOnUiThread {
                    createUI(personajeDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(personaje: CosmeticsResponse) {
        binding.tvPersonajeNombre.text = personaje.data[0].nombre
        Picasso.get().load(personaje.data[0].imagen.url).into(binding.ivPersonaje)
        binding.tvPersonajeDesc.text = personaje.data[0].descripcion
        binding.tvRareza2.text = personaje.data[0].rareza.rareza
        binding.tvSet2.text = personaje.data[0].set.set
        binding.tvTemporada2.text = personaje.data[0].introduccion.introduccion
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fortnite-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}