package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button ConvertorButton,RandomButton,SmsButton;
    TextView Numara,Isim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConvertorButton =findViewById(R.id.buttonConvertor);
        RandomButton= findViewById(R.id.buttonRandom);
        SmsButton = findViewById(R.id.buttonSMS);
        Numara = findViewById(R.id.textnum);
        Isim = findViewById(R.id.textisim);

        try {
            ConvertorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Convertor.class);
                    startActivity(intent);
                }
            });
            RandomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(MainActivity.this, RandomA.class);
                    startActivity(intent1);
                }
            });
            SmsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(MainActivity.this, Sms.class);
                    startActivity(intent2);
                }
            });
        }catch (Exception e){}
        ObjectAnimator fadein = ObjectAnimator.ofFloat(Numara,"alpha",0f,1f);
        fadein.setDuration(2000);
        fadein.setInterpolator(new AccelerateDecelerateInterpolator());
        fadein.start();
        ObjectAnimator fadein1 = ObjectAnimator.ofFloat(Isim,"alpha",0f,1f);
        fadein1.setDuration(3000);
        fadein1.setInterpolator(new AccelerateDecelerateInterpolator());
        fadein1.start();
    }
}