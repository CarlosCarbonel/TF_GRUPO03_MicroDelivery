package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.upc.tf_grupo03_microdelivery.entidades.Contactos
import com.upc.tf_grupo03_microdelivery.entidades.Usuarios
import com.upc.tf_grupo03_microdelivery.util.SqliteDB

class ContactosDAO (context:Context) {
    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun agregarContacto(contacto: Contactos): Long {
        val db = sqliteDB.writableDatabase
        val values = ContentValues().apply {
            put("usuarioId", contacto.usuarioId)
            put("contactodni", contacto.cdni)
            put("contactoNombres", contacto.cnombres)
            put("contactoApellidos", contacto.capellidos)
            put("contactoCorreo", contacto.ccorreo)
            put("contactoDistrito", contacto.cdistrito)
            put("contactoDireccion", contacto.cdireccion)
            put("contactoTipous", contacto.ctipoUsuario)
        }
        val id = db.insert("Contactos", null, values)
        db.close()
        return id
    }
    fun obtenerContactosPorUsuario(usuarioId: Int): List<Contactos> {
        val listaContactos = mutableListOf<Contactos>()
        val db = sqliteDB.readableDatabase

        val cursor = db.query(
            "Contactos",
            null,
            "usuarioId = ?",
            arrayOf(usuarioId.toString()),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val contactoIdIndex = cursor.getColumnIndex("contactoId")
                //val usuarioIdIndex = cursor.getColumnIndex("usuarioId")
                val contactoDniIndex = cursor.getColumnIndex("contactoDni")
                val contactoNombresIndex = cursor.getColumnIndex("contactoNombres")
                val contactoApellidosIndex = cursor.getColumnIndex("contactoApellidos")
                val contactoCorreoIndex = cursor.getColumnIndex("contactoCorreo")
                val contactoDistritoIndex = cursor.getColumnIndex("contactoDistrito")
                val contactoDireccionIndex = cursor.getColumnIndex("contactoDireccion")
                val contactoTipousIndex = cursor.getColumnIndex("contactoTipous")

                if (contactoIdIndex != -1  && contactoNombresIndex != -1 && contactoApellidosIndex != -1&& contactoCorreoIndex != -1&& contactoDistritoIndex != -1&& contactoDireccionIndex != -1&& contactoTipousIndex != -1) {
                val contacto = Contactos().apply {
                    cid=cursor.getInt(contactoIdIndex)
                    //usuarioId=cursor.getInt(usuarioIdIndex)
                    cdni=cursor.getString(contactoDniIndex)
                    cnombres=cursor.getString(contactoNombresIndex)
                    capellidos=cursor.getString(contactoApellidosIndex)
                    ccorreo=cursor.getString(contactoCorreoIndex)
                    cdistrito=cursor.getString(contactoDistritoIndex)
                    cdireccion=cursor.getString(contactoDireccionIndex)
                    ctipoUsuario=cursor.getInt(contactoTipousIndex)
                }
                    listaContactos.add(contacto)
                }

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return listaContactos
    }
    fun cargarContactos(): ArrayList<Contactos> {
        val listaContactos: ArrayList<Contactos> = ArrayList()
        val query = "SELECT * FROM Contactos"
        val db = sqliteDB.readableDatabase
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val cid: Int = cursor.getInt(cursor.getColumnIndexOrThrow("contactoId"))
                    val cdni: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoDni"))
                    val usuarioId: Int = cursor.getInt(cursor.getColumnIndexOrThrow("usuarioId"))
                    val cnombres: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoNombres"))
                    val capellidos: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoApellidos"))
                    val ccorreo: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoCorreo"))
                    val cdistrito: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoDistrito"))
                    val cdireccion: String = cursor.getString(cursor.getColumnIndexOrThrow("contactoDireccion"))
                    val ctipousaurio: Int = cursor.getInt(cursor.getColumnIndexOrThrow("contactoTipous"))

                    var contacto = Contactos()

                    contacto.cid = cid
                    contacto.cdni = cdni
                    contacto.usuarioId=usuarioId
                    contacto.cnombres=cnombres
                    contacto.capellidos=capellidos
                    contacto.ccorreo=ccorreo
                    contacto.cdistrito=cdistrito
                    contacto.cdireccion=cdireccion
                    contacto.ctipoUsuario=ctipousaurio

                    listaContactos.add(contacto)
                } while (cursor.moveToNext())
            }else {
                Log.d("ListaContactos", "Cursor vacío, no se encontraron contactos")
            }
        } catch (e: Exception) {
            Log.e("ListaContactos", "Error al cargar contactos: ${e.message}")
        } finally {
            db.close()
        }
        Log.d("ListaContactos", "Contactos cargados: ${listaContactos.size}")
        return listaContactos
    }


}