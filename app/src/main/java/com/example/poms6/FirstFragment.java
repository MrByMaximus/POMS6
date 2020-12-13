package com.example.poms6;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstFragment extends Fragment {
    private EditText Name;
    private EditText Surname;
    private TextView Result;
    public String TextResult;
    private Button button;
    View view;
    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper=new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        Name = view.findViewById(R.id.Input1);
        Surname = view.findViewById(R.id.Input2);
        Result = view.findViewById(R.id.textResult);

        button=view.findViewById(R.id.buttonAccess);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { OnClickRes(); }
                }
        );
        return view;
    }

    public void OnClickRes() {
        if (Name.getText().toString().equals("") || Surname.getText().toString().equals("")) {
            Result.setText("Данные не введены");
            Toast.makeText(getActivity(),Result.getText(),Toast.LENGTH_SHORT).show();
        }
        else {
            String username = Name.getText().toString();
            String usersurname = Surname.getText().toString();
            TextResult = "Здравствуйте, "+username+" "+usersurname+"!";

            SQLiteDatabase database=dbHelper.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(DBHelper.KEY_PROGRAMS,"1");
            contentValues.put(DBHelper.KEY_RESULT,TextResult);
            database.insert(DBHelper.TABLE_RESULT,null,contentValues);

            Result.setText(TextResult);
            MainActivity parent = (MainActivity)getActivity();
            parent.addHistory(new historyItem("Результат 1 фрагмента\n"+TextResult));
            Toast.makeText(getActivity(),Result.getText(),Toast.LENGTH_SHORT).show();
        }
    }
}