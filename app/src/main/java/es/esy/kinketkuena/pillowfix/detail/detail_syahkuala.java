package es.esy.kinketkuena.pillowfix.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import es.esy.kinketkuena.pillowfix.R;
import es.esy.kinketkuena.pillowfix.RequestHandler;
import es.esy.kinketkuena.pillowfix.config;

public class detail_syahkuala extends AppCompatActivity {
    private EditText editTextid;
    private EditText editTextnamakost;
    private EditText editTextnamapemilik;
    private EditText editTextnohp;
    private EditText editTextnik;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_syahkuala);

        Intent intent = getIntent();
        id = intent.getStringExtra(config.EMP_ID);

        editTextid = (EditText)findViewById(R.id.editTextid);
        editTextnamakost = (EditText)findViewById(R.id.editTextnamakost);
        editTextnamapemilik = (EditText)findViewById(R.id.editTextnamapemilik);
        editTextnohp = (EditText)findViewById(R.id.editTextnohp);
        editTextnik = (EditText)findViewById(R.id.editTextnik);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detail_syahkuala.this,"Fetching...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(config.TAG_ID);
            String name = c.getString(config.TAG_NAME);
            String pemilik = c.getString(config.TAG_DESG);
            String nohp = c.getString(config.TAG_SAL);
            String nik = c.getString(config.TAG_NIK);


            editTextnamakost.setText(name);
            editTextnamapemilik.setText(pemilik);
            editTextnohp.setText(nohp);
            editTextnik.setText(nik);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
