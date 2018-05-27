package com.ch.article;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class contentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        TextView textView=(TextView)findViewById(R.id.text);

       Intent intent= getIntent();
       String s=intent.getStringExtra("content");

       if (s!=""){

           if (s.length()>80){
               textView.setLines(50);
           }
           textView.setText(s);
       }

    }
}
