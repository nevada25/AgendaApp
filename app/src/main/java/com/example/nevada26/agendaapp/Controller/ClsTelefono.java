package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsTelefono {
    SQLite cx;
    String query;
    public ClsTelefono(Context context) {
        cx = new SQLite(context);
    }

    public void insert_Telefono(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM telefono");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO telefono( id_telefono,id_operador, nro_telefono, descripcion, estado) " +
                " VALUES ("+ jo.getInt("id_telefono")+","+ jo.getInt("id_operador")+" , '"+ jo.getString("nro_telefono")+"' , '"+ jo.getString("descripcion")+"','"+ jo.getString("estado")+"')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadTelefono(){
        return cx.getWritableDatabase().rawQuery("SELECT id_telefono as _id, id_operador, nro_telefono, descripcion, estado FROM telefono ",null);
    }
}
