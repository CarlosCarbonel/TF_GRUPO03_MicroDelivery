package com.upc.tf_grupo03_microdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.upc.tf_grupo03_microdelivery.util.ListaContactos
import com.upc.tf_grupo03_microdelivery.util.ListaRepartidor


class UsuarioActivity : AppCompatActivity() {

    private lateinit var txtmsnombapellidos: TextView
    private lateinit var btnbuscarepartidor: Button
    private lateinit var btncontactos: Button
    private lateinit var txtmscorreo: TextView

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
            txtmscorreo = findViewById(R.id.txtmscorreo)
            txtmsnombapellidos.setText(intent.getStringExtra("p_name"))
            txtmscorreo.setText(intent.getStringExtra("p_email"))
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
            val intent = Intent(this, ListaContactos::class.java)
            startActivity(intent)
        })
    }
}