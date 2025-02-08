package com.example.fortniteapi.Personajes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fortniteapi.CosmeticItem
import com.example.fortniteapi.databinding.ItemPersonajesBinding
import com.squareup.picasso.Picasso

class PersonajesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemPersonajesBinding.bind(view)

    fun bind(personajeItem: CosmeticItem, onItemSelected: (String) -> Unit) {
        binding.tvPersonajeName.text = personajeItem.nombre
        Picasso.get().load(personajeItem.imagen.url).into(binding.ivPersonaje)
        binding.root.setOnClickListener {
            onItemSelected(personajeItem.id)
        }
    }
}