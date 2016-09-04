package com.example.nevada26.agendaapp.activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nevada26.agendaapp.Controller.ClsArea;
import com.example.nevada26.agendaapp.Controller.ClsCorreo;
import com.example.nevada26.agendaapp.Controller.ClsEmpresa;
import com.example.nevada26.agendaapp.Controller.ClsOperador;
import com.example.nevada26.agendaapp.Controller.ClsPersona;
import com.example.nevada26.agendaapp.Controller.ClsPersonaCorreoTel;
import com.example.nevada26.agendaapp.Controller.ClsTelefono;
import com.example.nevada26.agendaapp.Controller.ClsTipoCorreo;
import com.example.nevada26.agendaapp.Controller.ClsTipoEmpresa;
import com.example.nevada26.agendaapp.Fragmento.AyudaFragment;
import com.example.nevada26.agendaapp.Fragmento.ContactosFragment;
import com.example.nevada26.agendaapp.Fragmento.InicioFragment;
import com.example.nevada26.agendaapp.Fragmento.ListarContactosFragment;
import com.example.nevada26.agendaapp.Fragmento.StarredFragment;
import com.example.nevada26.agendaapp.Modelo.Area;
import com.example.nevada26.agendaapp.Modelo.Correo;
import com.example.nevada26.agendaapp.Modelo.Empresa;
import com.example.nevada26.agendaapp.Modelo.Operador;
import com.example.nevada26.agendaapp.Modelo.Persona;
import com.example.nevada26.agendaapp.Modelo.PersonaCorreoTel;
import com.example.nevada26.agendaapp.Modelo.Telefono;
import com.example.nevada26.agendaapp.Modelo.TipoCorreo;
import com.example.nevada26.agendaapp.R;
import com.example.nevada26.agendaapp.RestClient.VolleyS;
import com.example.nevada26.agendaapp.helper.SQLiteHandler;
import com.example.nevada26.agendaapp.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
//INICIO LOGUEO

    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
// FIN LOGUEO


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    int theme, scrollPositionX = 0, scrollPositionY = -100;
    Intent intent;
    FrameLayout statusBar;
    SharedPreferences.Editor editor;
    Fragment fragmentoGenerico = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    //WEB SERVICES
    Area a;Empresa e;Persona p;Telefono t;Correo Co;Empresa Em;Operador o;TipoCorreo Tc;
    static private VolleyS volley;
    static protected RequestQueue fRequestQueue;
    ClsArea ca;ClsCorreo Cco;ClsEmpresa Ce;ClsTipoCorreo ctc;ClsTipoEmpresa cte;ClsOperador co;ClsTelefono ct;ClsPersona cp;ClsPersonaCorreoTel cpc;PersonaCorreoTel pc;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme();
        setContentView(R.layout.activity_main);
        // INICIO LOGUEO


        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = db.getUserDetails();





        // FIN LOGUEO





        String putextra;
