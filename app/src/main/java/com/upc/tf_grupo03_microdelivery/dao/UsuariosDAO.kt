package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun login(username: String, password:String):Usuarios{
        var usuario = Usuarios()
        val db = sqliteDB.readableDatabase
        val query = "SELECT * FROM usuarios where us_usuario='$username' and us_contrasena='$password'"

        val cursor: Cursor

        try {
            cursor = db.rawQuery(query,null)
            if (cursor.count>0){
                cursor.moveToFirst()
                do{
                    val id:Int = cursor.getInt(cursor.getColumnIndexOrThrow("us_id"))
                    val dni:String = cursor.getString(cursor.getColumnIndexOrThrow("us_dni"))
                    val nombres:String = cursor.getString(cursor.getColumnIndexOrThrow("us_nombres"))
                    val apellidos:String = cursor.getString(cursor.getColumnIndexOrThrow("us_apellidos"))
                    val correo:String = cursor.getString(cursor.getColumnIndexOrThrow("us_correo"))
                    val distrito:String = cursor.getString(cursor.getColumnIndexOrThrow("us_distrito"))
                    val direccion:String = cursor.getString(cursor.getColumnIndexOrThrow("us_direccion"))
                    val tipousaurio:Int = cursor.getInt(cursor.getColumnIndexOrThrow("tipous_id"))

                    usuario.id=id
                    usuario.dni=dni
                    usuario.nombres=nombres
                    usuario.apellidos=apellidos
                    usuario.correo=correo
                    usuario.distrito = distrito
                    usuario.direccion=direccion
                    usuario.tipoUsuario = tipousaurio

                }while(cursor.moveToNext())
            }else{
                usuario.flgencontrado = false
            }

        }catch (e:Exception){
            usuario.flgencontrado = false
            e.printStackTrace()
        }finally {
            db.close()
        }
        return usuario
    }

    fun cargarUsuarios():ArrayList<Usuarios>{
        val listaUsuarios:ArrayList<Usuarios> = ArrayList()
        val query = "SELECT * FROM usuarios"
        val db= sqliteDB.readableDatabase
        val cursor:Cursor
        try {
            cursor = db.rawQuery(query, null)
            if (cursor.count>0){
                cursor.moveToFirst()
                do{
                    val id:Int = cursor.getInt(cursor.getColumnIndexOrThrow("us_id"))
                    val dni:String = cursor.getString(cursor.getColumnIndexOrThrow("us_dni"))
                    val nombres:String = cursor.getString(cursor.getColumnIndexOrThrow("us_nombres"))
                    val apellidos:String = cursor.getString(cursor.getColumnIndexOrThrow("us_apellidos"))
                    val correo:String = cursor.getString(cursor.getColumnIndexOrThrow("us_correo"))
                    val distrito:String = cursor.getString(cursor.getColumnIndexOrThrow("us_distrito"))
                    val direccion:String = cursor.getString(cursor.getColumnIndexOrThrow("us_direccion"))
                    val tipousaurio:Int = cursor.getInt(cursor.getColumnIndexOrThrow("tipous_id"))

                    var usuario = Usuarios()

                    usuario.id=id
                    usuario.dni=dni
                    usuario.nombres=nombres
                    usuario.apellidos=apellidos
                    usuario.correo=correo
                    usuario.distrito = distrito
                    usuario.direccion=direccion
                    usuario.tipoUsuario = tipousaurio

                    listaUsuarios.add(usuario)
                }while (cursor.moveToNext())
            }
        }catch (e:Exception){

        }finally {
            db.close()
        }
        return listaUsuarios
    }


}