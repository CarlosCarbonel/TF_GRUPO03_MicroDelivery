package com.upc.tf_grupo03_microdelivery.dao

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.upc.tf_grupo03_microdelivery.entidades.TipoUsuario
import com.upc.tf_grupo03_microdelivery.util.SqliteDB


class TipoUsuarioDAO(context: Context) {

    private var sqliteDB: SqliteDB = SqliteDB(context)

    fun cargarTipoUsuario(): List<TipoUsuario?> {
        val listaTipoUsuario: ArrayList<TipoUsuario> = ArrayList()
        val db = sqliteDB.readableDatabase

        try {
            val c: Cursor = db.rawQuery("SELECT * FROM tipousuario", null)
            while (c.moveToNext()) {
                val id:Int = c.getInt(c.getColumnIndexOrThrow("tipous_id"))
                val tipo:String = c.getString(c.getColumnIndexOrThrow("tipous_tipo"))
                val tipoUsuario = TipoUsuario()
                tipoUsuario.id = id
                tipoUsuario.tipo = tipo
                listaTipoUsuario.add(tipoUsuario)
            }
        } catch (e: Exception) {
            Log.d("==>", e.message!!)
        }finally {
            db.close()
        }
        return listaTipoUsuario
    }



}