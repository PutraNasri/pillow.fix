package es.esy.kinketkuena.pillowfix;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import es.esy.kinketkuena.pillowfix.isiform.form;

/**
 * Created by addd on 2/27/2016.
 */
public class daftar extends Fragment {

    public daftar() {
    }
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_daftar, container, false);
        Button formm = (Button) rootview.findViewById(R.id.buttonf);
        Button bantuan = (Button) rootview.findViewById(R.id.buttonb);

        formm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),form.class);
                startActivity(intent);
            }
        });
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(),form.class);
                //startActivity(intent);
            }
        });
        return rootview;
    }}

