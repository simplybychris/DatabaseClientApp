package com.example.androidmysql;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TABLE_NAME = "com.example.androidmysql";
    List<String> recordsList;

    TextView text;
    Button tableBtn;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        // get LinearLayout
        LinearLayout ll = (LinearLayout) findViewById(R.id.tablesWrapper);

        // add Button
        Async asyncTask = (Async) new Async(new AsyncResponse() {

            @Override
            public void processFinish(List value) {
                recordsList = value;
                int i=0;

                for(String record : recordsList){
                    Button btn = new Button(getApplicationContext());
                    btn.setText(record.toUpperCase());
//                    String btnId = "tablesButton"+i;
                    int j=0;
                    do{
                        j = new Random().nextInt();
                    } while(findViewById(j) != null);
                    btn.setId(j);
                    btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300));
                    btn.setTextSize(25);
                    ll.addView(btn);

                    btn.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                            String tableName = (String) btn.getText();
                            intent.putExtra(EXTRA_TABLE_NAME, tableName);
                            startActivity(intent);
                        }
                    });

//                    TODO dodać addEventListener() przy kliknieciu przenosi do MainActivity2 z parametrami takimi jak nazwa tabeli


                    i++;
                };
            };



        }).execute("tables");

        */

//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

//        text = (TextView) findViewById(R.id.textView);
//        show = (Button) findViewById(R.id.button);

//        show.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Async asyncTask = (Async) new Async(new AsyncResponse() {
//
//                    @Override
//                    public void processFinish(String value) {
//                        text.setText(value);
//                    }
//
//                }).execute("tables");
//            }
//
//        });
//                try {
//
//
//
//                    Class.forName("com.mysql.cj.jdbc.Driver");
//                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/example?useUnicode=true" +
//                            "user=root&password=");
//                    if(connection == null) System.out.println("error conn");
//                    Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//
//                    String records ="";
//                    while (resultSet.next()) {
//
//                        records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
//
//                    }
//                    text.setText(records);
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                    text.setText(e.toString());
//
//                }
//            }
//
//
//        });


    }
}

