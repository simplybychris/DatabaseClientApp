package com.example.androidmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    public static void main(String[] args){
        System.out.print("Hello World");
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/example","root","test");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            String records ="";
            while (resultSet.next()) {

                records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";

            }

            System.out.println(records);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
