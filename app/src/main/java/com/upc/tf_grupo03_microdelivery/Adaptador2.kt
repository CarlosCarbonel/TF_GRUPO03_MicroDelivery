package com.upc.tf_grupo03_microdelivery

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador2:RecyclerView.Adapter<Adaptador2.MiViewHolder>() {
    class MiViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        private var filaproducto = view.findViewById<TextView>(R.id.filaproducto)
        private var filaenvio = view.findViewById<TextView>(R.id.filaenvio)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador2.MiViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Adaptador2.MiViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}