package com.upc.tf_grupo03_microdelivery.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upc.tf_grupo03_microdelivery.Perfil_Repartidor
import com.upc.tf_grupo03_microdelivery.R
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios

class ListaRepartidor : AppCompatActivity(), Adaptor.OnItemClickListener {

    private lateinit var rvUsuarios: RecyclerView
    private lateinit var usuariosDAO: UsuariosDAO
    private var adaptador: Adaptor? = null

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
        adaptador= Adaptor(this)
        rvUsuarios.adapter=adaptador
    }
    override fun onItemClicked(usuario: Usuarios) {
        val intent = Intent(this, Perfil_Repartidor::class.java)
        intent.putExtra("EXTRA_USUARIO_ID", usuario.id)
        // Puedes pasar otros datos del usuario si es necesario
        startActivity(intent)
        // Aquí manejas el clic en un ítem del RecyclerView.
        // Por ejemplo, puedes mostrar un Toast o iniciar una nueva actividad
        //Toast.makeText(this, "Clic en: ${usuario.nombres}", Toast.LENGTH_SHORT).show()
    }
    private fun mostrarUsuarios(){
        val listaUsuarios = usuariosDAO.cargarUsuarios()
        adaptador?.agregarItems(listaUsuarios)
        Log.d("===","${listaUsuarios.size}")
    }
}