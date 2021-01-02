package com.example.androidmysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    TextView text;

    Button show;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.textView);

        show = (Button) findViewById(R.id.button);


        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/example","root","test");

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

                    String records ="";
                    while (resultSet.next()) {

                        records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";

                    }
                    text.setText(records);

                } catch (Exception e) {

                    e.printStackTrace();
                    text.setText(e.toString());

                }
            }


        });

    }

}