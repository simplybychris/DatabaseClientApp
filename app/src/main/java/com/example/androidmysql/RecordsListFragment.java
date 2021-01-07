package com.example.androidmysql;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmysql.data.Async;
import com.example.androidmysql.data.AsyncResponse;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;


public class RecordsListFragment extends Fragment {

    // ChoosedCell
    public final TextView[] choosedCell = new TextView[1];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(RecordsListFragmentArgs.fromBundle(getArguments()).getTableName() + " RECORDS");

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

                    for (int j = 0; j < rowRecord.size(); j++) {
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
                        txt.setBackgroundResource(R.drawable.border);

                        //setting selectaleItemBackground as foreground
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            int[] attrs = new int[]{R.attr.selectableItemBackground};
                            TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
                            int selectableItemBackground = typedArray.getResourceId(0, 0);
                            txt.setForeground(getContext().getDrawable(selectableItemBackground));
                        }
                        txt.setPadding(20, 10, 20, 10);

//                        txt.setBackgroundResource(R.drawable.table);
                        if (i % 2 == 0) {
                            txt.setBackgroundResource(R.drawable.table);
                        }

                        txt.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                System.out.println("You've clicked " + txt.getText());
                                choosedCell[0] = txt;
                                popup_window(v);
                            }
                        });

                        if (i == 0) {
                            txt.setTypeface(null, Typeface.BOLD);
                            txt.setPadding(20, 20, 20, 20);
                            txt.setOnClickListener(null);
                        }
                        tRow.addView(txt);
                    }
                    tlGridTable.addView(tRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        }).execute("show " + tableName);
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

//    public void refreshTable(View v, List records) {
//        TableLayout tl = v.findViewById(R.id.tlGridTable);
//        tl.removeAllViews();
//    }

    private void setClipboard(Context context, String label, String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(label, text);
            clipboard.setPrimaryClip(clip);
        }
    }

    public String iterateRow(TableRow tRow) {
        String text = "";
        for (int i = 0; i < tRow.getChildCount() - 1; i++) {
            TextView v = (TextView) tRow.getChildAt(i);
            text += v.getText() + ", ";
        }
        text += ((TextView) tRow.getChildAt(tRow.getChildCount() - 1)).getText();
        return text;
    }

    public String iterateColumn(TextView textView) {
        String text = "";
        TableLayout tl = (TableLayout) ((TableRow) textView.getParent()).getParent();
        int indexOfTableRow = ((TableRow) textView.getParent()).indexOfChild(textView);
        System.out.println("Index rzedu: "+ indexOfTableRow);
        for (int i = 1; i < tl.getChildCount(); i++) {
            TableRow tRow = (TableRow) tl.getChildAt(i);
            System.out.println("iteracja i = "+i);
            for (int j = 0; j < tRow.getChildCount(); j++) {
                System.out.println("--j = "+j);
                if (j == indexOfTableRow) {
                    if(i == (tl.getChildCount()-1)){
                        TextView v = (TextView) tRow.getChildAt(j);
                        text += v.getText();
                        continue;
                    }
                    TextView v = (TextView) tRow.getChildAt(j);
                        text += v.getText() + ", ";
                }
            }
        }
        return text;
    }

    public void popup_window(View v) {
        View menuItemView = v;
        PopupMenu popupMenu = new PopupMenu(getActivity(), menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        MenuCompat.setGroupDividerEnabled(popupMenu.getMenu(), true);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("ResourceType")
            public boolean onMenuItemClick(MenuItem item) {
                TableRow vParent = (TableRow) v.getParent();
                TableLayout tl = (TableLayout) vParent.getParent();
                switch (item.getItemId()) {
                    case R.id.copy_row:
                        String copied_row = iterateRow(vParent);
                        setClipboard(getActivity(), "copy_row", copied_row);
                        Toast.makeText(getActivity(), "Row copied to clipboard", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.copy_column:
                        String copied_column = iterateColumn(choosedCell[0]);
                        setClipboard(getActivity(), "copy_column", copied_column);
                        Toast.makeText(getActivity(), "Column copied to clipboard", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.option_1:
                        Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.option_2:
                        Toast.makeText(getActivity(), "Record edited", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.option_3:
                        tl.removeView(vParent);
                        Toast.makeText(getActivity(), "Record deleted", Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}
