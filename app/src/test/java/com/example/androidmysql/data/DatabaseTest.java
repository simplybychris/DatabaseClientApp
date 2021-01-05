package com.example.androidmysql.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @org.junit.jupiter.api.Test
    void doInBackground() {

        List records = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/android", "andro", "andro");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            ResultSetMetaData md = resultSet.getMetaData();


            while (resultSet.next()) {

                records.add(resultSet.getString(2));
            }
            System.out.println(records.toString());
            System.out.println("Records fetched successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void onPostExecute() {
    }
}