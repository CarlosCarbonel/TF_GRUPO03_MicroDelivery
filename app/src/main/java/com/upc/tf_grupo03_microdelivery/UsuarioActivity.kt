package com.upc.tf_grupo03_microdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.upc.tf_grupo03_microdelivery.util.ListaRepartidor
import com.upc.tf_grupo03_microdelivery.util.lista_contactos

class UsuarioActivity : AppCompatActivity() {

    private lateinit var txtmsnombapellidos: TextView
    private lateinit var btnbuscarepartidor: Button
    private lateinit var btncontactos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        verificarAcceso()
        buscarRepartidor()
        buscarContacto()
    }

    private fun verificarAcceso() {
        if (intent.hasExtra("p_name")) {
            txtmsnombapellidos = findViewById(R.id.txtmsnombapellidos)
            txtmsnombapellidos.setText(intent.getStringExtra("p_name"))
        }
    }

    private fun buscarRepartidor(){
        btnbuscarepartidor = findViewById(R.id.btnbuscarepartidor)
        btnbuscarepartidor.setOnClickListener({
            val intent = Intent(this, ListaRepartidor::class.java)
            startActivity(intent)
        })
    }

    private fun buscarContacto(){
        btncontactos = findViewById(R.id.btncontactos)
        btncontactos.setOnClickListener({
            val intent = Intent(this, lista_contactos::class.java)
            startActivity(intent)
        })
    }
}