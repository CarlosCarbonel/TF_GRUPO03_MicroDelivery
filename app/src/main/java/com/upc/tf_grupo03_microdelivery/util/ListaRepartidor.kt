package com.upc.tf_grupo03_microdelivery.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.upc.tf_grupo03_microdelivery.Adaptor
import com.upc.tf_grupo03_microdelivery.R
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO

class ListaRepartidor : AppCompatActivity() {

    private lateinit var rvUsuarios: RecyclerView
    private lateinit var usuariosDAO: UsuariosDAO
    private var adaptador:Adaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_repartidor)
        asignarReferencias()
        mostrarUsuarios()
    }
    private fun asignarReferencias(){
        usuariosDAO = UsuariosDAO(this)
        rvUsuarios=findViewById(R.id.rvUsuarios)
        rvUsuarios.layoutManager= LinearLayoutManager(this)
        adaptador= Adaptor()
        rvUsuarios.adapter=adaptador
    }
    private fun mostrarUsuarios(){
        val listaUsuarios = usuariosDAO.cargarUsuarios()
        adaptador?.agregarItems(listaUsuarios)
        Log.d("===","${listaUsuarios.size}")
    }
}