package es.esy.kinketkuena.pillowfix.list;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import es.esy.kinketkuena.pillowfix.R;
import es.esy.kinketkuena.pillowfix.RequestHandler;
import es.esy.kinketkuena.pillowfix.config;
import es.esy.kinketkuena.pillowfix.detail.detail_kutaalam;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class list_kutaalam extends Activity implements ListView.OnItemClickListener{
    private ListView listView;
    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kutaalam);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);


        getJSON();



    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(list_kutaalam.this,"Mengambil Data","Loading...",false,false);
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
                String s = rh.sendGetRequest(config.URL_GET_ALL_KUTAALAM);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    private void showEmployee(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(config.TAG_ID);
                String name = jo.getString(config.TAG_NAME);
                String alamat = jo.getString(config.TAG_ALAMAT);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(config.TAG_ID,id);
                employees.put(config.TAG_NAME,name);
                employees.put(config.TAG_ALAMAT,alamat);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                list_kutaalam.this, list, R.layout.list_filter,
                new String[]{config.TAG_NAME,config.TAG_ALAMAT},
                new int[]{R.id.name, R.id.alamat});

        listView.setAdapter(adapter);



    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, detail_kutaalam.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(config.TAG_ID).toString();
        intent.putExtra(config.EMP_ID,empId);
        startActivity(intent);
    }

    //public void refles(View view) {
    //  getJSON();
    //}

}
