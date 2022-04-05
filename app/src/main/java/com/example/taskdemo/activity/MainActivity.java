package com.example.taskdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.taskdemo.R;

public class MainActivity extends AppCompatActivity {
    ImageButton qrBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrBtn = findViewById(R.id.qrBtn);

        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewsArticalsActivity.class);
                startActivity(intent);
            }
        });

    }
}