package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        button = findViewById(R.id.add_btn);
        textView = findViewById(R.id.textview);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            String val = bundle.getString("city_name");
            textView.setText(val);
        }

        button.setOnClickListener(v -> {
            this.finish();
        });
    }
}