package com.example.nevada26.agendaapp.Fragmento;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.nevada26.agendaapp.activity.MainActivity;
import com.example.nevada26.agendaapp.R;
import com.example.nevada26.agendaapp.helper.SQLiteHandler;
import com.example.nevada26.agendaapp.helper.SessionManager;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class InicioFragment extends Fragment {
    private Thread mUiThread;
    final Handler mHandler = new Handler();
    private ImageSwitcher imageSwitcher1;
    private int[] gallery = {R.drawable.img12, R.drawable.img22, R.drawable.img32,R.drawable.img42,R.drawable.img48,R.drawable.img492,R.drawable.img562};
    private int position;
    private static final Integer DURATION = 4000;
    private Timer timer = null;
    TextView dato,datos;
    private SQLiteHandler db;
    private SessionManager session;
    private TextView txtName;
    private TextView txtEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("INICIO");
        txtName = (TextView) view.findViewById(R.id.NombreLogin);

        db = new SQLiteHandler(getActivity());

        // session manager
        session = new SessionManager(getActivity());
        HashMap<String, String> user = db.getUserDetails();


        String name = user.get("nombres");

        dato=(TextView)view.findViewById(R.id.TvTitulo);
        datos=(TextView)view.findViewById(R.id.Subtitulo);
        txtName.setText(name);

        String font_path= "fonts/28dayslater.ttf";
        // String font_path= "fonts/cool.ttf";
        //String font_path2= "fonts/artbrush.ttf";

        Typeface Tf=Typeface.createFromAsset(getActivity().getAssets(),font_path);
        dato.setTypeface(Tf);
        datos.setTypeface(Tf);
        //Esto es el Slider
        imageSwitcher1 = (ImageSwitcher) view.findViewById(R.id.imageSwitcher);
        imageSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                View v = new ImageView(getActivity());
                return v;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
        imageSwitcher1.setInAnimation(fadeIn);
        imageSwitcher1.setOutAnimation(fadeOut);
        startSlider();

        return view;
    }
    private void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageSwitcher1.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }
        }, 0, DURATION);
    }
    public final void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != mUiThread) {
            mHandler.post(action);
        } else {
            action.run();
        }

    }
}

