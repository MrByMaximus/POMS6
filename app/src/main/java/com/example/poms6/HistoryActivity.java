package com.example.poms6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();

        ListView mListView =(ListView)findViewById(R.id.listHistory);

        historyAdapter adapter=new historyAdapter(this,R.layout.history_item,intent.<historyItem>getParcelableArrayListExtra("history"));
        mListView.setAdapter(adapter);
    }
}