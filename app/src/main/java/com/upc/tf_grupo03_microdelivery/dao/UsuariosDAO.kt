package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.SqliteDB


class UsuariosDAO(context: Context) {

    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun registrarPersona(persona: Usuarios):String{

        var respuesta=""

        val db = sqliteDB.writableDatabase
        try {

            val valores = ContentValues()
            valores.put("us_dni", persona.dni)
            valores.put("us_nombres", persona.nombres)
            valores.put("us_apellidos", persona.apellidos)
            valores.put("us_usuario", persona.usuario)
            valores.put("us_contrasena", persona.contrasena)
            valores.put("us_correo", persona.correo)
            valores.put("us_distrito", persona.distrito)
            valores.put("us_direccion", persona.direccion)
            valores.put("tipous_id", persona.tipoUsuario)

            var resultado = db.insert("usuarios", null, valores)
            if (resultado == -1L){
                respuesta = "Error al registrar"
            }else{
                respuesta = "Se registró correctamente"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally {
            db.close()
        }

        return respuesta

    }



}