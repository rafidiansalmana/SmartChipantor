package com.example.smartchipantor2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Locale;

public class MonitoringActivity extends AppCompatActivity {

    public final static String TEXT_SUHU = "Suhu Ruangan";
    TextView txtSuhu, txtHumidity, txtPh;
    ImageView imgSuhu, imgHumidity, imgPh;
    TextToSpeech speechMon;
    ImageButton btnSpeak;
    String toSpeakSuhu, toSpeakHumid, toSpeakPh;
    Locale locInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        txtSuhu = findViewById(R.id.txtSuhu);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtPh = findViewById(R.id.txtPh);

        imgSuhu = findViewById(R.id.imgSuhu);
        imgHumidity = findViewById(R.id.imgHumid);
        imgPh = findViewById(R.id.imgPh);

        locInd = new Locale("in", "ID");

//        suhu = findViewById(R.string.Suhu1);

        btnSpeak = findViewById(R.id.controlButton);

        speechMon = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speechMon.setLanguage(locInd);
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Result result = dataSnapshot.getValue(Result.class);
                        toSpeakSuhu = result.Temperature.toString().replace(".", " koma ");
                        toSpeakHumid = result.Humidity.toString().replace(".", " koma ");
                        toSpeakPh = result.PH.toString().replace(".", " koma ");
                        speechMon.speak("informasi kondisi grin hous, dengan suhu ruangan" + toSpeakSuhu +
                                "derajat selsius, kelembab ban udara " + toSpeakHumid + "persen," +
                                "dan Peha Tanah " + toSpeakPh
                                , TextToSpeech.QUEUE_FLUSH, null);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Result result = dataSnapshot.getValue(Result.class);
                Log.d("cekData", result.Blower); //ngecek
                txtSuhu.setText(result.Temperature.toString()); //disesuaikan dengan kondisi
                txtHumidity.setText(result.Humidity.toString());
                txtPh.setText(result.PH.toString());
                if (result.Temperature >= 27) {
//                        imgSuhu.setBackgroundResource(R.drawable.suhu2);
                    txtSuhu.setTextColor(Color.parseColor("#d11717"));
                }
                if (result.Temperature <= 26) {
                    txtSuhu.setTextColor(Color.parseColor("#000000"));
                }
                if (result.Humidity <= 60 || result.Humidity >= 80) {
//                        imgHumidity.setImageDrawable(R.id.imgHumid2);
                    txtHumidity.setTextColor(Color.parseColor("#d11717"));
                }
                else if (result.Humidity >= 61 || result.Humidity <= 79) {
                    txtHumidity.setTextColor(Color.parseColor("#000000"));
                }
                if (result.PH <= 4.9 || result.PH >= 8.1) {
//                        imgPh.setImageDrawable(R.id.imgPh2);
                    txtPh.setTextColor(Color.parseColor("#d11717"));
                }
                else if (result.PH >= 5 || result.PH <= 8) {
//                        imgPh.setImageDrawable(R.id.imgPh2);
                    txtPh.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    @Override
    protected void onPause() {
        if (speechMon != null) {
            speechMon.stop();
            speechMon.shutdown();
        }
        super.onPause();
    }

    public void keMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


}
