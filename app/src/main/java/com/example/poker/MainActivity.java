package com.example.poker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int numberOfCards = 0;
    int suit = 0;

    ImageButton clubButtn;
    ImageButton diamondButtn;
    ImageButton heartButtn;
    ImageButton spadeButtn;

    ImageButton handButtons[] = new ImageButton[8];

    Button cardsNum[] = new Button[14];
    String myCards[] = new String[7]; //7 hex number representing the cards
    // 1 -> K & clubs,diamond,heart,spade
    // ie. A1 = 10 of clubs
    // ie. 14 = Ace of spade
    // ie. D3 = king of heart

    //The debugger
    Button DebugButton;
    Button nextButton;
    Button undoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DebugButton  = findViewById(R.id.DebugButton);
        nextButton   = findViewById(R.id.nextButton);
        undoButton   = findViewById(R.id.undoButton);

        clubButtn    = findViewById(R.id.club);
        diamondButtn = findViewById(R.id.diamond);
        heartButtn   = findViewById(R.id.heart);
        spadeButtn   = findViewById(R.id.spade);

        for (int i=0; i<7; i++){
            myCards[i] = "00";
        }


        cardsNum[0]  = null;
        cardsNum[1]  = findViewById(R.id.cardA);
        cardsNum[2]  = findViewById(R.id.card2);
        cardsNum[3]  = findViewById(R.id.card3);
        cardsNum[4]  = findViewById(R.id.card4);
        cardsNum[5]  = findViewById(R.id.card5);
        cardsNum[6]  = findViewById(R.id.card6);
        cardsNum[7]  = findViewById(R.id.card7);
        cardsNum[8]  = findViewById(R.id.card8);
        cardsNum[9]  = findViewById(R.id.card9);
        cardsNum[10] = findViewById(R.id.card10);
        cardsNum[11] = findViewById(R.id.cardJ);
        cardsNum[12] = findViewById(R.id.cardQ);
        cardsNum[13] = findViewById(R.id.cardK);

        handButtons[0]  = null;
        handButtons[1]  = findViewById(R.id.hand1);
        handButtons[2]  = findViewById(R.id.hand2);
        handButtons[3]  = findViewById(R.id.hand3);
        handButtons[4]  = findViewById(R.id.hand4);
        handButtons[5]  = findViewById(R.id.hand5);
        handButtons[6]  = findViewById(R.id.hand6);
        handButtons[7]  = findViewById(R.id.hand7);

        addListeners();
    }

    public void numVisible(boolean visible) {
        if (visible){
            for (int i = 1; i <= 13; i++) {
                cardsNum[i].setVisibility(View.VISIBLE);
            }
        }else{
            for (int i = 1; i <= 13; i++) {
                cardsNum[i].setVisibility(View.INVISIBLE);
            }
            setImages();
        }
    }

    public void setImages(){
        if (myCards[numberOfCards-1] == "00"){
            int resID = getResources().getIdentifier("empty", "drawable", "com.example.poker");
            handButtons[numberOfCards].setImageResource(resID);
        }else{
            String imageString = "p" + myCards[numberOfCards-1].substring(0, 1) + suit;
            int resID = getResources().getIdentifier(imageString, "drawable", "com.example.poker");
            handButtons[numberOfCards].setImageResource(resID);
        }
    }

    public void addListeners() {

        DebugButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        undoButton.setOnClickListener(this);


        clubButtn   .setOnClickListener(this);
        diamondButtn.setOnClickListener(this);
        heartButtn  .setOnClickListener(this);
        spadeButtn  .setOnClickListener(this);

        for (int i = 1; i <= 13; i++) {
            cardsNum[i].setOnClickListener(this);
        }
    }

    public void goToTableCards(){
        Intent intent = new Intent(this, tablecards.class);
        String myCardsStr = "";
        for (int i=0;i<7;i++){
            if (myCards[i]!="00") myCardsStr += myCards[i];
        }
        intent.putExtra("myCardsStr",myCardsStr);
        startActivity(intent);
    }

    public void undo(){
        if (myCards[0] != "00") {
            for (int i = 0; i < 7; i++) {
                if (myCards[i] == "00") myCards[i - 1] = "00";
            }
            setImages();
            numberOfCards-=1;
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.DebugButton:
                //Toast.makeText(this,Integer.toString(numberOfCards),Toast.LENGTH_SHORT).show();
                for (int i=0;i<7;i++) {
                    System.out.print(myCards[i]);
                    System.out.print(" ");
                }
                break;
            case R.id.nextButton:
                goToTableCards();
                break;

            case R.id.undoButton:
                undo();
                break;

            case R.id.club:
                numVisible(true);
                suit = 1;
                break;
            case R.id.diamond:
                numVisible(true);
                suit = 2;
                break;
            case R.id.heart:
                numVisible(true);
                suit = 3;
                break;
            case R.id.spade:
                numVisible(true);
                suit = 4;
                break;

            case R.id.cardA:
                myCards[numberOfCards] = Integer.toHexString(1) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card2:
                myCards[numberOfCards] = Integer.toHexString(2) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card3:
                myCards[numberOfCards] = Integer.toHexString(3) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card4:
                myCards[numberOfCards] = Integer.toHexString(4) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card5:
                myCards[numberOfCards] = Integer.toHexString(5) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card6:
                myCards[numberOfCards] = Integer.toHexString(6) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card7:
                myCards[numberOfCards] = Integer.toHexString(7) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card8:
                myCards[numberOfCards] = Integer.toHexString(8) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card9:
                myCards[numberOfCards] = Integer.toHexString(9) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.card10:
                myCards[numberOfCards] = Integer.toHexString(10) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.cardJ:
                myCards[numberOfCards] = Integer.toHexString(11) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.cardQ:
                myCards[numberOfCards] = Integer.toHexString(12) + suit;
                numberOfCards++;
                numVisible(false);
                break;
            case R.id.cardK:
                myCards[numberOfCards] = Integer.toHexString(13) + suit;
                numberOfCards++;
                numVisible(false);
                break;
        }
    }
}