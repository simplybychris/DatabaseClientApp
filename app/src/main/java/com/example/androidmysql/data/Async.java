package com.example.androidmysql.data;

import android.os.AsyncTask;

import java.sql.Array;
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

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private String host = "localhost";
    private String port = "3306";
    private String database = "android";
    private String user = "andro";
    private String password = "andro";

    String error = "";
    Integer columncount = 0;
    List<String> recordsList = new ArrayList<>();
    List<List> nestedList = new ArrayList<>();
    ArrayList<String> columnsList = new ArrayList<>();

    public AsyncResponse delegate = null;

    public Async(AsyncResponse delegate) {
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
                Class.forName(DRIVER);
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("show tables");
                while (resultSet.next()) {

                    recordsList.add(resultSet.getString(1));
                }
            } catch (Exception e) {
                error = e.toString();
            }
        }
        if (Arrays.asList(command).contains("insert")) {
            try {
                Class.forName(DRIVER);
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                Statement statement = connection.createStatement();
                System.out.println("argument: " + arg0[0]);
                statement.executeUpdate(arg0[0]);
//                }
            } catch (Exception e) {
                error = e.toString();
            }
        }
        /**
         *  SHOW [table_name] => Display all records of certain table
         */
        //for example: primarykey user
        if (Arrays.asList(command).contains("primarykey")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                DatabaseMetaData md = connection.getMetaData();
                ResultSet resultSet = md.getPrimaryKeys(null, null, queryTable);

                String pkey = "";
                while (resultSet.next()) {
//                 ===  Primary key name  ===
                    pkey = resultSet.getString(4);
                }
                recordsList.add(pkey);

            } catch (Exception e) {
                error = e.toString();
            }
        }
        if (Arrays.asList(command).contains("show")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + queryTable);
                ResultSetMetaData md = resultSet.getMetaData();
//
////                 ===  Column count  ===
                columncount = md.getColumnCount();
////                 ===  Columns name  ===
                for (int i = 1; i <= columncount; i++) {
                    String name = md.getColumnName(i);
                    columnsList.add(name);
                }
                nestedList.add(columnsList);

                while (resultSet.next()) {
                    ArrayList<String> rowRecord = new ArrayList();
                    for (int i = 1; i <= columncount; i++) {
                        rowRecord.add(resultSet.getString(i));
                    }
                    nestedList.add(rowRecord);
                }
                System.out.println("Records fetched successfully");
            } catch (Exception e) {
                error = e.toString();
            }
        } else if (Arrays.asList(command).contains("delete")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
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

                    recordsList.add(resultSet.getString(1));
                }

            } catch (Exception e) {
                error = e.toString();
            }
        } else {
            error = "Wrong command";
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (!nestedList.isEmpty()) {
            delegate.processFinish(nestedList);
        } else {
            delegate.processFinish(recordsList);
        }
    }
}
