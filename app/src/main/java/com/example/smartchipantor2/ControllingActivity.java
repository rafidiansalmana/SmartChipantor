package com.example.smartchipantor2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControllingActivity extends AppCompatActivity {

    private TextView statusBiasa;
    private TextView statusAsam;
    private TextView statusBasa;
    private TextView statusHama;
    private TextView statusBlower;

    private Switch switchBiasa;
    private Switch switchAsam;
    private Switch switchBasa;
    private Switch switchHama;
    private Switch switchBlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlling);

        final Switch switchBiasa;
        final Switch switchAsam;
        final Switch switchHama;
        final Switch switchBasa;
        final Switch switchBlower;

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        statusBiasa = (TextView) findViewById(R.id.statusBiasa);
        statusAsam = (TextView) findViewById(R.id.statusAsam);
        statusBasa = (TextView) findViewById(R.id.statusBasa);
        statusHama = (TextView) findViewById(R.id.statusHama);
        statusBlower = (TextView) findViewById(R.id.statusBlower);

        switchBiasa = (Switch) findViewById(R.id.switchBiasa);
        switchAsam = (Switch) findViewById(R.id.switchAsam);
        switchBasa = (Switch) findViewById(R.id.switchBasa);
        switchHama = (Switch) findViewById(R.id.switchHama);
        switchBlower = (Switch) findViewById(R.id.switchBlower);

        //set the switch to ON

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String biasa = dataSnapshot.child("P_Biasa").getValue(String.class);
                String asam = dataSnapshot.child("P_Asam").getValue(String.class);
                String basa = dataSnapshot.child("P_Kapur").getValue(String.class);
                String hama = dataSnapshot.child("P_Hama").getValue(String.class);
                String blower = dataSnapshot.child("Blower").getValue(String.class);

                //buat ngecek kondisi
                if (biasa.equals("0")) {
                    switchBiasa.setChecked(false);
                } else {
                    switchBiasa.setChecked(true);
                }

                if (asam.equals("0")) {
                    switchAsam.setChecked(false);
                } else {
                    switchAsam.setChecked(true);
                }

                if (basa.equals("0")) {
                    switchBasa.setChecked(false);
                } else {
                    switchBasa.setChecked(true);
                }

                if (hama.equals("0")) {
                    switchHama.setChecked(false);
                } else {
                    switchHama.setChecked(true);
                }

                if (blower.equals("0")) {
                    switchBlower.setChecked(false);
                } else {
                    switchBlower.setChecked(true);
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        //attach a listener to check for changes in state
        switchBiasa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    statusBiasa.setText("Status: ON");
                    myRef.child("P_Biasa").setValue("1");
                } else {
                    statusBiasa.setText("Status: OFF");
                    myRef.child("P_Biasa").setValue("0");
                }

            }
        });


        switchAsam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusAsam.setText("Status: ON");
                    myRef.child("P_Asam").setValue("1");
                } else {
                    statusAsam.setText("Status: OFF");
                    myRef.child("P_Asam").setValue("0");

                }
            }
        });


        switchBasa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusBasa.setText("Status: ON");
                    myRef.child("P_Kapur").setValue("1");
                } else {
                    statusBasa.setText("Status: OFF");
                    myRef.child("P_Kapur").setValue("0");
                }
            }
        });


        switchHama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusHama.setText("Status: ON");
                    myRef.child("P_Hama").setValue("1");
                } else {
                    statusHama.setText("Status: OFF");
                    myRef.child("P_Hama").setValue("0");
                }
            }
        });


        switchBlower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    statusBlower.setText("Status: ON");
                    myRef.child("Blower").setValue("1");
                } else {
                    statusBlower.setText("Status: OFF");
                    myRef.child("Blower").setValue("0");
                }
            }
        });
    }

    public void keMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }


}
