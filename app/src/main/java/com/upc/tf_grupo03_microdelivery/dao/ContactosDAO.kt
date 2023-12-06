package com.upc.tf_grupo03_microdelivery.dao

import android.content.ContentValues
import android.content.Context
import com.upc.tf_grupo03_microdelivery.entidades.Contacto
import com.upc.tf_grupo03_microdelivery.util.SqliteDB

class ContactosDAO (context:Context) {
    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun agregarContacto(contacto: Contacto): Long {
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
    fun obtenerContactosPorUsuario(usuarioId: Int): List<Contacto> {
        val listaContactos = mutableListOf<Contacto>()
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
                val contacto = Contacto().apply {
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


}