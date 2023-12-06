package com.upc.tf_grupo03_microdelivery.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.upc.tf_grupo03_microdelivery.R
import com.upc.tf_grupo03_microdelivery.entidades.Contactos


class Adaptorc (private val listener: OnItemClickListener):RecyclerView.Adapter<Adaptorc.MiViewHolder>(){

    var listaContactos:ArrayList<Contactos> = ArrayList()


    fun agregarItems(items:ArrayList<Contactos>){
        this.listaContactos=items
        notifyDataSetChanged()
    }


    class MiViewHolder(var view:View, val listener: OnItemClickListener):RecyclerView.ViewHolder(view){
        private var fcNombres= view.findViewById<TextView>(R.id.ctonombapellidos)
        private var btnEliminar=view.findViewById<FloatingActionButton>(R.id.fbtneliminar)


        fun bindView(contacto:Contactos) {
            fcNombres.text=contacto.cnombres +" "+contacto.capellidos

            view.setOnClickListener{
                listener.onItemClicked(contacto)
            }
            btnEliminar.setOnClickListener{
               listener.onEliminarClicked(contacto,adapterPosition)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila_contacto, parent, false),
        listener
    )
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val contactoItem=listaContactos[position]
        holder.bindView(contactoItem)
    }

    override fun getItemCount(): Int {
        return listaContactos.size
    }

    interface OnItemClickListener {
        fun onItemClicked(contacto: Contactos)
        fun onEliminarClicked(contacto: Contactos, position: Int)
    }

}


