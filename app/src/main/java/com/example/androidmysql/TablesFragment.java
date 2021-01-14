package com.example.androidmysql;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidmysql.data.Async;
import com.example.androidmysql.data.AsyncResponse;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Random;

public class TablesFragment extends Fragment {

    private String tableName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get view
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tables, null);
        // get LinearLayout
        LinearLayout ll = (LinearLayout) root.findViewById(R.id.tablesLayout);
        // add Button
        Async asyncTask = (Async) new Async(new AsyncResponse() {

            @Override
            public void processFinish(List value) {
                List<String> recordsList = value;
                int i = 0;

                for (String record : recordsList) {
                    MaterialButton btn = new MaterialButton(getActivity());
                    btn.setText(record.toUpperCase());
                    int j = 0;
                    do {
                        j = new Random().nextInt();
                    } while (root.findViewById(j) != null);
                    btn.setId(j);
                    btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300));
                    btn.setTextSize(25);
                    ll.addView(btn);

                    btn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            tableName = btn.getText().toString();
                            Navigation.findNavController(v).navigate(TablesFragmentDirections.actionTablesFragment2ToRecordsListFragment(tableName));

                        }
                    });
                    i++;
                }
            }
        }).execute("tables");


        return root;
    }
}