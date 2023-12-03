package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.SqliteDB


class UsuariosDAO(context: Context) {

    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun registrarUsuario(usuario: Usuarios):String{

        var respuesta=""

        val db = sqliteDB.writableDatabase
        try {

            val valores = ContentValues()
            valores.put("us_dni", usuario.dni)
            valores.put("us_nombres", usuario.nombres)
            valores.put("us_apellidos", usuario.apellidos)
            valores.put("us_usuario", usuario.usuario)
            valores.put("us_contrasena", usuario.contrasena)
            valores.put("us_correo", usuario.correo)
            valores.put("us_distrito", usuario.distrito)
            valores.put("us_direccion", usuario.direccion)
            valores.put("tipous_id", usuario.tipoUsuario)

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