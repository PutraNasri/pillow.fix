package es.esy.kinketkuena.pillowfix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Maps extends AppCompatActivity {
private String lokasi;
    private TextView textView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        lokasi = intent.getStringExtra(config.EMP_LOKASI);

        textView9 = (TextView)findViewById(R.id.textView9);
        textView9.setText(lokasi);
    }
}
