package com.example.androidmysql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmysql.data.Async;
import com.example.androidmysql.data.AsyncResponse;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUpdateFragment newInstance(String param1, String param2) {
        AddUpdateFragment fragment = new AddUpdateFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String tableName = getArguments().getString("table_name");
        String[][] passedData = null;
        if (bundle.getSerializable("selectedData") != null) {
            passedData = (String[][]) getArguments().getSerializable("selectedData");
        }

        // get view
        View root = inflater.inflate(R.layout.fragment_add_update, null);
        // get tableLayout
        LinearLayout ll = root.findViewById(R.id.dataLinearLayout);

        for (int i = 0; i < passedData.length; i++) {

            LinearLayout llrow = (LinearLayout) inflater.inflate(R.layout.lledit, ll, false);
            llrow.setId(generateRandomId(root));

            TextView title = (TextView) inflater.inflate(R.layout.tvedit, llrow, false);
            title.setText(passedData[i][0]);

            EditText editText = (EditText) inflater.inflate(R.layout.etedit, llrow, false);
            editText.setId(generateRandomId(root));
            editText.setHint(passedData[i][0]);
            if (passedData[i][1] != null) {
                editText.setText(passedData[i][1]);
            }

            llrow.addView(title);
            llrow.addView(editText);

            ll.addView(llrow);
        }

        Button confirmBtn = (Button) inflater.inflate(R.layout.editbtn, ll, false);
        String[][] finalPassedData = passedData;
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] providedData = new String[ll.getChildCount() - 1];
                boolean error = false;
                for (int i = 0; i < ll.getChildCount() - 1; i++) {
                    EditText llChild = (EditText) ((LinearLayout) ll.getChildAt(i)).getChildAt(1);
                    if (!validate(llChild)) {
                        error = true;
                        break;
                    }
                    providedData[i] = llChild.getText().toString();
                }
                if (!error) {
                    String headerNames = "";
                    String values = "'";
                    for (int i = 0; providedData.length > i; i++) {
                        if (i != providedData.length - 1) {
                            headerNames += finalPassedData[i][0] + ", ";
                            values += providedData[i] + "', '";
                        } else {
                            headerNames += finalPassedData[i][0] + "";
                            values += providedData[i] + "'";
                        }
                    }

                    String updateData = "";
                    for (int i = 0; i < providedData.length; i++) {
                        if (i != providedData.length - 1) {
                            updateData += finalPassedData[i][0] + " = '" + providedData[i] + "', ";
                        } else {
                            updateData += finalPassedData[i][0] + " = '" + providedData[i] + "'";
                        }
                    }

                    String[] tableTitle = getActivity().getTitle().toString().split(" ");

                    String query = "insert into " + tableName + "(" + headerNames + ") values(" + values +
                            ") ON DUPLICATE KEY UPDATE " + updateData + ";";
                    Async asyncTask = (Async) new Async(new AsyncResponse() {

                        @Override
                        public void processFinish(List records) {
                            Bundle bundle = new Bundle();
                            bundle.putString("table_name", tableName);
                            Navigation.findNavController(root).navigate(R.id.action_addUpdateFragment_to_recordsFragment, bundle);
                        }
                    }).execute(query);
                    Toast.makeText(getActivity(), "Submited successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        ll.addView(confirmBtn);

        return root;
    }

    public int generateRandomId(View v) {
        int id = 0;
        do {
            id = new Random().nextInt();
        } while (v.findViewById(id) != null);

        return id;
    }

    private boolean validate(EditText editText) {
// check whether the field is empty or not
        if (editText.getText().toString().trim().length() < 1) {
// display the error if field is empty
            editText.setError("Input this field and try again");
// set focus on field so that cursor will automatically move to that field
            editText.requestFocus();
            return false;
        }
        return true;
    }

}