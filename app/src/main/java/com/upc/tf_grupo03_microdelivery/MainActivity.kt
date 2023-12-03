package com.upc.tf_grupo03_microdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btncrearusuario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crearUsuario()
    }

    private fun crearUsuario(){
        btncrearusuario = findViewById(R.id.btncrearusuario)
        btncrearusuario.setOnClickListener({
            val intent = Intent(this, CrearUsuarioActivity::class.java)
            startActivity(intent)
        })
    }


}