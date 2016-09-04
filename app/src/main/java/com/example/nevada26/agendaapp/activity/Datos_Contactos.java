package com.example.nevada26.agendaapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nevada26.agendaapp.Controller.ClsPersonaCorreoTel;
import com.example.nevada26.agendaapp.Modelo.PersonaCorreoTel;
import com.example.nevada26.agendaapp.R;

public class Datos_Contactos extends AppCompatActivity {
    Toolbar toolbar;
    Intent i;
    int theme;
    long user_id = 4;
    ListView datosPersona;
    String putextra;
    ClsPersonaCorreoTel cct;
    SharedPreferences sharedPreferences;
    PersonaCorreoTel pc;
    ImageView boton;
    TextView texto2;
    Cursor cursordcp;
    //List<PersonaCorreoTel> data = new ArrayList<>();
    int[] id_views = new int[]{R.id.TvNroDato,R.id.TvSubtituloDatos};
    String[] columnas = new String[]{"_id","operadora"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme();
        setContentView(R.layout.activity_datos__contactos);
final Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

            collapsingToolbarLayout.setTitle("CONTACTO");

        datosPersona=(ListView)findViewById(R.id.LvDatosContacto);
        texto2=(TextView)findViewById(R.id.TvNomDas);

        cct = new ClsPersonaCorreoTel(getApplicationContext());
        pc = new PersonaCorreoTel();
        Bundle datos = this.getIntent().getExtras();
        final long userid = datos.getLong("id_user");

        cursordcp=cct.ReadDatos(userid);
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getApplicationContext(),R.layout.estilos_datos, cursordcp, columnas, id_views);
        datosPersona.setAdapter(adaptador);

        if(cursordcp.moveToFirst()) {
            texto2.setText(cursordcp.getString(1).toString());
        }
        datosPersona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,final long _id) {
                final String[] items = {"LLAMAR", "ENVIAR MENSAJE"};

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Datos_Contactos.this);
                builder.setTitle("Selección")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        llamar(_id+"");
                                        break;
                                    case 1:

                                        enviaSMS2(_id+"");
                                        break;

                                }
                            }
                        });

                builder.create().show();




            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            Datos_Contactos.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void llamar(String tel){
        try{
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+tel)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviaSMS2(String tel){
        Uri uri = Uri.parse("smsto:"+tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(it);
    }
    public void theme() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("THEME", 0);
        settingTheme(theme);
    }
    public void settingTheme(int theme) {
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
            case 3:
                setTheme(R.style.AppTheme3);
                break;
            case 4:
                setTheme(R.style.AppTheme4);
                break;
            case 5:
                setTheme(R.style.AppTheme5);
                break;
            case 6:
                setTheme(R.style.AppTheme6);
                break;
            case 7:
                setTheme(R.style.AppTheme7);
                break;
            case 8:
                setTheme(R.style.AppTheme8);
                break;
            case 9:
                setTheme(R.style.AppTheme9);
                break;
            case 10:
                setTheme(R.style.AppTheme10);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }
    }
}