//INICIO WEB SERVICES
        volley = VolleyS.getInstance(getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
        co=new ClsOperador(MainActivity.this); o=new Operador(); Ce=new ClsEmpresa(MainActivity.this); Em=new Empresa();
        ctc=new ClsTipoCorreo(MainActivity.this); Tc=new TipoCorreo(); Cco=new ClsCorreo(MainActivity.this); Co=new Correo();
        t=new Telefono(); ct=new ClsTelefono(MainActivity.this); a=new Area(); cte=new ClsTipoEmpresa(MainActivity.this);
        e=new Empresa(); ca=new ClsArea(MainActivity.this); p=new Persona(); cp=new ClsPersona(MainActivity.this);
        cpc=new ClsPersonaCorreoTel(MainActivity.this); pc=new PersonaCorreoTel();
//FIN WEB SERVICES
        toolbarStatusBar();
        agregarToolbar();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            putextra = bundle.getString("idAra");
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (navigationView != null && bundle==null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, new InicioFragment())
                    .commit();
        }else{
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, new ListarContactosFragment())
                    .commit();
            }
            //prepararDrawer(navigationView);
            // Seleccionar item por defecto
            //seleccionarItem(navigationView.getMenu().getItem(0));
        setupNavigationDrawerContent(navigationView);
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }
    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_inbox:
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.contenedor_principal, new InicioFragment())
                                        .commit();
                                break;
                            case R.id.item_navigation_drawer_starred:
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.contenedor_principal, new StarredFragment())
                                        .commit();
                                break;
                            case R.id.item_navigation_drawer_sent_mail:
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.contenedor_principal, new ContactosFragment())
                                        .commit();
                                break;
                            case R.id.ayu:
                                fragmentManager
                                        .beginTransaction()
                                        .replace(R.id.contenedor_principal, new AyudaFragment())
                                        .commit();
                                break;
                            case R.id.Setting:
                                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.Exit:
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("¿ESTA SEGURO QUE DESEA SALIR?")
                                        .setTitle("Confirmacion")
                                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                MainActivity.this.finish();
                                            }
                                        })
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                builder.create().show();
                                break;

                        }

                        menuItem.setChecked(true);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }
    /*
    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {


        switch (itemDrawer.getItemId()) {
            case R.id.item_navigation_drawer_inbox:
                fragmentoGenerico = new InicioFragment();
                break;
            case R.id.item_navigation_drawer_starred:
                fragmentoGenerico = new StarredFragment();
                break;
            case R.id.item_navigation_drawer_sent_mail:
                fragmentoGenerico = new ContactosFragment();
                break;
            case R.id.item_navigation_drawer_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:

                if(estaConectado()) {
                    pDialog = new ProgressDialog(MainActivity.this);
                    pDialog = ProgressDialog.show(MainActivity.this, null, "CARGANDO DATOS..", true);

                    WaitTime wait = new WaitTime();
                    wait.execute();
                    cargararea();
                    cargarpersona();
                    cargartelefono();
                    cargarOperadora();
                    cargarPerCorreo();
                    CargarCorreo();
                    CargarEmpresa();
                    CargarTipoCorreo();
                    CargarTipoEmpresa();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class WaitTime extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }
        protected void onPostExecute() {
            pDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            pDialog.dismiss();
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... params) {
            long delayInMillis = 2000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pDialog.dismiss();
                }
            }, delayInMillis);
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("DOWNLOAD", false);
        editor.apply();

        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void theme() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("THEME", 0);
        settingTheme(theme);
    }

    public void toolbarStatusBar() {

        // Cast toolbar and status bar
        statusBar = (FrameLayout) findViewById(R.id.statusBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Get support to the toolbar and change its title
        setSupportActionBar(toolbar);
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
    //INICIO DE CONTROLADORES DE LA WEB SERVICES
    public  void cargarpersona(){
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerPeople.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("persona");
                    cp.insert_Persona(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void cargarOperadora(){
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerOperador.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("operador");
                    co.insert_Operadora(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void cargararea(){
        String url = "http://agendateleupeu.esy.es/WebServices/obtenerArea.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("area");
                    ca.insert_Area(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void cargartelefono(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerTelefono.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("telefono");
                    ct.insert_Telefono(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void CargarCorreo(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerCorreo.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("correo");
                    Cco.insertCorreo(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void CargarEmpresa(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerEmpresa.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("empresa");
                    Ce.insertEmpresa(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void CargarTipoCorreo(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerTipoCorreo.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("tipo_correo");
                    ctc.insertTipoCorreo(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void CargarTipoEmpresa(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerTipoEmpresa.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("tipo_empresa");
                    cte.insertTipoEmpresa(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public  void cargarPerCorreo(){
        //http://upeuadmision.esy.es/modulos/rest/servicios.php
        String url = "http://agendateleupeu.esy.es/WebServices/ObtenerPersonaCorreo.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Area> MyList = new ArrayList<>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("persona_correo");
                    cpc.insert_PerCorreo(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onConnectionFailed(volleyError.toString());
            }
        });
        addToQueue(request);

    }
    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            onPreStartConnection();
            fRequestQueue.add(request);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (fRequestQueue != null) {
            fRequestQueue.cancelAll(this);
        }
    }
    public void  onPreStartConnection() {
        setProgressBarIndeterminateVisibility(true);
    }

    public void onConnectionFailed(String error) {
        setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    public void onConnectionFinished() {
        setProgressBarIndeterminateVisibility(false);
    }
    protected Boolean estaConectado(){
        if(conectadoWifi()){

            return true;
        }else{
            if(conectadoRedMovil()){

                return true;
            }else{
                showAlertDialog(MainActivity.this, "Conexion a Internet",
                        "Tu Dispositivo no tiene Conexion a Internet.", false);
                return false;
            }
        }
    }
    protected Boolean conectadoWifi(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Boolean conectadoRedMovil(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);

        alertDialog.setMessage(message);

        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }

    //FIN DE CONTROLADORES DE LA WEB SERVICES
}