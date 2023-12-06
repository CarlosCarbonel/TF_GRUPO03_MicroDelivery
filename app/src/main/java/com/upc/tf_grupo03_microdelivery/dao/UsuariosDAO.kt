package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.SqliteDB


class UsuariosDAO(context: Context) {

    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun registrarUsuario(usuario: Usuarios): String {

        if (existeUsuario(usuario.dni)) {
            return "Usuario ya registrado"
        }

        var respuesta = ""

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
            if (resultado == -1L) {
                respuesta = "Error al registrar"
            } else {
                respuesta = "Se registró correctamente"
            }

        } catch (e: Exception) {
            respuesta = e.message.toString()
        } finally {
            db.close()
        }

        return respuesta
    }

    fun login(username: String, password: String): Usuarios {
        var usuario = Usuarios()
        val db = sqliteDB.readableDatabase
        val query =
            "SELECT * FROM usuarios where us_usuario='$username' and us_contrasena='$password'"

        val cursor: Cursor

        try {
            cursor = db.rawQuery(query, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow("us_id"))
                    val dni: String = cursor.getString(cursor.getColumnIndexOrThrow("us_dni"))
                    val nombres: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_nombres"))
                    val apellidos: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_apellidos"))
                    val correo: String = cursor.getString(cursor.getColumnIndexOrThrow("us_correo"))
                    val distrito: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_distrito"))
                    val direccion: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_direccion"))
                    val tipousaurio: Int = cursor.getInt(cursor.getColumnIndexOrThrow("tipous_id"))

                    usuario.id = id
                    usuario.dni = dni
                    usuario.nombres = nombres
                    usuario.apellidos = apellidos
                    usuario.correo = correo
                    usuario.distrito = distrito
                    usuario.direccion = direccion
                    usuario.tipoUsuario = tipousaurio

                } while (cursor.moveToNext())
            } else {
                usuario.flgencontrado = false
            }

        } catch (e: Exception) {
            usuario.flgencontrado = false
            e.printStackTrace()
        } finally {
            db.close()
        }
        return usuario
    }

    fun cargarUsuarios(): ArrayList<Usuarios> {
        val listaUsuarios: ArrayList<Usuarios> = ArrayList()
        val query = "SELECT * FROM usuarios"
        val db = sqliteDB.readableDatabase
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow("us_id"))
                    val dni: String = cursor.getString(cursor.getColumnIndexOrThrow("us_dni"))
                    val nombres: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_nombres"))
                    val apellidos: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_apellidos"))
                    val correo: String = cursor.getString(cursor.getColumnIndexOrThrow("us_correo"))
                    val distrito: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_distrito"))
                    val direccion: String =
                        cursor.getString(cursor.getColumnIndexOrThrow("us_direccion"))
                    val tipousaurio: Int = cursor.getInt(cursor.getColumnIndexOrThrow("tipous_id"))

                    var usuario = Usuarios()

                    usuario.id = id
                    usuario.dni = dni
                    usuario.nombres = nombres
                    usuario.apellidos = apellidos
                    usuario.correo = correo
                    usuario.distrito = distrito
                    usuario.direccion = direccion
                    usuario.tipoUsuario = tipousaurio

                    listaUsuarios.add(usuario)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {

        } finally {
            db.close()
        }
        return listaUsuarios
    }

    fun existeUsuario(dni: String): Boolean {
        val db = sqliteDB.readableDatabase
        val cursor = db.query(
            "usuarios",
            arrayOf("us_dni"),
            "us_dni = ?",
            arrayOf(dni),
            null, null, null
        )

        val existe = cursor.count > 0
        cursor.close()
        return existe
    }

    fun obtenerUsuarioPorId(id: Int): Usuarios? {
        val db = sqliteDB.readableDatabase
        val cursor = db.query(
            "Usuarios",
            null,
            "us_id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        var usuario: Usuarios? = null
        if (cursor.moveToFirst()) {
            usuario = Usuarios().apply {
                val idIndex = cursor.getColumnIndex("us_id")
                if (idIndex != -1) this.id = cursor.getInt(idIndex)

                val nombresIndex = cursor.getColumnIndex("us_nombres")
                if (nombresIndex != -1) this.nombres = cursor.getString(nombresIndex)

                val apellidosIndex = cursor.getColumnIndex("us_apellidos")
                if (apellidosIndex != -1) this.apellidos = cursor.getString(apellidosIndex)

                val usuarioIndex = cursor.getColumnIndex("us_usuario")
                if (usuarioIndex != -1) this.usuario = cursor.getString(usuarioIndex)

                val contrasenaIndex = cursor.getColumnIndex("us_contrasena")
                if (contrasenaIndex != -1) this.contrasena = cursor.getString(contrasenaIndex)

                val correoIndex = cursor.getColumnIndex("us_correo")
                if (correoIndex != -1) this.correo = cursor.getString(correoIndex)

                val distritoIndex = cursor.getColumnIndex("us_distrito")
                if (distritoIndex != -1) this.distrito = cursor.getString(distritoIndex)

                val direccionIndex = cursor.getColumnIndex("us_direccion")
                if (direccionIndex != -1) this.direccion = cursor.getString(direccionIndex)

                val tipoUsuarioIndex = cursor.getColumnIndex("us_tipoUsuario")
                if (tipoUsuarioIndex != -1) this.tipoUsuario = cursor.getInt(tipoUsuarioIndex)
                // Continuar con más campos si es necesario

                val dniIndex = cursor.getColumnIndex("us_dni")
                if (dniIndex != -1) this.dni = cursor.getString(dniIndex)

            }
        }

        cursor.close()
        return usuario
    }

    fun actualizarUsuario(persona: Usuarios):String{

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

            var resultado = db.update("usuarios", valores, "us_id="+persona.id,null)
            if (resultado == -1){
                respuesta = "Error al modificar"
            }else{
                respuesta = "Se actualizó correctamente"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally {
            db.close()
        }

        return respuesta

    }

}

