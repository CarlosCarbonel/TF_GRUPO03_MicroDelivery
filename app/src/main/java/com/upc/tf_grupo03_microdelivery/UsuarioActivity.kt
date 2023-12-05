package com.upc.tf_grupo03_microdelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UsuarioActivity : AppCompatActivity() {

    private lateinit var txtmsnombapellidos: TextView
    private lateinit var txtmscorreo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        verificarAcceso()
    }

    private fun verificarAcceso() {
        if (intent.hasExtra("p_name")) {
            txtmsnombapellidos = findViewById(R.id.txtmsnombapellidos)
            txtmscorreo = findViewById(R.id.txtmscorreo)
            txtmsnombapellidos.setText(intent.getStringExtra("p_name"))
            txtmscorreo.setText(intent.getStringExtra("p_email"))
        }
    }
}