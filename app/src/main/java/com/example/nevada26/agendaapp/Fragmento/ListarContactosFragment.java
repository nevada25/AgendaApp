package com.example.nevada26.agendaapp.Fragmento;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
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

import com.example.nevada26.agendaapp.Controller.ClsPersonaCorreoTel;
import com.example.nevada26.agendaapp.Modelo.Persona;
import com.example.nevada26.agendaapp.Modelo.PersonaCorreoTel;
import com.example.nevada26.agendaapp.R;
import com.example.nevada26.agendaapp.activity.Datos_Contactos;
import com.example.nevada26.agendaapp.activity.MainActivity;


public class ListarContactosFragment extends Fragment {
    ClsPersonaCorreoTel cct; PersonaCorreoTel pc;  Persona p;
    private String putextra; private String nombrearea; private String opcion;
    private Cursor cursorpa;
    private String[] columnas = new String[]{"nombres","nro"};
    private int[] id_views = new int[]{R.id.TvNombre,R.id.TvTelefono};
    private TextView nulo; private ImageView buscarpersona; private EditText TextoPersonaSearch;
    private TextWatcher textper;
    private ListView listarpersona;
    private AutoCompleteTextView DatosArea;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cct=new ClsPersonaCorreoTel(getActivity());
        pc=new PersonaCorreoTel();
        p=new Persona();
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_contactos, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("CONTACTOS");



        DatosArea = (AutoCompleteTextView)view.findViewById(R.id.email);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            putextra = bundle.getString("idAra");
        }
        listarpersona =(ListView)view.findViewById(R.id.Lvper);
        //     buscarpersona=(ImageView)v.findViewById(R.id.BtBuscarper);
        nulo=(TextView)view.findViewById(R.id.Tvnulos);
        //   TextoPersonaSearch=(EditText)v.findViewById(R.id.EtBuscarper);

        if(!putextra.equals("")) {
//            nulo.setVisibility(View.GONE);
            cursorpa = cct.ReadPersonaArea(putextra);
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getActivity(), R.layout.adapter_listar_contactos, cursorpa, columnas, id_views);
            listarpersona.setAdapter(adaptador);
        }else if(putextra.length()<0){
            nulo.setText("NO HAY DATOS");
            nulo.setVisibility(View.VISIBLE);
            listarpersona.setVisibility(View.GONE);
        }

        textper = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                opcion = "1";
                listaPorArea(opcion);
            }

            @Override
            public void afterTextChanged(Editable s) {            }
        };
        DatosArea.addTextChangedListener(textper);

        listarpersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,final long _id) {
                //Intent area = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                Intent w= new Intent(getActivity(), Datos_Contactos.class);
                w.putExtra("id_user", _id);
                startActivity(w);

            }
        });
        /*buscarpersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcion = "2";
                listaPorArea(opcion);
            }
        });*/
        return view;
    }
    public void listaPorArea(String opcion){
        nombrearea = DatosArea.getText().toString();

        if (!nombrearea.equals("")) {
            cursorpa=cct.SearchPersonaArea(nombrearea);
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getActivity(),R.layout.adapter_listar_contactos, cursorpa, columnas, id_views);
            listarpersona.setAdapter(adaptador);
        }else if(nombrearea.equals("")){
            cursorpa=cct.ReadPersonaArea(putextra);
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getActivity(),R.layout.adapter_listar_contactos, cursorpa, columnas, id_views);
            listarpersona.setAdapter(adaptador);
            if (!opcion.equals("1")){
                Toast.makeText(getActivity(), "POR FAVOR INGRESE EL NOMBRE POR EL CUAL DESEA BUSCAR", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
