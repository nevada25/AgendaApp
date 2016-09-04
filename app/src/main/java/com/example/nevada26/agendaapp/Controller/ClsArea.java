package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ClsArea {
    SQLite cx;
    String query;
    public ClsArea(Context context) {
        cx = new SQLite(context);
    }

    public void insert_Area(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM area");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO area(id_area, nombre_area, estado, id_empresa, id_area_padre) " +
                        "VALUES ("+ jo.getInt("id_area")+",'"+ jo.getString("nombre_area")+"','"+ jo.getString("estado")+"',"+ jo.getInt("id_empresa")+",0)");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadArea(){
        return cx.getWritableDatabase().rawQuery("SELECT id_area as _id, nombre_area, estado, id_empresa, id_area_padre FROM area GROUP by nombre_area order by nombre_area",null);
    }
    public Cursor SearchArea(String nombre){
        return cx.getWritableDatabase().rawQuery("SELECT id_area as _id, nombre_area, estado, id_empresa, id_area_padre FROM area WHERE nombre_area LIKE '"+nombre+"%' order by nombre_area",null);
    }

}
