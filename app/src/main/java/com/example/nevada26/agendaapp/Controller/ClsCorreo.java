package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsCorreo {
    SQLite cx;
    String query;
    public ClsCorreo(Context context) {
        cx = new SQLite(context);
    }

    public void insertCorreo(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM correo");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO correo(id_correo, id_tipo_correo, correo, estado) " +
                        "VALUES ("+ jo.getInt("id_correo")+","+ jo.getInt("id_tipo_correo")+",'"+ jo.getString("correo")+"','"+ jo.getString("estado")+"')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadCorreo(){
        return cx.getWritableDatabase().rawQuery("SELECT id_correo as _id, id_tipo_correo, correo, estado FROM correo order by correo",null);
    }
}
