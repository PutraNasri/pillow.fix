package es.esy.kinketkuena.pillowfix;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import es.esy.kinketkuena.pillowfix.list.list_bandaraya;
import es.esy.kinketkuena.pillowfix.list.list_jayabaru;
import es.esy.kinketkuena.pillowfix.list.list_kutaalam;
import es.esy.kinketkuena.pillowfix.list.list_kutaraja;
import es.esy.kinketkuena.pillowfix.list.list_luengbata;
import es.esy.kinketkuena.pillowfix.list.list_meuraxa;
import es.esy.kinketkuena.pillowfix.list.list_syahkuala;
import es.esy.kinketkuena.pillowfix.list.list_ulekareng;

public class home extends Fragment {

    public home() {
    }
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_home, container, false);
        Button syahkuala = (Button) rootview.findViewById(R.id.button1);
        Button kutaraja = (Button) rootview.findViewById(R.id.button2);
        Button ulekareng = (Button) rootview.findViewById(R.id.button3);
        Button luengbata = (Button) rootview.findViewById(R.id.button4);
        Button meuraxa = (Button) rootview.findViewById(R.id.button5);
        Button kutaalam = (Button) rootview.findViewById(R.id.button6);
        Button jayabaru = (Button) rootview.findViewById(R.id.button7);
        Button bandaraya = (Button) rootview.findViewById(R.id.button8);


        syahkuala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_syahkuala.class);
                startActivity(intent);
            }
        });
        kutaraja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_kutaraja.class);
                startActivity(intent);
            }
        });
        ulekareng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_ulekareng.class);
                startActivity(intent);
            }
        });
        luengbata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_luengbata.class);
                startActivity(intent);
            }
        });
        meuraxa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_meuraxa.class);
                startActivity(intent);
            }
        });
        kutaalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_kutaalam.class);
                startActivity(intent);
            }
        });
        jayabaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), list_jayabaru.class);
                startActivity(intent);
            }
        });
        bandaraya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intent = new Intent(getActivity(), list_bandaraya.class);
                startActivity(intent);

            }
        });
        return rootview;
    }
}
