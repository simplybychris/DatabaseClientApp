package com.example.androidmysql;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String selectedTableName = intent.getStringExtra(MainActivity.EXTRA_TABLE_NAME);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.tableText);
        textView.setText(selectedTableName);

    }
}