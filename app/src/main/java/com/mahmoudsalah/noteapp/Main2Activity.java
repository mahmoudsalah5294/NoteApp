package com.mahmoudsalah.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Main2Activity extends AppCompatActivity {
EditText editText2;
    int noteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText2 = findViewById(R.id.editText2);
        Intent intent = getIntent();
          noteid = intent.getIntExtra("position",-1);

        if (noteid != -1){
            editText2.setText(MainActivity.listArray.get(noteid));
        }else{
            MainActivity.listArray.add("");
            noteid = MainActivity.listArray.size()-1;
        }
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             MainActivity.listArray.set(noteid, String.valueOf(s));
             MainActivity.adapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getSharedPreferences("com.mahmoudsalah.noteapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.listArray);
                sharedPreferences.edit().putStringSet("notes",set).apply();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
