package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsTipoEmpresa {
    SQLite cx;
    String query;
    public ClsTipoEmpresa(Context context) {
        cx = new SQLite(context);
    }

    public void insertTipoEmpresa(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM tipo_empresa");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO tipo_empresa(id_tipo_empresa, nombre_te, descripcion) " +
                        "VALUES ("+ jo.getInt("id_tipo_empresa")+",'"+ jo.getString("nombre_te")+"','"+ jo.getString("descripcion")+"')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadTipoEmpresa(){
        return cx.getWritableDatabase().rawQuery("SELECT id_tipo_empresa as _id, nombre_te, descripcion FROM tipo_empresa  order by nombre_te",null);
    }
}
