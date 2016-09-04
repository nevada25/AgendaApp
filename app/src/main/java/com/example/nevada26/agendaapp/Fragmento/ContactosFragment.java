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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nevada26.agendaapp.Controller.ClsPersona;
import com.example.nevada26.agendaapp.Modelo.Persona;
import com.example.nevada26.agendaapp.R;
import com.example.nevada26.agendaapp.activity.Datos_Contactos;
import com.example.nevada26.agendaapp.activity.MainActivity;


public class ContactosFragment extends Fragment {

    private ClsPersona cp; private Persona p;
    private Cursor cursorPerso;
    private String[] columnas = new String[]{"nombres","nro"};
    private int[] id_views = new int[]{R.id.TvNombre,R.id.TvTelefono};
    private Button btBuscarPersona; EditText TextSearchPeople; private ListView persona; private TextWatcher text = null;
    private String nombreper; private String opcion;
    private AutoCompleteTextView mEmailView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cp=new ClsPersona(getActivity());
        p=new Persona();
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_contactos, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("CONTACTOS");

        persona=(ListView)view.findViewById(R.id.Lvper);
        mEmailView = (AutoCompleteTextView)view.findViewById(R.id.email);


        cursorPerso=cp.ReadPersonas();
        SimpleCursorAdapter adaptador = new android.support.v4.widget.SimpleCursorAdapter(getActivity(),R.layout.adapter_listar_contactos, cursorPerso, columnas, id_views);
        persona.setAdapter(adaptador);
        persona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, final long _id) {
                //Intent persona = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                Intent w = new Intent(getActivity(), Datos_Contactos.class);
                w.putExtra("id_user", _id);
                startActivity(w);
            }
        });

        //Busqueda Sensitiva con el TextWatcher
        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                opcion = "1";
                listaPersona(opcion);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mEmailView.addTextChangedListener(text);  //Aqui termina el método de la busqueda sensitiva



        return view;
    }
    public void listaPersona(String opcion){
        nombreper = mEmailView.getText().toString();
        if (!nombreper.equals("")) {
            cursorPerso=cp.SearchPersonas(nombreper);
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getActivity(),R.layout.adapter_listar_contactos, cursorPerso, columnas, id_views);
            persona.setAdapter(adaptador);
        }else if(nombreper.equals("")){
            cursorPerso=cp.ReadPersonas();
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getActivity(),R.layout.adapter_listar_contactos, cursorPerso, columnas, id_views);
            persona.setAdapter(adaptador);
            if (!opcion.equals("1")){
                Toast.makeText(getActivity(), "POR FAVOR INGRESE EL NOMBRE POR EL CUAL DESEA BUSCAR", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
