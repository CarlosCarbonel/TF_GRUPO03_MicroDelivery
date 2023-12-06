package com.upc.tf_grupo03_microdelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews.RemoteCollectionItems
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios


class Adaptor (private val listener:OnItemClickListener):RecyclerView.Adapter<Adaptor.MiViewHolder>(){

    private var listaUsuarios:ArrayList<Usuarios> = ArrayList()

    fun agregarItems(items:ArrayList<Usuarios>){
        this.listaUsuarios=items
        notifyDataSetChanged()
    }


    class MiViewHolder(var view:View, val listener: OnItemClickListener):RecyclerView.ViewHolder(view){
        private var fNombres= view.findViewById<TextView>(R.id.filanombres)


        fun bindView(usuario:Usuarios) {
            fNombres.text=usuario.nombres +" "+usuario.apellidos

            view.setOnClickListener{
                listener.onItemClicked(usuario)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila_repartidor, parent, false),
        listener
    )
    override fun onBindViewHolder(holder: Adaptor.MiViewHolder, position: Int) {
        val personaItem=listaUsuarios[position]
        holder.bindView(personaItem)
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }


}
interface OnItemClickListener {
    fun onItemClicked(usuario: Usuarios)
}
