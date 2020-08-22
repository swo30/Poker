package com.example.poker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int numberOfCards = 0;
    int suit = 0;
    int selectedCard = -1;
    Drawable selectedCardLayer = null;

    ImageButton clubButtn;
    ImageButton diamondButtn;
    ImageButton heartButtn;
    ImageButton spadeButtn;

    ImageButton handButtons[] = new ImageButton[8];

    Button cardsNum[] = new Button[14];
    String myCards[] = new String[7]; //7 2-digit hex numbers representing the cards
    // 1 -> K & clubs,diamond,heart,spade
    // ie. A1 = 10 of clubs
    // ie. 14 = Ace of spade
    // ie. D3 = king of heart

    //The debugger
    Button DebugButton;
    Button nextButton;
    Button undoButton;
    Button resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DebugButton  = findViewById(R.id.DebugButton);
        nextButton   = findViewById(R.id.nextButton);
        undoButton   = findViewById(R.id.undoButton);
        resetButton   = findViewById(R.id.resetButton);

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
            resetSuitImages();
            for (int i = 1; i <= 13; i++) {
                cardsNum[i].setVisibility(View.INVISIBLE);
            }
            setImages();
        }
    }
    public void resetSuitImages(){
        int resID;
        resID = getResources().getIdentifier("clubimage", "drawable", "com.example.poker");
        clubButtn.setImageResource(resID);
        resID = getResources().getIdentifier("diamondimage", "drawable", "com.example.poker");
        diamondButtn.setImageResource(resID);
        resID = getResources().getIdentifier("heartimage", "drawable", "com.example.poker");
        heartButtn.setImageResource(resID);
        resID = getResources().getIdentifier("spadeimage", "drawable", "com.example.poker");
        spadeButtn.setImageResource(resID);
    }
    public void setImages(){
        if (myCards[numberOfCards - 1].equals("00")){
            Drawable[] layers = new Drawable[1];
            layers[0] = getResources().getDrawable(R.drawable.clubimage);
            LayerDrawable layerDraw = new LayerDrawable(layers);
            handButtons[numberOfCards].setImageDrawable(layerDraw);
        }else{
            Drawable[] layers = new Drawable[1];
            String imageString = "p" + myCards[numberOfCards-1].substring(0, 1) + suit;
            layers[0] =  getResources().getDrawable(getResources().getIdentifier(imageString, "drawable", "com.example.poker"));
            LayerDrawable layerDraw = new LayerDrawable(layers);
            handButtons[numberOfCards].setImageDrawable(layerDraw);
        }
    }

    public void select(int card){
        if (selectedCard != -1) {
            Drawable[] layers = new Drawable[1];
            layers[0] = selectedCardLayer;
            LayerDrawable layerDraw = new LayerDrawable(layers);
            handButtons[selectedCard+1].setImageDrawable(layerDraw);

        }
        selectedCard = card;
        Drawable[] layers = new Drawable[2];
        selectedCardLayer = handButtons[card+1].getDrawable();
        layers[0] = selectedCardLayer;
        layers[1] = getResources().getDrawable(R.drawable.select);
        LayerDrawable layerDraw = new LayerDrawable(layers);
        handButtons[card+1].setImageDrawable(layerDraw);


    }



    public void addListeners() {

        DebugButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        undoButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        clubButtn   .setOnClickListener(this);
        diamondButtn.setOnClickListener(this);
        heartButtn  .setOnClickListener(this);
        spadeButtn  .setOnClickListener(this);

        for (int i = 1; i <= 7; i++) {
            handButtons[i].setOnClickListener(this);
        }
        for (int i = 1; i <= 13; i++) {
            cardsNum[i].setOnClickListener(this);
        }
    }

    public void undo() {
        if (myCards[0] != "00") {
            myCards[numberOfCards - 1] = "00";
            setImages();
            numberOfCards -= 1;
        }
    }

    public void reset() {
        int resID = getResources().getIdentifier("empty", "drawable", "com.example.poker");
        for (int i = 0; i < 7; i++) {
            myCards[i] = "00";
            handButtons[i+1].setImageResource(resID);
        }
        numberOfCards = 0;
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

    public void addCard(int i){
        if (numberOfCards >=  myCards.length)
            numberOfCards = myCards.length-1;

        myCards[numberOfCards] = Integer.toHexString(i) + suit;
        numberOfCards++;
        numVisible(false);
        selectedCard =-1; // removes selected
    }

    @Override
    public void onClick(View v) {
        int resID;
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

            case R.id.resetButton:
                reset();
                break;

            case R.id.club:
                resID = getResources().getIdentifier("clubselect", "drawable", "com.example.poker");
                resetSuitImages();
                clubButtn.setImageResource(resID);
                numVisible(true);
                suit = 1;
                break;
            case R.id.diamond:
                resID = getResources().getIdentifier("diamondselect", "drawable", "com.example.poker");
                resetSuitImages();
                diamondButtn.setImageResource(resID);
                numVisible(true);
                suit = 2;
                break;
            case R.id.heart:
                resID = getResources().getIdentifier("heartselect", "drawable", "com.example.poker");
                resetSuitImages();
                heartButtn.setImageResource(resID);
                numVisible(true);
                suit = 3;
                break;
            case R.id.spade:
                resID = getResources().getIdentifier("spadeselect", "drawable", "com.example.poker");
                resetSuitImages();
                spadeButtn.setImageResource(resID);
                numVisible(true);
                suit = 4;
                break;

            case R.id.cardA:
                addCard(1);
                break;
            case R.id.card2:
                addCard(2);
                break;
            case R.id.card3:
                addCard(3);
                break;
            case R.id.card4:
                addCard(4);
                break;
            case R.id.card5:
                addCard(5);
                break;
            case R.id.card6:
                addCard(6);
                break;
            case R.id.card7:
                addCard(7);
                break;
            case R.id.card8:
                addCard(8);
                break;
            case R.id.card9:
                addCard(9);
                break;
            case R.id.card10:
                addCard(10);
                break;
            case R.id.cardJ:
                addCard(11);
                break;
            case R.id.cardQ:
                addCard(12);
                break;
            case R.id.cardK:
                addCard(13);
                break;

            case R.id.hand1:
                numberOfCards = 0;
                select(numberOfCards);
                break;
            case R.id.hand2:
                numberOfCards = 1;
               select(numberOfCards);
                break;
            case R.id.hand3:
                numberOfCards = 2;
              select(numberOfCards);
                break;
            case R.id.hand4:
                numberOfCards = 3;
              select(numberOfCards);
                break;
            case R.id.hand5:
                numberOfCards = 4;
               select(numberOfCards);
                break;
            case R.id.hand6:
                numberOfCards = 5;
               select(numberOfCards);
                break;
            case R.id.hand7:
                numberOfCards = 6;
               select(numberOfCards);
                break;
        }
    }
}