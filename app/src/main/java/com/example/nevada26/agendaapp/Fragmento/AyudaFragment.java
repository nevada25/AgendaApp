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
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.nevada26.agendaapp.R;
import com.example.nevada26.agendaapp.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;


public class AyudaFragment extends Fragment {
    private Thread mUiThread;
    final Handler mHandler = new Handler();
    private ImageSwitcher imageSwitcher1;
    private int[] gallery = {R.drawable.img12, R.drawable.img22, R.drawable.img32,R.drawable.img42,R.drawable.img48,R.drawable.img492,R.drawable.img562};
    private int position;
    private static final Integer DURATION = 4000;
    private Timer timer = null;
    TextView dato,datos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ayuda, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("INFORMACION");



        return view;
    }


}

