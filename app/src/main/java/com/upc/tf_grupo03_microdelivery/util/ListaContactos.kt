package com.upc.tf_grupo03_microdelivery.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upc.tf_grupo03_microdelivery.Perfil_Repartidor
import com.upc.tf_grupo03_microdelivery.R
import com.upc.tf_grupo03_microdelivery.dao.ContactosDAO
import com.upc.tf_grupo03_microdelivery.entidades.Contactos

class ListaContactos : AppCompatActivity(), Adaptorc.OnItemClickListener {

    private lateinit var rvContactos: RecyclerView
    private lateinit var contactosDAO: ContactosDAO
    private var adaptadorc: Adaptorc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_contactos)
        asignarReferencias()
        mostrarContactos()

    }
    private fun asignarReferencias(){
        contactosDAO = ContactosDAO(this)
        rvContactos=findViewById(R.id.rvContactos)
        rvContactos.layoutManager= LinearLayoutManager(this)
        adaptadorc= Adaptorc(this)
        rvContactos.adapter=adaptadorc
    }
    override fun onItemClicked(contacto: Contactos) {
        val intent = Intent(this, Perfil_Repartidor::class.java)
        intent.putExtra("EXTRA_USUARIO_ID_C", contacto.usuarioId)
        // Puedes pasar otros datos del usuario si es necesario
        startActivity(intent)
        // Aquí manejas el clic en un ítem del RecyclerView.
        // Por ejemplo, puedes mostrar un Toast o iniciar una nueva actividad
        //Toast.makeText(this, "Clic en: ${usuario.nombres}", Toast.LENGTH_SHORT).show()
    }
    private fun mostrarContactos(){
        val listaContactos = contactosDAO.cargarContactos()
        adaptadorc?.agregarItems(listaContactos)
        Log.d("===","${listaContactos.size}")
    }
    override fun onEliminarClicked(contacto: Contactos, position: Int) {
      // Eliminar el contacto de la base de datos
      val exito= contactosDAO.eliminarContacto(contacto.cid)
      if (exito){
      // Actualizar la lista en el adaptador
      adaptadorc?.let {
          it.listaContactos.removeAt(position)
          it.notifyItemRemoved(position)
      }
      Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(this, "Error al eliminar contacto", Toast.LENGTH_SHORT).show()
    }
    }
}