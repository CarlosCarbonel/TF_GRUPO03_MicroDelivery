package com.upc.tf_grupo03_microdelivery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import com.upc.tf_grupo03_microdelivery.dao.ContactosDAO
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO
import com.upc.tf_grupo03_microdelivery.entidades.Contactos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Perfil_Repartidor : AppCompatActivity() {
    private lateinit var usuariosDAO: UsuariosDAO
    private lateinit var contactosDAO: ContactosDAO
    private var usuarioId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_repartidor)

            usuariosDAO = UsuariosDAO(this)

            usuarioId = intent.getIntExtra("EXTRA_USUARIO_ID", -1)

            val usuario = usuariosDAO.obtenerUsuarioPorId(usuarioId)

            if (usuario != null) {

                findViewById<TextView>(R.id.nombreRepartidor).text = usuario.nombres
                findViewById<TextView>(R.id.apellidoRepartidor).text = usuario.apellidos
                findViewById<TextView>(R.id.direccionRepartidor).text = usuario.direccion
                findViewById<TextView>(R.id.distritoRepartidor).text = usuario.distrito

            } else {
                // Manejar el caso en que el usuario no se encuentra
            }
        val miBoton = findViewById<Button>(R.id.btnpopupllamada)
        miBoton.setOnClickListener {
            mostrarPopup(it)
        }
        contactosDAO =ContactosDAO(this)


        val btnguardar=findViewById<Button>(R.id.btnagregarcontacto)
        btnguardar.setOnClickListener {
            agregarContacto()
        }
    }

    private fun mostrarPopup(anchorView: View) {
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.activity_popupllamada, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Configura si el popup se cierra al tocar fuera de él
        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true

        // Muestra el PopupWindow
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0)
    }


    private fun agregarContacto() {
        val usuario = usuariosDAO.obtenerUsuarioPorId(usuarioId)
        usuario?.let {
            val nuevoContacto = Contactos().apply {
            usuarioId = it.id
            cdni = it.dni
            cnombres = it.nombres
            capellidos = it.apellidos
            ccorreo = it.correo
            cdistrito = it.distrito
            cdireccion = it.direccion
            ctipoUsuario = it.tipoUsuario
        }

            val resultado = contactosDAO.agregarContacto(nuevoContacto)
            if (resultado > -1) {
                Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al agregar contacto", Toast.LENGTH_SHORT).show()
            }
        }
    }

}