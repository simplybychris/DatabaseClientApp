package com.example.androidmysql;

import android.app.ActionBar;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.androidmysql.data.Async;
import com.example.androidmysql.data.AsyncResponse;
import com.google.android.material.button.MaterialButton;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class RecordsListFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(RecordsListFragmentArgs.fromBundle(getArguments()).getTableName()+" RECORDS");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get view
        View root = inflater.inflate(R.layout.fragment_records, null);
        // get tableLayout
        TableLayout tlGridTable = root.findViewById(R.id.tlGridTable);


        String tableName = "";
        if (getArguments() != null) {
            RecordsListFragmentArgs args = RecordsListFragmentArgs.fromBundle(getArguments());
            tableName = args.getTableName();
        }

        Async asyncTask = (Async) new Async(new AsyncResponse() {

            @Override
            public void processFinish(List records) {
                for (int i = 0; i < records.size(); i++) { // iteracja po rzedach
                    List rowRecord = (List) records.get(i);
                    TableRow tRow = new TableRow(getActivity());
//            tRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    tRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                    if (i % 2 == 0) {
                        tRow.setBackgroundResource(R.drawable.table);
                    }
                    tRow.setBackgroundResource(R.drawable.border);

//            tRow.setId(generateRandomId(inflater));
//            String[] record = new Sstring[10]; //tam gdzie 10 to idzie size ile jest kolumn
                    for (int j = 0; j < rowRecord.size(); j++) { // iteracja po kolumnach, czyli kolejne stringi listy w liscie
                        String generatedString = (String) rowRecord.get(j);
                        rowRecord.iterator().next();
                        TextView txt = new TextView(getActivity());
                        txt.setId(generateRandomId(inflater));
                        txt.setGravity(Gravity.CENTER);
                        txt.setText(generatedString);
                        txt.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        txt.setTextSize(20);
                        txt.setTextColor(Color.DKGRAY);
                        txt.setClickable(true);
//                        TypedValue outValue = new TypedValuibute(android.R.attr.selectableItee();
////                        getContext().getTheme().resolveAttrmBackground, outValue, true);
//                        txt.setBackgroundResource(outValue.resourceId);
                        txt.setBackgroundResource(R.drawable.border);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            int[] attrs = new int[]{R.attr.selectableItemBackground};
                            TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
                            int selectableItemBackground = typedArray.getResourceId(0, 0);
                            txt.setForeground(getContext().getDrawable(selectableItemBackground));
                        }
                        txt.setPadding(20, 10, 20, 10);
                        if (i == 0) {
                            txt.setTypeface(null, Typeface.BOLD);
                            txt.setPadding(20, 20, 20, 20);
                        }
//                        txt.setBackgroundResource(R.drawable.table);
                        if (i % 2 == 0) {
                            txt.setBackgroundResource(R.drawable.table);
                        }

                        txt.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                System.out.println("You've clicked " + txt.getText());
//                                tableName = btn.getText().toString();
//                                Navigation.findNavController(v).navigate(TablesFragmentDirections.actionTablesFragment2ToRecordsListFragment(tableName));
//                            Intent intent = new Intent(getActivity(), MainActivity2.class);
//                            String tableName = (String) btn.getText();
//                            intent.putExtra(EXTRA_TABLE_NAME, tableName);
//                            startActivity(intent);

                            }
                        });
                        tRow.addView(txt);
                    }
                    tlGridTable.addView(tRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        }).execute("show " + tableName);


//        //todo dodac do tabeli header
//
//        // add records to table
//        Async asyncTask = (Async) new Async(new AsyncResponse() {
//
//            @Override
//            public void processFinish(List value) {
//                List<String> recordsList = value;
//                int i = 0;
//
//                for (String record : recordsList) {
//                    MaterialButton btn = new MaterialButton(getActivity());
//                    btn.setText(record.toUpperCase());
//                    int j = 0;
//                    do {
//                        j = new Random().nextInt();
//                    } while (root.findViewById(j) != null);
//                    // todo dodac nowy row
//
//                    btn.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            //todo onclik wyswietlaj dropdown z opcjami: edytuj, usun, status(ilosc row, updated...)
////                            Intent intent = new Intent(getActivity(), MainActivity2.class);
////                            String tableName = (String) btn.getText();
////                            intent.putExtra(EXTRA_TABLE_NAME, tableName);
////                            startActivity(intent);
//
//                        }
//                    });
//
////                    TODO dodać addEventListener() przy kliknieciu przenosi do MainActivity2 z parametrami takimi jak nazwa tabeli
//
//
//                    i++;
//                }
//                ;
//            }
//
//            ;
//
//
//        }).execute("show "+tableName);
//
        return root;
    }

    public int generateRandomId(LayoutInflater inflater) {
        View root = inflater.inflate(R.layout.fragment_records, null);
        int id = 0;
        do {
            id = new Random().nextInt();
        } while (root.findViewById(id) != null);

        return id;
    }
}
