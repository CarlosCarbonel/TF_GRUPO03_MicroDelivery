package com.upc.tf_grupo03_microdelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO

class Perfil_Repartidor : AppCompatActivity() {
    private lateinit var usuariosDAO: UsuariosDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_repartidor)
        usuariosDAO = UsuariosDAO(this)

        val usuarioId = intent.getIntExtra("EXTRA_USUARIO_ID", -1)

        val usuario = usuariosDAO.obtenerUsuarioPorId(usuarioId)

        if (usuario != null) {

            findViewById<TextView>(R.id.nombreRepartidor).text = usuario.nombres
            findViewById<TextView>(R.id.apellidoRepartidor).text = usuario.apellidos
            findViewById<TextView>(R.id.direccionRepartidor).text = usuario.direccion
            findViewById<TextView>(R.id.distritoRepartidor).text = usuario.distrito

        } else {
            // Manejar el caso en que el usuario no se encuentra
        }
    }

}