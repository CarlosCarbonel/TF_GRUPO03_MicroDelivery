package com.upc.tf_grupo03_microdelivery.util

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.upc.tf_grupo03_microdelivery.MainActivity
import com.upc.tf_grupo03_microdelivery.R

class RecuperarContrasena : AppCompatActivity() {

    private lateinit var txtCorreo:EditText
    private lateinit var btnEnviar:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contrasena)
        asignarReferencias()
    }

    private fun asignarReferencias(){
        txtCorreo = findViewById(R.id.txtCorreo)
        btnEnviar = findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener({enviarCorreo()})
    }

    private fun enviarCorreo(){
        val correo = txtCorreo.text.toString()

        if (correo.isEmpty()){
            Toast.makeText(this,"Escribir correo",Toast.LENGTH_SHORT).show()
        }else{
            val mensaje = "Se envío el correo"
            mostrarMensaje(mensaje)
        }
    }
    private fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Información")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialogInterface, i ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }
}