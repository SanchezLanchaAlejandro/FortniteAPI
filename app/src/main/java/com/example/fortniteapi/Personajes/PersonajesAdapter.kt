package com.example.fortniteapi.Personajes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fortniteapi.CosmeticItem
import com.example.fortniteapi.R

class PersonajesAdapter(
    var personajesList: List<CosmeticItem> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<PersonajesViewHolder>() {

    fun updateList(personajesList: List<CosmeticItem>) {
        this.personajesList = personajesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonajesViewHolder(layoutInflater.inflate(R.layout.item_personajes, parent, false))
    }

    override fun getItemCount() = personajesList.size

    override fun onBindViewHolder(viewHolder: PersonajesViewHolder, position: Int) {
        viewHolder.bind(personajesList[position], onItemSelected)
    }
}