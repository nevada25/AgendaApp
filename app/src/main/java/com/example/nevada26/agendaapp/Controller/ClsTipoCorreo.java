package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsTipoCorreo {
    SQLite cx;
    String query;
    public ClsTipoCorreo(Context context) {
        cx = new SQLite(context);
    }

    public void insertTipoCorreo(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM tipo_correo");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO tipo_correo(id_tipo_correo, descripcion) " +
                                        "VALUES ("+ jo.getInt("id_tipo_correo")+",'"+ jo.getString("descripcion")+"')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadTipoCorreo(){
        return cx.getWritableDatabase().rawQuery("SELECT id_tipo_correo as _id, descripcion FROM tipo_correo order by descripcion",null);
    }
}
