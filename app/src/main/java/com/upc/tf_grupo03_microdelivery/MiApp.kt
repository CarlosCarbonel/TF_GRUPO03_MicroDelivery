package com.upc.tf_grupo03_microdelivery

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.upc.tf_grupo03_microdelivery.dao.UsuariosDAO
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios


class MiApp : Application() {

    private lateinit var usuariosDAO: UsuariosDAO

    override fun onCreate() {
        super.onCreate()
        asignarReferencias()
        inicializarUsuarios()

    }
    private fun asignarReferencias(){
        usuariosDAO = UsuariosDAO(this)
    }

    private fun inicializarUsuarios(){
        val listaUsuarios = listOf(
            Usuarios().apply {
                dni = "43378433"
                nombres = "Carlos"
                apellidos = "Carbonel Del Castillo"
                usuario = "CarlosCD"
                contrasena = "prueba1"
                correo = "carlosc@gmail.com"
                distrito = "La Victoria"
                direccion = "Jr. Los Corales 128"
                tipoUsuario= 1
            },
            Usuarios().apply {
                dni = "55566777"
                nombres = "Alex"
                apellidos = "Puelles Bermeo"
                usuario = "AlexPB"
                contrasena = "prueba2"
                correo = "alexpb@gmail.com"
                distrito = "Lince"
                direccion = "Jr. Los agatas 122"
                tipoUsuario= 2
            },
            Usuarios().apply {
                dni = "77788777"
                nombres = "Gerson"
                apellidos = "Encalada De La Cruz"
                usuario = "GersonEDC"
                contrasena = "prueba3"
                correo = "Gersonedc@gmail.com"
                distrito = "Jesus María"
                direccion = "Jr. Los rubies 180"
                tipoUsuario= 1
            },
            Usuarios().apply {
                dni = "99988777"
                nombres = "Miguel"
                apellidos = "Espinoza Surichaqui"
                usuario = "Migueles"
                contrasena = "prueba44"
                correo = "migueles@gmail.com"
                distrito = "San Isidro"
                direccion = "Jr. Los diamantes 2020"
                tipoUsuario= 2
            }
        )


        listaUsuarios.forEach{usuario->
            val resultado = usuariosDAO.registrarUsuario(usuario)
            Log.d("MiApp",resultado)

        }

    }

}