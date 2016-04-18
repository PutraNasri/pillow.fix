package es.esy.kinketkuena.pillowfix.isiform;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import es.esy.kinketkuena.pillowfix.MainActivity;
import es.esy.kinketkuena.pillowfix.R;
import es.esy.kinketkuena.pillowfix.RequestHandler;
import es.esy.kinketkuena.pillowfix.config;
import es.esy.kinketkuena.pillowfix.list.list_bandaraya;

public class form extends Activity implements GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private Location mLastLocation;
    public LocationManager mLocationManager;
    private EditText editTextnamakost;
    private EditText editTextnamapemilik;
    private EditText editTextnohp;
    private EditText editTextnik;
    private EditText editTextalamat;
    private EditText editTextharga;
    private EditText editTextdeskripsi;
    private TextView editTextlokasiLAT;
    private TextView editTextlokasiLONG;
    private ImageView imageViewfoto;
    private Spinner spinnerdaerah, spinnerper;
    public static final int REQUEST_CAPTURE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Button clik = (Button) findViewById(R.id.cokfoto);

        if (!hasCamera()) {
            clik.setEnabled(true);
        }
        editTextnamakost = (EditText) findViewById(R.id.editTextnamakost);
        editTextnamapemilik = (EditText) findViewById(R.id.editTextnamapemilik);
        editTextnohp = (EditText) findViewById(R.id.editTextnohp);
        editTextnik = (EditText) findViewById(R.id.editTextnik);
        editTextalamat = (EditText) findViewById(R.id.editTextalamat);
        editTextharga = (EditText) findViewById(R.id.editTextharga);
        editTextdeskripsi = (EditText) findViewById(R.id.editTextdeskripsi);
        editTextlokasiLAT = (TextView) findViewById(R.id.editTextlokasiLAT);
        editTextlokasiLONG = (TextView) findViewById(R.id.editTextlokasLONG);
        imageViewfoto = (ImageView) findViewById(R.id.imageViewfoto);
        spinnerdaerah = (Spinner) findViewById(R.id.spinnerdaerah);
        List<String> item = new ArrayList<String>();
        item.add("Letak Daerah Kost");
        item.add("Syiah kuala");
        item.add("Banda raya");
        item.add("Meuraxa");
        item.add("Uleee kareng");
        item.add("Lueng bata");
        item.add("Kuta raja");
        item.add("Jaya baru");
        item.add("Kuta alam");
        spinnerper = (Spinner) findViewById(R.id.spinnerper);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(form.this, android.R.layout.simple_spinner_dropdown_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdaerah.setAdapter(adapter);

        coklokasi();

    }

    //Adding an employee
    private void addEmployee() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) imageViewfoto.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] fotoo = baos.toByteArray();
        //proses pengambilan string dari variabel
        final String namakost = editTextnamakost.getText().toString().trim();
        final String namapemilik = editTextnamapemilik.getText().toString().trim();
        final String nohp = editTextnohp.getText().toString().trim();
        final String nik = editTextnik.getText().toString().trim();
        final String alamat = editTextalamat.getText().toString().trim();
        final String daerah = spinnerdaerah.getSelectedItem().toString().trim();
        final String harga = editTextharga.getText().toString().trim();
        final String jangka = spinnerper.getSelectedItem().toString().trim();
        final String deskripsi = editTextdeskripsi.getText().toString().trim();
        final String lokasiLAT = editTextlokasiLAT.getText().toString().trim();
        final String lokasiLONG = editTextlokasiLONG.getText().toString().trim();
        final String foto = Base64.encodeToString(fotoo, Base64.DEFAULT);

        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(form.this, "Mengirim...", "HARAP TUNGGU...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(form.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... v) {

                HashMap<String, String> params = new HashMap<>();
                params.put(config.KEY_EMP_NAME, namakost);
                params.put(config.KEY_EMP_DESG, namapemilik);
                params.put(config.KEY_EMP_SAL, nohp);
                params.put(config.KEY_EMP_NIK, nik);
                params.put(config.KEY_EMP_ALAMAT, alamat);
                params.put(config.KEY_EMP_DAERAH, daerah);
                params.put(config.KEY_EMP_HARGA, harga);
                params.put(config.KEY_EMP_JANGKA, jangka);
                params.put(config.KEY_EMP_DESKRIPSI, deskripsi);
                params.put(config.KEY_EMP_LOKASILAT, lokasiLAT);
                params.put(config.KEY_EMP_LOKASILONG, lokasiLONG);
                params.put(config.KEY_EMP_FOTO, foto);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(config.URL_ADD, params);
                return res;
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();


    }

    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void cokfotoile(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE && requestCode == RESULT_OK) ;
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageViewfoto.setImageBitmap(photo);
        }
    }

    public  void coklokasi(View v){
        coklokasi();
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("GPS");
        progress.setMessage("Mengambil Lokasi...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };
        v.postDelayed(progressRunnable, 10000);
    }



    public void coklokasi() {
//proses pengambilan lokasi gps

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,35000, 10, mLocationListener);

    }
    private final LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            System.out.println("onLocationChanged");
            mLastLocation = location;
            editTextlokasiLAT.setText(String.valueOf(location.getLatitude()));
            editTextlokasiLONG.setText(String.valueOf(location.getLongitude()));

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("onStatusChanged");
        }
        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("onProvidermati");
        }
    };
    //////////////////////////////////////////////////////////////////
    @Override
    public void onConnected(Bundle bundle) {
    }
    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
    @Override
    public void onLocationChanged(Location location) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    public void onClick(View v) {
        //pengeceka nanti di buat disini


        if (editTextnamapemilik.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Nama Pemilik tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        if (editTextnohp.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Contact tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        if (editTextnik.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("NIK Pemilik tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        if (editTextnamakost.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Nama Rumah tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        if (editTextalamat.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Alamat Rumah tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        if (spinnerdaerah.getSelectedItem().toString().equals("Letak Daerah Kost")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Daerah belum di pilih")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }

        if (editTextharga.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Harga Sewa Rumah tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        if (spinnerper.getSelectedItem().toString().equals("Tenggang Waktu")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Tenggang waktu sewa belum di pilih")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();
        }
        if (editTextdeskripsi.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Deskripsi tentang Rumah tidak boleh kosong")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();

        } else if (editTextlokasiLAT.getText().toString().equals("")) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Anda belum mengambil lokasi")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();


        } else {
            addEmployee();
/*
            AlertDialog.Builder a_builder = new AlertDialog.Builder(form.this);
            a_builder.setMessage("Terima Kasih Telah Bergabung, ")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent hasilIntent = new Intent(form.this, MainActivity.class);
                            startActivity(hasilIntent);
                            finish();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Info");
            alert.show();


            */



        }
    }
}