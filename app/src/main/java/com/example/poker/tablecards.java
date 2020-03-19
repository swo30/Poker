package com.example.poker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class tablecards extends AppCompatActivity {

    math math = new math();

    Intent intent;
    String[] myCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablecards);

        intent = getIntent();
        String myCardsStr = intent.getStringExtra("myCardsStr");

        myCards = splitToNChar(myCardsStr, 2);
    }

    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();
        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
}
