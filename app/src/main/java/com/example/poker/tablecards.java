package com.example.poker;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class tablecards extends AppCompatActivity{

    math math = new math();
    Intent intent;
    String[] myCards;
    TextView pokerHands[] = new TextView[10];
    Button DebugButton;

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

        DebugButton  = findViewById(R.id.DebugButton);


        String myCardsStr = intent.getStringExtra("myCardsStr");
        myCards = splitToNChar(myCardsStr, 2);

        updateText();
        onPause();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                double[] chances;
                double decimals = Math.pow(10,4);
                chances = Straight(myCards);
                pokerHands[5].setText(String.valueOf(Math.floor(chances[2] * 100*decimals) / decimals)+"%");
            }
        }, 1);

    }

    public double[] Straight(String[] myCards)  {
        Arrays.sort(myCards, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                Integer int1 = Integer.parseInt(str1,16);
                Integer int2 = Integer.parseInt(str2,16);
                return int1.compareTo(int2);
            }
        });
        if (myCards.length == 0) {
            return new double[] {0.048243,0.048243,0.048243};
        }
        ArrayList <int[]> list;
        try {
            InputStream input = getAssets().open("hexStraight.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int count = 0;
            int cardsDeployed = myCards.length;
            int sumOfChances = 0;
            Boolean result;
            int chunk_size = 6454272/12; // 537856
            for(int chunk=1; chunk<=12;chunk++){
                list = new ArrayList<>();
                count = 0;
                while (count < chunk_size){
                    line = reader.readLine();
                    list.add(methodasd(myCards));
                    count++;
                }
//                sumOfChances += math.Straight(myCards, list);
                for (int[] card : list) {
//                    System.out.println(Arrays.toString(card));
                    result = math.checkCard(myCards, card);
                    if (result == null) {
                        break;
                    } else {
                        if (result)
                            sumOfChances++;
                    }
                }
            }
            return new double[] {sumOfChances,math.comb(52-cardsDeployed,7-cardsDeployed),((sumOfChances)/math.comb(52-cardsDeployed,7-cardsDeployed))};
        }
        catch(Exception e){
            System.out.println("ERROR ASDFKLMKLADFSJNMJKGBNFDSJNKGDFSJNKMLDSG");
            System.out.println(e);
            return new double[] {0,0,0};
        }
    }

    private static int[] methodasd(String[] cards) {
        int[] newint = new int[cards.length];
        for(int i =0; i<cards.length; i++) {
            newint[i] = Integer.parseInt(cards[i]);
        }
        return newint;
    }

    private double [][]handCalculator()  {
        double [][]chances = new double [9][3];
        chances[0] = math.RoyalFlush(myCards);
        chances[1] = math.StraightFlush(myCards);
        chances[2] = math.FourOfKind(myCards);
        chances[3] = math.FullHouse(myCards);
        chances[4] = math.Flush(myCards);
        chances[5] = new double[] {0,0,0}; //Straight(myCards);
        chances[6] = math.ThreeOfAKind(myCards);
        chances[7] = math.TwoPair(myCards);
        chances[8] = math.Pair(myCards);

        return chances;
    }

    private void updateText(){
        double [][]chances = handCalculator();
        double decimals = Math.pow(10,4);

        for (int i=0;i<chances.length;i++){
            if (Math.floor(chances[i][2]*10)>0) pokerHands[i].setText(String.valueOf(Math.floor(chances[i][2] * 100*(decimals/10))/(decimals/10))+"%");
            else pokerHands[i].setText(String.valueOf(Math.floor(chances[i][2] * 100*decimals) / decimals)+"%");
        }
        pokerHands[5].setText("Calculating.");

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
