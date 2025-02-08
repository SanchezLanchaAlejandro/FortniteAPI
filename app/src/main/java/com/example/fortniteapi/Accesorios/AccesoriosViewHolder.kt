package com.example.fortniteapi.Accesorios

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fortniteapi.CosmeticItem
import com.example.fortniteapi.databinding.ItemAccesoriosBinding
import com.squareup.picasso.Picasso

class AccesoriosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = ItemAccesoriosBinding.bind(view)

    fun bind(accesoriosItemResponse: CosmeticItem, onItemSelected: (String) -> Unit) {
        binding.tvAccesorioName.text = accesoriosItemResponse.nombre
        Picasso.get().load(accesoriosItemResponse.imagen.url).into(binding.ivAccesorios)
        binding.root.setOnClickListener{
            onItemSelected(accesoriosItemResponse.id)
        }
    }
}