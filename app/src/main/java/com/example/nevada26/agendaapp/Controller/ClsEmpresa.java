package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsEmpresa {
    SQLite cx;
    String query;
    public ClsEmpresa(Context context) {
        cx = new SQLite(context);
    }

    public void insertEmpresa(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM empresa");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO empresa(id_empresa, nombre_empresa, id_tipo_empresa, estado, id_empresa_padre) " +
                        "VALUES ("+ jo.getInt("id_empresa")+",'"+ jo.getString("nombre_empresa")+"',"+ jo.getInt("id_tipo_empresa")+",'"+ jo.getString("estado")+"',0)");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadEmpresa(){
        return cx.getWritableDatabase().rawQuery("SELECT id_empresa as _id, nombre_empresa, id_tipo_empresa, estado, id_empresa_padre FROM empresa  order by nombre_empresa",null);
    }
}
