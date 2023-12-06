package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Convertor extends AppCompatActivity {

    private final String[] tabanlar = {"2","8","16"};
    private final String[] boyutlar = {"Megabyte","Kilobyte","Byte"};
    String secimt,secimb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        EditText decimal = findViewById(R.id.decimal);
        EditText gbyte = findViewById(R.id.gbyte);
        EditText celcius = findViewById(R.id.celcius);

        TextView sonuctaban = findViewById(R.id.tsonuc);
        TextView sonucboyut = findViewById(R.id.bsonuc);
        TextView sonucsicaklik = findViewById(R.id.celsonuc);

        RadioButton fah = findViewById(R.id.fah);
        RadioButton kel = findViewById(R.id.kel);

        ArrayAdapter<String> veriAdaptertaban, veriAdapterboyut;

        Spinner spinnerboyut = findViewById(R.id.boyut);
        Spinner spinnertaban = findViewById(R.id.taban);

        veriAdapterboyut = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, boyutlar);
        veriAdaptertaban = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tabanlar);

        spinnerboyut.setAdapter(veriAdapterboyut);
        spinnertaban.setAdapter(veriAdaptertaban);

        try {
            spinnertaban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        secimt = spinnertaban.getSelectedItem().toString();
                        String str = decimal.getText().toString();
                        if (!str.isEmpty()) {
                            Double dec = Double.parseDouble(str);
                            String sonuc = tabanHesap(dec, Integer.parseInt(secimt));
                            sonuctaban.setText("Sonuç: " + sonuc);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            spinnerboyut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        secimb = spinnerboyut.getSelectedItem().toString();
                        String str = gbyte.getText().toString();
                        if (!str.isEmpty()) {
                            Double dec = Double.parseDouble(str);
                            String sonuc = boyutHesap(dec, secimb);
                            sonucboyut.setText("Sonuç: " + sonuc);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            fah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked){
                            Double celsius = Double.parseDouble(celcius.getText().toString());
                            double fahrenayt = (celsius * 9/5) + 32;
                            sonucsicaklik.setText("Sonuç: " + fahrenayt);
                        }
                    }catch (Exception e){}
                }
            });
            kel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked){
                            Double celsius = Double.parseDouble(celcius.getText().toString());
                            double kelvin = celsius +273.15;
                            sonucsicaklik.setText("Sonuç: " + kelvin);
                        }
                    }catch (Exception e){}
                }
            });
        }catch (Exception e) {}
    }
    public static String tabanHesap(double decimalv, int taban){
        long intpart = (long) decimalv;
        String result = Long.toString(intpart,taban);
        if(decimalv % 1 != 0){
            result +=".";
            double kesir =decimalv % 1;
            for (int i =0; i <10; i++){
                kesir *=taban;
                int tam =(int) kesir;
                result += Integer.toString(tam,taban);
                kesir -= tam;
            }
        }
        return result;
    }
    public  static  String boyutHesap(double value, String boyut){
        double result = 0;
        String resultString;
        switch (boyut){
            case "Megabyte":
                result = value * 1024 ;
                resultString = String.format("%.0f", result) + "MB";
                break;
            case "Kilobyte":
                result = value * 1024 * 1024 ;
                resultString = String.format("%.0f", result) + "KB";
                break;
            case "Byte":
                result = value * 1024 * 1024 * 1024 ;
                resultString = String.format("%.0f", result) + "B";
                break;
            default:
                resultString = "Yanlış Seçim Yaptınız";
        }
        return resultString;
    }


}