package com.example.nevada26.agendaapp.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    private static final String TAG = SQLite.class.getSimpleName();
    private static String NAME_DATABASE = "AgendaTelefonica";
    private static int VERSION=4;
    //private static SQLiteDatabase.CursorFactory factory=null;
    //AGREGANDO NUEVO DATOS
    // Login table name

/*
   private String CREATE_LOGIN_TABLE = "CREATE TABLE user ("
            + "id INTEGER PRIMARY KEY,"
            + "name TEXT,"
            + "email TEXT UNIQUE, "
            + "uid TEXT, "
            + "created_at TEXT )";

*/
    private String TB_OPERADOR ="CREATE TABLE operador " +
            "(id_operador INTEGER PRIMARY KEY, " +
            " operadora_nombre TEXT , " +
            " estado TEXT )";

    private String TB_AREA ="CREATE TABLE area " +
            "(id_area INTEGER PRIMARY KEY ," +
            " nombre_area TEXT ," +
            " estado TEXT, "+
            " id_empresa INTEGER, "+
            " id_area_padre INTEGER , "+
            " FOREIGN KEY (id_area_padre) REFERENCES area (id_area), " +
            " FOREIGN KEY (id_empresa) REFERENCES empresa (id_empresa)  )";

    private String TB_PERSONA ="CREATE TABLE persona " +
            "(id_persona INTEGER PRIMARY KEY , " +
            " nombres TEXT, " +
            " apepat TEXT, " +
            " apemat TEXT, " +
            " genero TEXT, "+
            " cargo TEXT, "+
            " codigo TEXT, "+
            " foto BLOB,"+ //BLOB para archivos (fotos, entre otros)
            " estado TEXT)";

    private String TB_TELEFONO ="CREATE TABLE telefono " +
            "(id_telefono INTEGER PRIMARY KEY , " +
            " id_operador INTEGER, " +
            " nro_telefono TEXT UNIQUE, " +
            " descripcion TEXT, "+
            " estado TEXT, "+
            " FOREIGN KEY(id_operador) REFERENCES operador(id_operador))";

    private String TB_TIPO_EMPRESA ="CREATE TABLE tipo_empresa " +
            "(id_tipo_empresa INTEGER PRIMARY KEY, " +
            " nombre_te TEXT, " +
            " descripcion TEXT)";

    private String TB_EMPRESA ="CREATE TABLE empresa " +
            "(id_empresa INTEGER PRIMARY KEY, " +
            " nombre_empresa TEXT, " +
            " id_tipo_empresa INTEGER, " +
            " estado TEXT, "+
            " id_empresa_padre INTEGER, "+
            // " PRIMARY KEY(id_empresa) REFERENCES persona(id_persona), " +
            " FOREIGN KEY(id_tipo_empresa) REFERENCES tipo_empresa(id_tipo_empresa), " +
            " FOREIGN KEY(id_empresa_padre) REFERENCES empresa(id_empresa))";
    //ALTER TABLE empresa ADD FOREIGN KEY (id_empresa) REFERENCES persona (id_persona)

    private String TB_TIPO_CORREO ="CREATE TABLE tipo_correo " +
            "(id_tipo_correo INTEGER PRIMARY KEY, " +
            " descripcion TEXT)";

    private String TB_CORREO ="CREATE TABLE correo " +
            "(id_correo INTEGER PRIMARY KEY, " +
            " id_tipo_correo INTEGER, " +
            " correo TEXT UNIQUE, " +
            " estado TEXT, "+
            " id_persona INTEGER, "+
            " FOREIGN KEY(id_tipo_correo) REFERENCES tipo_correo(id_tipo_correo), " +
            " FOREIGN KEY(id_persona) REFERENCES persona(id_persona))";

    private String TB_PERSONA_CORREO ="CREATE TABLE persona_correo " +
            "(idpercorreo INTEGER PRIMARY KEY , " +
            " id_correo INTEGER, " +
            " id_area INTEGER, " +
            " id_telefono INTEGER, "+
            " id_persona INTEGER, "+
            " FOREIGN KEY (id_correo) REFERENCES correo(id_correo)," +
            " FOREIGN KEY (id_area) REFERENCES area(id_area)," +
            " FOREIGN KEY (id_telefono) REFERENCES telefono(id_telefono)," +
            " FOREIGN KEY(id_persona) REFERENCES persona(id_persona))";




    public SQLite(Context c) {
        super(c, NAME_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(TB_AREA);
        db.execSQL(TB_PERSONA);
        db.execSQL(TB_OPERADOR);
        db.execSQL(TB_TELEFONO);
        db.execSQL(TB_TIPO_EMPRESA);
        db.execSQL(TB_EMPRESA);
        db.execSQL(TB_TIPO_CORREO);
        db.execSQL(TB_CORREO);
        db.execSQL(TB_PERSONA_CORREO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS area");
        db.execSQL("DROP TABLE IF EXISTS operador");
        db.execSQL("DROP TABLE IF EXISTS persona");
        db.execSQL("DROP TABLE IF EXISTS telefono");
        db.execSQL("DROP TABLE IF EXISTS tipo_empresa");
        db.execSQL("DROP TABLE IF EXISTS empresa");
        db.execSQL("DROP TABLE IF EXISTS tipo_correo");
        db.execSQL("DROP TABLE IF EXISTS correo");
        db.execSQL("DROP TABLE IF EXISTS persona_correo");
      //  db.execSQL("DROP TABLE IF EXISTS user" );

        this.onCreate(db);
    }
    /**
     * Storing user details in database
     * */
   /* public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name); // Name
        values.put("email", email); // Email
        values.put("uid", uid); // Email
        values.put("created_at", created_at); // Created At

        // Inserting Row
        long id = db.insert("user", null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }


     // Getting user data from database

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM user";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
      //  Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }


     // Re crate database Delete all tables and create them again

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("user", null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
*/

}
