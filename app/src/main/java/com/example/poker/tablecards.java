package com.example.poker;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tablecards extends AppCompatActivity {

    math math = new math();

    Intent intent;
    String[] myCards;


    TextView pokerHands[] = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablecards);
        intent = getIntent();

        pokerHands[0] = findViewById(R.id.RFChance);
        pokerHands[1] = findViewById(R.id.SFChance);
        pokerHands[2] = findViewById(R.id.FKChance);
        pokerHands[3] = findViewById(R.id.FHChance);
        pokerHands[4] = findViewById(R.id.FlushChance);
        pokerHands[5] = findViewById(R.id.StraightChance);
        pokerHands[6] = findViewById(R.id.TKChance);
        pokerHands[7] = findViewById(R.id.TPChance);
        pokerHands[8] = findViewById(R.id.OPChance);
        pokerHands[9] = findViewById(R.id.HCText);


        String myCardsStr = intent.getStringExtra("myCardsStr");
        myCards = splitToNChar(myCardsStr, 2);

        updateText();
    }

    private double [][]handCalculator(){
        double [][]chances = new double [10][3];
        chances[0] = math.RoyalFlush(myCards);
        chances[1] = math.StraightFlush(myCards);
        chances[2] = math.FourOfKind(myCards);
        chances[3] = math.FullHouse(myCards);
        chances[4] = math.RoyalFlush(myCards);
        chances[5] = math.FullHouse(myCards);
        chances[6] = math.ThreeOfAKind(myCards);
        chances[7] = math.RoyalFlush(myCards);
        chances[8] = math.Pair(myCards);
        chances[9] = math.RoyalFlush(myCards);

        return chances;
    }

    private void updateText(){
        double [][]chances = handCalculator();
        double decimals = Math.pow(10,4);

        for (int i=0;i<10;i++){
            pokerHands[i].setText(String.valueOf(Math.floor(chances[i][2] * 100*decimals) / decimals)+"%");
        }
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
