package com.example.nevada26.agendaapp.Controller;

import android.content.Context;
import android.database.Cursor;

import com.example.nevada26.agendaapp.Config.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClsPersonaCorreoTel {
    SQLite cx;
    String query;
    public ClsPersonaCorreoTel(Context context) {
        cx = new SQLite(context);
    }

    public void insert_PerCorreo(JSONArray jsonArray) {
        try {
            cx.getWritableDatabase().execSQL("DELETE FROM persona_correo");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                cx.getWritableDatabase().execSQL("INSERT INTO persona_correo(idpercorreo,id_area, id_correo, id_telefono, id_persona) " +
                        "VALUES ("+ jo.getInt("idpercorreo")+","+ jo.getInt("id_area")+",0,"+ jo.getInt("id_telefono")+","+ jo.getInt("id_persona")+")");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Cursor ReadPerCorreo(){
        return cx.getWritableDatabase().rawQuery("SELECT idpercorreo as _id, id_area, id_correo, id_telefono, id_persona FROM persona_correo ",null);
    }

    public Cursor ReadPersonaArea(String id){
        return cx.getWritableDatabase().rawQuery("SELECT  pc.idpercorreo ,pc.id_area,a.nombre_area ,pc. id_persona as _id,pc.id_telefono as id_telefono, t.nro_telefono as nro,p.nombres||'  '||p.apepat||'  '||p.apemat as nombres " +
                "FROM persona_correo pc,area a, persona p,telefono t WHERE a.id_area=pc.id_area  AND p.id_persona=pc.id_persona and t.id_telefono=pc.id_telefono and pc.id_area="+id,null);
    }
    public Cursor SearchPersonaArea(String persona){
        return cx.getWritableDatabase().rawQuery("SELECT  pc.idpercorreo ,pc.id_area,a.nombre_area ,pc. id_persona as _id,pc.id_telefono as id_telefono, t.nro_telefono as nro,p.nombres||'  '||p.apepat||'  '||p.apemat as nombres, p.apepat as apepat,p.apemat as apemat " +
                "FROM persona_correo pc,area a, persona p,telefono t WHERE a.id_area=pc.id_area  AND p.id_persona=pc.id_persona and t.id_telefono=pc.id_telefono and nombres LIKE '"+persona+"%'  ",null);
    }

    public Cursor ReadDatos(long id){
        return cx.getWritableDatabase().rawQuery("SELECT pc.idpercorreo,p.nombres as nombre, t.nro_telefono as _id,o.operadora_nombre as operadora " +
                "FROM persona_correo pc,persona p,telefono t,operador o " +
                "WHERE o.id_operador=t.id_operador and t.id_telefono=pc.id_telefono  and p.id_persona=pc.id_persona  and pc.id_persona="+id+" order by p.nombres",null);
    }

}
