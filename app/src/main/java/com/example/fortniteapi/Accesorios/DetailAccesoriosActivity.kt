package com.example.fortniteapi.Accesorios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fortniteapi.ApiService
import com.example.fortniteapi.CosmeticsResponse
import com.example.fortniteapi.databinding.ActivityDetailAccesoriosBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailAccesoriosActivity : AppCompatActivity() {

    companion object{
        const val ID = "id"
    }

    private lateinit var binding: ActivityDetailAccesoriosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAccesoriosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(ID).orEmpty()
        getAccesorioDetail(id)
    }

    private fun getAccesorioDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val accesorioDetail = getRetrofit().create(ApiService::class.java).getCosmeticDetail(id)
            if (accesorioDetail.body()!=null){
                runOnUiThread{
                    createUI(accesorioDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(accesorio: CosmeticsResponse) {
        binding.tvAccesorioNombre.text = accesorio.data[0].nombre
        Picasso.get().load(accesorio.data[0].imagen.url).into(binding.ivAccesorio)
        binding.tvAccesorioDesc.text = accesorio.data[0].descripcion
        binding.tvRareza2.text = accesorio.data[0].rareza.rareza
        binding.tvSet2.text = accesorio.data[0].set.set
        binding.tvTemporada2.text = accesorio.data[0].introduccion.introduccion
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fortnite-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}