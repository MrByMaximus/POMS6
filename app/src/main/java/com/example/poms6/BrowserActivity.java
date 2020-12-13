package com.example.poms6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BrowserActivity extends AppCompatActivity {
    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        url=(EditText)findViewById(R.id.inputURL);
    }
    public void OpenBrowser(View v){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString()));
        startActivity(intent);
    }
}