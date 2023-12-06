package com.upc.tf_grupo03_microdelivery

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.upc.tf_grupo03_microdelivery.dao.TipoUsuarioDAO
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO
import com.upc.tf_grupo03_microdelivery.entidades.TipoUsuario
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios

class CrearUsuarioActivity : AppCompatActivity() {

    private lateinit var edcuenta: EditText
    private lateinit var edpass: EditText
    private lateinit var ednombres: EditText
    private lateinit var edapellidos: EditText
    private lateinit var eddni: EditText
    private lateinit var eddistrito: EditText
    private lateinit var eddireccion: EditText
    private lateinit var edcorreo: EditText
    private lateinit var btncusuario: Button
    private lateinit var txtTituloForm: TextView

    private lateinit var usuarioDAO: UsuariosDAO
    private lateinit var tipoUsuarioDAO: TipoUsuarioDAO
    private var id:Int = 0

    private var modificar:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)
        cargarTipoUsuario()
        verificaSiActualiza()
        asignarReferencias()
    }

    private fun verificaSiActualiza() {
        if (intent.hasExtra("p_id")){
            modificar = true

            var numero:String? = ""
            numero = intent.getStringExtra("p_id")

            id = Integer.parseInt(numero)

            txtTituloForm = findViewById(R.id.txtTituloForm)
            btncusuario = findViewById(R.id.btncusuario)

            txtTituloForm.setText("Editar Usuario")
            btncusuario.setText("Guardar cambios")

            val usuariosDAOV = UsuariosDAO(this)
            val objPersona = usuariosDAOV.obtenerUsuarioPorId(id)
            if (objPersona!=null){
                edcuenta = findViewById(R.id.edcuenta)
                edpass = findViewById(R.id.edpass)
                ednombres = findViewById(R.id.ednombres)
                edapellidos = findViewById(R.id.edapellidos)
                eddni = findViewById(R.id.eddni)
                eddistrito = findViewById(R.id.eddistrito)
                eddireccion = findViewById(R.id.eddireccion)
                edcorreo  = findViewById(R.id.edcorreo)

                edcuenta.setText(objPersona.usuario)
                edpass.setText(objPersona.contrasena)
                ednombres.setText(objPersona.nombres)
                edapellidos.setText(objPersona.apellidos)
                eddni.setText(objPersona.dni)
                eddistrito.setText(objPersona.distrito)
                eddireccion.setText(objPersona.direccion)
                edcorreo.setText(objPersona.correo)
            }

        }
    }

    private fun asignarReferencias(){

        edcuenta = findViewById(R.id.edcuenta)
        edpass = findViewById(R.id.edpass)
        ednombres = findViewById(R.id.ednombres)
        edapellidos = findViewById(R.id.edapellidos)
        eddni = findViewById(R.id.eddni)
        eddistrito = findViewById(R.id.eddistrito)
        eddireccion = findViewById(R.id.eddireccion)
        edcorreo  = findViewById(R.id.edcorreo)

        btncusuario = findViewById(R.id.btncusuario)

        usuarioDAO = UsuariosDAO(this)

        btncusuario.setOnClickListener({registrarPersona()})
    }

    private fun registrarPersona() {
        val dni = eddni.text.toString()
        val cuenta = edcuenta.text.toString()
        val contrasena = edpass.text.toString()
        val nombres = ednombres.text.toString()
        val apellidos = edapellidos.text.toString()
        val distrito = eddistrito.text.toString()
        val direccion = eddireccion.text.toString()
        val correo = edcorreo.text.toString()

        if (dni.isEmpty() || cuenta.isEmpty() || contrasena.isEmpty() || nombres.isEmpty()
            || apellidos.isEmpty()|| distrito.isEmpty()|| direccion.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show()
        }else{
            val objUsuario = Usuarios()
            val spTipoUsuario = findViewById<Spinner>(R.id.spTipoUsuario)
            val tpusuario: TipoUsuario = spTipoUsuario.selectedItem as TipoUsuario

            objUsuario.dni = dni
            objUsuario.usuario = cuenta
            objUsuario.contrasena = contrasena
            objUsuario.nombres = nombres
            objUsuario.apellidos = apellidos
            objUsuario.distrito = distrito
            objUsuario.direccion = direccion
            objUsuario.correo = correo
            objUsuario.tipoUsuario = tpusuario.id

            val usuariosDAO = UsuariosDAO(this)
            var mensaje = ""

            if (modificar == false){
                mensaje = usuariosDAO.registrarUsuario(objUsuario)
            }else{
                objUsuario.id = id
                mensaje = usuariosDAO.actualizarUsuario(objUsuario)
            }

            mostrarMensaje(mensaje)
            limpiarCajas()
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

    private fun limpiarCajas(){
        edcuenta.setText("")
        edpass.setText("")
        ednombres.setText("")
        edapellidos.setText("")
        eddni.setText("")
        eddireccion.setText("")
        eddistrito.setText("")
    }

    private fun cargarTipoUsuario() {
        val tipoUsuarioDAO = TipoUsuarioDAO(this)
        val listaTipoUsuario:List<TipoUsuario?> = tipoUsuarioDAO.cargarTipoUsuario()
        val adaptador = ArrayAdapter<TipoUsuario>(this, android.R.layout.simple_spinner_item, listaTipoUsuario)
        //adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spTipoUsuario = findViewById<Spinner>(R.id.spTipoUsuario)
        spTipoUsuario.adapter = adaptador
    }

}