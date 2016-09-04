package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsOperador {
    SQLite cx;
    String query;
    public ClsOperador(Context context) {
        cx = new SQLite(context);
    }

    public void insert_Operadora(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM operador");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO operador(id_operador, operadora_nombre, estado) " +
                        "VALUES ("+ jo.getInt("id_operador")+",'"+ jo.getString("operadora_nombre")+"','"+ jo.getString("estado")+"')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadOperador(){
        return cx.getWritableDatabase().rawQuery("SELECT id_operador as _id, operadora_nombre, estado FROM operador ",null);
    }
}
