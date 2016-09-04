package com.example.nevada26.agendaapp.Fragmento;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nevada26.agendaapp.Adaptadores.Areas;
import com.example.nevada26.agendaapp.Adaptadores.AreasAdaptador;
import com.example.nevada26.agendaapp.Controller.ClsArea;
import com.example.nevada26.agendaapp.Modelo.Area;
import com.example.nevada26.agendaapp.activity.MainActivity;
import com.example.nevada26.agendaapp.R;

import java.util.ArrayList;
import java.util.List;


public class StarredFragment extends Fragment {
    ListView area;
    ClsArea ca;
    Area a;
    ImageView buscararea;
    private String nombrearea;
    EditText TextoBuscararea;
    Cursor cursora = null;
    String id;
    private String opcion;
    private TextWatcher textper;
    private AutoCompleteTextView DatosAreas;
    String[] columnas = new String[]{"nombre_area"};
    int[] id_views = new int[]{R.id.nombre};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_starred, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("AREAS");
        ca = new ClsArea(getActivity());
        a = new Area();
       DatosAreas = (AutoCompleteTextView) view.findViewById(R.id.datosareas);
        area = (ListView) view.findViewById(R.id.LvArea);
        cursora = ca.ReadArea();
        SimpleCursorAdapter adaptador = new android.support.v4.widget.SimpleCursorAdapter(getActivity(), R.layout.adapter_areas, cursora, columnas, id_views);
        area.setAdapter(adaptador);
        area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, final long _id) {

                Intent area = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                area.putExtra("idAra", _id+"");
                startActivity(area);
                getActivity().finish();
            }
        });
        textper = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                opcion = "1";
                listaPorArea(opcion);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        DatosAreas.addTextChangedListener(textper);

        return view;
    }
    public void listaPorArea(String opcion) {
        nombrearea = DatosAreas.getText().toString();
        if (!nombrearea.equals("")) {
            cursora=ca.SearchArea(nombrearea);
            SimpleCursorAdapter adaptador = new android.support.v4.widget.SimpleCursorAdapter(getActivity(),R.layout.adapter_areas, cursora, columnas, id_views);
            area.setAdapter(adaptador);
        }else if(nombrearea.equals("")){
            cursora=ca.ReadArea();
            SimpleCursorAdapter adaptador = new android.support.v4.widget.SimpleCursorAdapter(getActivity(),R.layout.adapter_areas, cursora, columnas, id_views);
            area.setAdapter(adaptador);
            if (!opcion.equals("1")){
                Toast.makeText(getActivity(), "POR FAVOR INGRESE EL NOMBRE POR EL CUAL DESEA BUSCAR", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
