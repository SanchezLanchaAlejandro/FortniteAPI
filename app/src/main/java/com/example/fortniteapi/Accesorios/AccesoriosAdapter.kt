package com.example.fortniteapi.Accesorios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fortniteapi.CosmeticItem
import com.example.fortniteapi.R

class AccesoriosAdapter(var accesoriosList: List<CosmeticItem> = emptyList(),
                        private val onItemSelected: (String) -> Unit) : RecyclerView.Adapter<AccesoriosViewHolder>() {

    fun updateList(accesoriosList: List<CosmeticItem>) {
        this.accesoriosList = accesoriosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccesoriosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AccesoriosViewHolder(layoutInflater.inflate(R.layout.item_accesorios, parent, false))
    }

    override fun getItemCount() = accesoriosList.size

    override fun onBindViewHolder(viewHolder: AccesoriosViewHolder, position: Int) {
        viewHolder.bind(accesoriosList[position], onItemSelected)
    }

}