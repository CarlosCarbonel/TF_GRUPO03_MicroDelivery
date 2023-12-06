package com.upc.tf_grupo03_microdelivery.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDB(context: Context):SQLiteOpenHelper(context,NOMBRE_BD, null, VERSION){
    companion object{
        private const val NOMBRE_BD = "Microdelivery.db"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?){

        var query = "CREATE TABLE tipousuario (" +
                "tipous_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipous_tipo NOT NULL);"
        db?.execSQL(query)
        query = "INSERT INTO tipousuario VALUES (NULL, 'VENDEDOR');"
        db?.execSQL(query)
        query = "INSERT INTO tipousuario VALUES (NULL, 'REPARTIDOR');"
        db?.execSQL(query)
        query = "INSERT INTO tipousuario VALUES (NULL, 'RECEPTOR');"
        db?.execSQL(query)

        query = "CREATE TABLE usuarios (" +
                "us_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "us_dni NOT NULL," +
                "us_nombres NOT NULL," +
                "us_apellidos NOT NULL," +
                "us_usuario NOT NULL," +
                "us_contrasena NOT NULL," +
                "us_correo NOT NULL," +
                "us_distrito NOT NULL," +
                "us_direccion NOT NULL," +
                "tipous_id INTEGER NOT NULL," +
                "foreign key(tipous_id)references tipousuario(tipous_id));"
        db?.execSQL(query)

        query = "CREATE TABLE rubrotienda(" +
                "rubrotd_id INTEGER PRIMARY KEY AUTOINCREMENT," + "rubrotd_tipo NOT NULL);"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES (NULL, 'JUGUETERIA');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES (NULL,'FERRETERIA');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES(NULL,'ROPA');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES(NULL,'BISUTERIA');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES(NULL,'ABARROTES');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES(NULL,'VIDEO JUEGOS');"
        db?.execSQL(query)
        query = "INSERT INTO rubrotienda VALUES(NULL,'OTROS');"
        db?.execSQL(query)

        query = "CREATE TABLE tiendas (" +
                "td_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "td_nombre NOT NULL," +
                "td_celular NOT NULL," +
                "td_direccion NOT NULL," +
                "td_distrito NOT NULL," +
                "rubrotd_id INTEGER NOT NULL," + "foreign key(rubrotd_id) references rubrotienda(rubrotd_id));"
        db?.execSQL(query)

        query = "CREATE TABLE Contactos (" +
                "contactoId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "contactoDni NOT NULL," +
                "usuarioId INTEGER NOT NULL," +
                "contactoNombres NOT NULL," +
                "contactoApellidos NOT NULL," +
                "contactoCorreo NOT NULL," +
                "contactoDistrito NOT NULL," +
                "contactoDireccion NOT NULL," +
                "contactoTipous NOT NULL," +
                "foreign key (usuarioId) references Usuarios(us_id)," +
                "foreign key (contactoTipous) references tipousuario(tipous_id));"
        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int){
        db?.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

}