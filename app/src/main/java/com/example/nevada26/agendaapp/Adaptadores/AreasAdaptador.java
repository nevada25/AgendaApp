package com.example.nevada26.agendaapp.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nevada26.agendaapp.R;

import java.util.List;

/**
 * Creado por Hermosa Programación
 */
public class AreasAdaptador extends RecyclerView.Adapter<AreasAdaptador.AreasViewHolder> {
    private List<Areas> items;

    public static class AreasViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public AreasViewHolder(View v) {
            super(v);
       //     imagen = (ImageView) v.findViewById(R.id.imagen);
         //   nombre = (TextView) v.findViewById(R.id.nombre);

        }
    }

    public AreasAdaptador(List<Areas> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AreasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_areas, viewGroup, false);
        return new AreasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AreasViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());

    }
}
