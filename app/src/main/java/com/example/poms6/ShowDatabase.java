package com.example.poms6;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowDatabase extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        dbHelper=new DBHelper(this);
    }
    public void OnClickShow(View v){
        String result="";
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        Cursor cursor=database.query(DBHelper.TABLE_RESULT,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            int programIndex=cursor.getColumnIndex(DBHelper.KEY_PROGRAMS);
            int resultIndex=cursor.getColumnIndex(DBHelper.KEY_RESULT);
            do {
                result+="Программа: "+cursor.getString(programIndex)+"; Результат: "+cursor.getString(resultIndex)+"\n";
            } while (cursor.moveToNext());
        }
        TextView textView=(TextView)findViewById(R.id.showDatabase_textView);
        textView.setText(result);
    }
}