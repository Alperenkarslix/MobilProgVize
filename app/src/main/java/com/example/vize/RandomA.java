package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class RandomA extends AppCompatActivity {

    int mindeger, maxdeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randoma);

        LinearLayout linearLayout = findViewById(R.id.linearlayout);
        EditText adetd = findViewById(R.id.adet);
        EditText mind = findViewById(R.id.min);
        EditText maxd = findViewById(R.id.max);
        try {
            maxd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE){
                        int adet = Integer.parseInt(adetd.getText().toString());
                        mindeger = Integer.parseInt(mind.getText().toString());
                        maxdeger = Integer.parseInt(maxd.getText().toString());

                        linearLayout.removeAllViews();

                        for (int i = 0; i < adet; i++){
                            progressBar_ekle(RandomA.this,linearLayout);
                        }
                        return true;
                    }
                    return false;
                }
            });
        }catch (Exception e){}
    }
    private void progressBar_ekle(Context context, LinearLayout linearLayout){
        int min, max, konum;
        do {
            min = mindeger + new Random().nextInt(maxdeger-mindeger);
            max = min + new Random().nextInt(maxdeger- min + 1);
            konum = min + new Random().nextInt(max - min + 1);
        }while (konum == max || konum == min || max == min);
        String yuzde = Double.toString(((double) (konum - min)/ (max-min))*100);
        String küsürat[] = yuzde.split("\\.");
        yuzde = küsürat[0];

        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));

        TextView textyuzde = new TextView(context);
        textyuzde.setId(View.generateViewId());
        String str = konum + " %" + yuzde;
        textyuzde.setText(str);
        ConstraintLayout.LayoutParams paramsyuzde = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        paramsyuzde.topToTop = ConstraintSet.PARENT_ID;
        paramsyuzde.startToStart = ConstraintSet.PARENT_ID;
        paramsyuzde.setMargins(cevrim(180),cevrim(30),0,0);
        textyuzde.setLayoutParams(paramsyuzde);
        constraintLayout.addView(textyuzde);


        TextView textmin = new TextView(context);
        textmin.setId(View.generateViewId());
        textmin.setText(String.valueOf(min));
        ConstraintLayout.LayoutParams paramsmin = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        paramsmin.topToTop = ConstraintSet.PARENT_ID;
        paramsmin.startToStart = ConstraintSet.PARENT_ID;
        paramsmin.setMargins(cevrim(100),cevrim(40),0,0);
        textmin.setLayoutParams(paramsmin);
        constraintLayout.addView(textmin);

        TextView textmax = new TextView(context);
        textmax.setId(View.generateViewId());
        textmax.setText(String.valueOf(max));
        ConstraintLayout.LayoutParams paramsmax = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        paramsmax.topToTop = ConstraintSet.PARENT_ID;
        paramsmax.endToEnd = ConstraintSet.PARENT_ID;
        paramsmax.setMargins(0,cevrim(40),cevrim(100),0);
        textmax.setLayoutParams(paramsmax);
        constraintLayout.addView(textmax);

        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setId(View.generateViewId());
        ConstraintLayout.LayoutParams paramsPB = new ConstraintLayout.LayoutParams(
                cevrim(150),
                cevrim(20)
        );
        paramsPB.topToTop = ConstraintSet.PARENT_ID;
        paramsPB.startToStart = textmin.getId();
        paramsPB.endToEnd = textmax.getId();
        paramsPB.setMargins(cevrim(30),cevrim(40),cevrim(30),cevrim(100));
        progressBar.setLayoutParams(paramsPB);
        progressBar.setMin(0);
        progressBar.setProgress(Integer.parseInt(yuzde));
        progressBar.setMax(100);
        constraintLayout.addView(progressBar);

        linearLayout.addView(constraintLayout);
    }
    public int cevrim(int dp){
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}