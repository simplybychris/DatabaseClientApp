package com.example.androidmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView text;

    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        text = (TextView) findViewById(R.id.textView);
        show = (Button) findViewById(R.id.button);

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Async asyncTask = (Async) new Async(new AsyncResponse() {

                    @Override
                    public void processFinish(String value) {
                        text.setText(value);
                    }

                }).execute("tables");
            }

        });
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

