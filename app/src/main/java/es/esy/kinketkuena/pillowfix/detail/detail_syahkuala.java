package es.esy.kinketkuena.pillowfix.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.esy.kinketkuena.pillowfix.Maps;
import es.esy.kinketkuena.pillowfix.R;
import es.esy.kinketkuena.pillowfix.RequestHandler;
import es.esy.kinketkuena.pillowfix.config;

public class detail_syahkuala extends AppCompatActivity {
    private TextView editTextid;
    private TextView editTextnamakost;
    private TextView editTextnamapemilik;
    private TextView editTextnohp;
    private TextView editTextnik;
    private TextView editTextdeskripsi;
    private TextView editTextharga;
    private TextView editTextalamat;
    private ImageView image;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_syahkuala);

        Intent intent = getIntent();
        id = intent.getStringExtra(config.EMP_ID);


        editTextid = (TextView)findViewById(R.id.editTextid);
        editTextnamakost = (TextView)findViewById(R.id.editTextnamakost);
        editTextnamapemilik = (TextView)findViewById(R.id.editTextnamapemilik);
        editTextnohp = (TextView)findViewById(R.id.editTextnohp);
        editTextnik = (TextView)findViewById(R.id.editTextnik);
        editTextalamat=(TextView)findViewById(R.id.editTextalamat);
        image = (ImageView)findViewById(R.id.image);
        editTextdeskripsi= (TextView)findViewById(R.id.editTextdeskripsi);
        editTextharga=(TextView)findViewById(R.id.editTextharga);
        getEmployee();

    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(detail_syahkuala.this,"Menampilkan Data...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(config.URL_GET_EMP, id);
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
            String foto = c.getString(config.TAG_FOTO);
            String name = c.getString(config.TAG_NAME);
            String pemilik = c.getString(config.TAG_DESG);
            String nohp = c.getString(config.TAG_SAL);
            String nik = c.getString(config.TAG_NIK);
            String deskripsi = c.getString(config.TAG_DESKRIPSI);
            String harga = c.getString(config.TAG_HARGA);
            String jangka = c.getString(config.TAG_JANGKA);
            String alamat = c.getString(config.TAG_ALAMAT);
            String lokasi = c.getString(config.TAG_LOKASI);


            ///////////////proses mengubah binner blob ke file bitmap//////////////
            byte[] bytarray = Base64.decode(foto, Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0, bytarray.length);
            ////////////////////proses-tampilkan-data//////////////////////////////


            image.setImageBitmap(bmimage);
            editTextnamakost.setText(name);
            editTextnamapemilik.setText(pemilik);
            editTextnohp.setText(nohp);
            editTextharga.setText("Rp "+harga+" Per "+jangka);
            editTextalamat.setText(alamat);
            editTextdeskripsi.setText(deskripsi);

            HashMap<String,Object> employees = new HashMap<>();
            employees.put(config.TAG_LOKASI, lokasi);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void maps(View view)  {


    }


}
