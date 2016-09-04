package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsPersona {
    SQLite cx;
    public ClsPersona(Context context) {
        cx = new SQLite(context);
    }

    public void insert_Persona(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM persona");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO persona(id_persona,nombres, apepat, apemat, genero, cargo, codigo, foto, estado) " +
                        "VALUES ("+ jo.getInt("id_persona")+",'"+ jo.getString("nombres")+"','"+ jo.getString("apepat")+"','"+ jo.getString("apemat")+"','"+ jo.getString("genero")+"','"+ jo.getString("cargo")+"','NULL','NULL','1')");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadPersona(){
        return cx.getWritableDatabase().rawQuery("SELECT id_persona as _id, nombres, apepat, apemat, genero, cargo, codigo, estado FROM persona ",null);
    }
    public Cursor ReadPersonas(){
        return cx.getWritableDatabase().rawQuery("SELECT  pc.idpercorreo as idpercorreo ,pc. id_persona as _id,pc.id_telefono as id_telefono, t.nro_telefono as nro,UPPER(p.nombres||'  '||p.apepat||'  '||p.apemat) as nombres " +
                "FROM persona_correo pc, persona p,telefono t WHERE  p.id_persona=pc.id_persona and t.id_telefono=pc.id_telefono ",null);
    }
    public Cursor SearchPersonas(String nombres){
        return cx.getWritableDatabase().rawQuery("SELECT  pc.idpercorreo as _id ,pc. id_persona as idp_persona,pc.id_telefono as id_telefono, t.nro_telefono as nro,UPPER(p.nombres||'  '||p.apepat||'  '||p.apemat) as nombres " +
                "FROM persona_correo pc, persona p,telefono t WHERE  p.id_persona=pc.id_persona and t.id_telefono=pc.id_telefono and nombres LIKE '"+nombres+"%' ",null);
    }
}
