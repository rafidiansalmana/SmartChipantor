package com.example.smartchipantor2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void keMonitoring(View view) {
        Intent intent = new Intent(this, MonitoringActivity.class);
        startActivity(intent);
    }

    public void keControlling(View view){
        Intent intent = new Intent(this, ControllingActivity.class);
        startActivity(intent);
    }
}
