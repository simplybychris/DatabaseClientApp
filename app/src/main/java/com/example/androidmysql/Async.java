package com.example.androidmysql;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Async extends AsyncTask<String, Void, Void> {
    String records = "", error = "";
    Integer columncount = 0, rowcount = 0;
    List<String> columnNamesList = new ArrayList<>();

    public AsyncResponse delegate = null;

    public Async(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(String... arg0) {
        String queryTable = null;
        String id = null;
        String[] command = arg0[0].split(" ");
        if (command.length >= 2) {
            queryTable = command[1];
        }


        /**
         * Check argument provided in execute()
         */


        /**
         *  SHOW TABLES NAMES => Display all tables available
         */
        if (Arrays.asList(command).contains("tables")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.18/android", "andro", "andro");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("show tables");

                while (resultSet.next()) {

                    records += resultSet.getString(1) + "\n";
                }
            } catch (Exception e) {
                error = e.toString();
            }
        }
        /**
         *  SHOW [table_name] => Display all records of certain table
         */
        if (Arrays.asList(command).contains("show")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/android", "andro", "andro");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + queryTable);
                ResultSetMetaData md = resultSet.getMetaData();

//                 ===  Column count  ===
                columncount = md.getColumnCount();

//                 ===  Row count  ===
                resultSet.last();
                rowcount = resultSet.getRow();
                resultSet.beforeFirst();

//                 ===  Columns name  ===
                for (int i = 1; i <= columncount; i++) {
                    String name = md.getColumnName(i);
                    columnNamesList.add(name);
                }

                while (resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
                }
                System.out.println("Records fetched successfully");
            } catch (Exception e) {
                error = e.toString();
            }
        } else if (Arrays.asList(command).contains("delete")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.18:3306/android", "andro", "andro");
                DatabaseMetaData md = connection.getMetaData();
                ResultSet resultSet = md.getPrimaryKeys(null, null, queryTable);

                id = command[2];
                String pkey = "";
                while (resultSet.next()) {
//                 ===  Primary key name  ===
                    pkey = resultSet.getString(4);
                }

                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM " + queryTable + " WHERE " + pkey + "=" + id);

                System.out.println("Record deleted successfully");
                while (resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
                }

            } catch (Exception e) {
                error = e.toString();
            }
        } else {
            error = "Wrong command";
        }

        //TODO select specified user by id

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        delegate.processFinish(records);
    }
//    @Override
//    protected void onPostExecute(Void aVoid) {
//
//        text.setText(records);
//
//        if (error != "")
//
//            text.setText(error);
//
//        super.onPostExecute(aVoid);
//
//    }
}
