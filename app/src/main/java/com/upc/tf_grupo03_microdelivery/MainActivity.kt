package com.upc.tf_grupo03_microdelivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.RecuperarContrasena

class MainActivity : AppCompatActivity() {

    private lateinit var txtusu: EditText
    private lateinit var txtpwd:EditText
    private lateinit var btncrearusuario: Button
    private lateinit var btningresar: Button
    private lateinit var btnRecuperar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        asignarReferencias()
        crearUsuario()
        login()
        recuperarContraseña()

    }

    private fun asignarReferencias() {
        //txtmsnombapellidos = findViewById(R.id.txtmsnombapellidos)
    }

    private fun crearUsuario(){
        btncrearusuario = findViewById(R.id.btncrearusuario)
        btncrearusuario.setOnClickListener({
            val intent = Intent(this, CrearUsuarioActivity::class.java)
            startActivity(intent)
        })
    }

    private fun login(){
        btningresar = findViewById(R.id.btningresar)
        btningresar.setOnClickListener({validarIngreso()})
    }


    private fun recuperarContraseña(){
        btnRecuperar = findViewById(R.id.btnrecuperarpwd)
        btnRecuperar.setOnClickListener({
            val intent = Intent(this, RecuperarContrasena::class.java)
            startActivity(intent)
        })
    }

    private fun validarIngreso(){

        txtusu = findViewById(R.id.txtusu)
        txtpwd = findViewById(R.id.txtpwd)

        val user = txtusu.text.toString()
        val password = txtpwd.text.toString()


        if (validaCamposIngreso(user, password)){

            val usuarioDAO = UsuariosDAO(this)

            var usuarioLogueado:Usuarios = usuarioDAO.login(user,password)

            if (usuarioLogueado != null){
                if (usuarioLogueado.flgencontrado){
                    guardarIdUsuarioLogueado(usuarioLogueado.id)
                    val intent = Intent(this, UsuarioActivity::class.java)
                    intent.putExtra("p_name", usuarioLogueado.nombres + " " + usuarioLogueado.apellidos)
                    intent.putExtra("p_email", usuarioLogueado.correo)
                    intent.putExtra("p_id", usuarioLogueado.id)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "No se encontraron resultados con los valores ingresados", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
    private fun guardarIdUsuarioLogueado(id: Int) {
        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putInt("usuarioId", id)
            apply()
        }
    }

    private fun validaCamposIngreso(inusu:String, inpwd:String):Boolean{
        var valida:Boolean=true

        if (inusu.isEmpty() || inpwd.isEmpty()){
            valida = false
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show()
        }

        return valida
    }

}