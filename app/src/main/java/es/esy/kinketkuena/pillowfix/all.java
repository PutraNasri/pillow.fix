package es.esy.kinketkuena.pillowfix;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by addd on 2/27/2016.
 */
public class all extends Fragment  {

    public all() {
    }
    GoogleMap gMaps;
    private GoogleApiClient client;
    private String JSON_STRING;

    String lokasiLAT="";
    String lokasiLONG="";
    MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapslbs);
        mMapView.onCreate(savedInstanceState);


        getJSON();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Mengambil Data","Loading...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(config.URL_GET_ALL_LOCATION);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    private void showEmployee() {

        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);

                lokasiLAT = jo.getString(config.TAG_LOKASILAT);
                lokasiLONG = jo.getString(config.TAG_LOKASILONG);

                HashMap<String, String> employees = new HashMap<>();

                employees.put(config.TAG_LOKASILAT, lokasiLAT);
                employees.put(config.TAG_LOKASILONG, lokasiLONG);


                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        mMapView.onResume();// needed to get the map to display immediately

        try {

            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gMaps = mMapView.getMap();
        // latitude and longitude


        Double lok1 = 17.385044;


        Double lok2 = 78.486671;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(lok1, lok2)).title("Maps testing all");
        MarkerOptions marker2 = new MarkerOptions().position(
                new LatLng(lok1, lok2)).title("Maps testing dua");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        gMaps.addMarker(marker);
        gMaps.addMarker(marker2);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
        gMaps.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

    }
}