package com.upc.tf_grupo03_microdelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO

class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var edcuenta: EditText
    private lateinit var edpass: EditText
    private lateinit var ednombres: EditText
    private lateinit var edapellidos: EditText
    private lateinit var eddni: EditText
    private lateinit var spTipoUsuario: Spinner
    private lateinit var eddistrito: EditText
    private lateinit var eddireccion: EditText
    private lateinit var btncusuario: Button

    private lateinit var usuarioDAO: UsuariosDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)
    }

    private fun asignarReferencias(){
        edcuenta = findViewById(R.id.edcuenta)
        edpass = findViewById(R.id.edpass)
        ednombres = findViewById(R.id.ednombres)
        edapellidos = findViewById(R.id.edapellidos)
        eddni = findViewById(R.id.eddni)
        spTipoUsuario = findViewById(R.id.spTipoUsuario)
        eddistrito = findViewById(R.id.eddistrito)
        eddireccion = findViewById(R.id.eddireccion)

        btncusuario = findViewById(R.id.btncusuario)

        usuarioDAO = UsuariosDAO(this)

        btncusuario.setOnClickListener({registrarPersona()})
    }

    private fun registrarPersona() {
        TODO("Not yet implemented")
    }
}