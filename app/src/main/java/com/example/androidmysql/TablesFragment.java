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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_TABLE_NAME = "com.example.androidmysql";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String tableName;
    private String mParam1;
    private String mParam2;

    public TablesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TablesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TablesFragment newInstance(String param1, String param2) {
        TablesFragment fragment = new TablesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
//                    btn.setBackgroundColor(0xff0099ff);
                    ll.addView(btn);

                    btn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            tableName = btn.getText().toString();
                            Navigation.findNavController(v).navigate(TablesFragmentDirections.actionTablesFragment2ToRecordsListFragment(tableName));
//                            Intent intent = new Intent(getActivity(), MainActivity2.class);
//                            String tableName = (String) btn.getText();
//                            intent.putExtra(EXTRA_TABLE_NAME, tableName);
//                            startActivity(intent);

                        }
                    });

//                    TODO dodać addEventListener() przy kliknieciu przenosi do MainActivity2 z parametrami takimi jak nazwa tabeli


                    i++;
                }
                ;
            }

            ;


        }).execute("tables");


        return root;
    }
}