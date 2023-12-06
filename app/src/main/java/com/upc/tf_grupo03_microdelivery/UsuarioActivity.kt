package com.upc.tf_grupo03_microdelivery

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.upc.tf_grupo03_microdelivery.entidades.Contactos
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.Adaptor
import com.upc.tf_grupo03_microdelivery.util.ListaContactos
import com.upc.tf_grupo03_microdelivery.util.ListaRepartidor
import com.upc.tf_grupo03_microdelivery.util.ProductosEnviados


class UsuarioActivity : AppCompatActivity() {

    private lateinit var txtmsnombapellidos: TextView
    private lateinit var btnbuscarepartidor: Button
    private lateinit var btncontactos: Button
    private lateinit var txtmscorreo: TextView
    private lateinit var txtmscelular: TextView
    private lateinit var btnactdatos: Button
    private lateinit var btnprodenviados: Button
    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        verificarAcceso()
        buscarRepartidor()
        buscarContacto()
        actualizarDatosUsuario()
        productosEnviados()
        cargarfoto()
        //val btnAbrirPerfilRepartidor = findViewById<Button>(R.id.btnprodenviados)
        //btnAbrirPerfilRepartidor.setOnClickListener {
        //    abrirPerfilRepartidor()
        //}
    }

    private fun actualizarDatosUsuario() {
        btnactdatos = findViewById(R.id.btnactdatos)
        txtmscelular = findViewById(R.id.txtmscelular)
        btnactdatos.setOnClickListener({
            val intent = Intent(this,CrearUsuarioActivity::class.java)
            intent.putExtra("p_id", txtmscelular.text)
            startActivity(intent)
        })
    }

    private fun verificarAcceso() {
        if (intent.hasExtra("p_name")) {
            txtmsnombapellidos = findViewById(R.id.txtmsnombapellidos)
            txtmscorreo = findViewById(R.id.txtmscorreo)
            txtmscelular = findViewById(R.id.txtmscelular)

            txtmsnombapellidos.setText(intent.getStringExtra("p_name"))
            txtmscorreo.setText(intent.getStringExtra("p_email"))
            txtmscelular.setText(intent.getIntExtra("p_id",0).toString())
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

    private fun productosEnviados(){
      btnprodenviados = findViewById(R.id.btnprodenviados)
        btnprodenviados.setOnClickListener({
            val intent = Intent(this, ProductosEnviados::class.java)
           startActivity(intent)
       })
    }
    //private fun abrirPerfilRepartidor(){
    //      val intent = Intent(this, Perfil_Repartidor::class.java)
    //        val usuarioId = obtenerIdUsuarioLogueado()
    //        intent.putExtra("EXTRA_USUARIO_ID", usuarioId)
    //        startActivity(intent)
    //}
    private fun obtenerIdUsuarioLogueado(): Int {
        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val usuarioId =
            sharedPreferences.getInt("usuarioId", -1) // Retorna -1 si no se encuentra el ID

        // Agregar un log para verificar el valor obtenido
        Log.d("UsuarioActivity", "ID del usuario logueado: $usuarioId")

        return usuarioId
    }
    private fun cargarfoto(){
        val btnAbrirGaleria = findViewById<ImageButton>(R.id.btncamara)
        btnAbrirGaleria.setOnClickListener{
            abrirGaleria()
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imagenUri = data?.data
            // Aquí puedes usar la URI de la imagen para mostrarla en tu aplicación
        }
    }
}