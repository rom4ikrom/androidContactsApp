package com.example.romanromanov.contactsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KeypadActivity extends AppCompatActivity {

    private TextView number;

    private Button btnDelete;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btnStar;
    private Button btnPound;
    private Button btnZero;

    private Button btnCall;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        number = findViewById(R.id.display_field);

        btnDelete = findViewById(R.id.buttonDelete);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        btnStar = findViewById(R.id.buttonStar);
        btnPound = findViewById(R.id.buttonPound);
        btnZero = findViewById(R.id.buttonZero);

        btnCall = findViewById(R.id.buttonCall);

        //add all buttons except zero, call and delete to array
        final Button btnArray[] = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btnPound};

        //disabled all buttons in the array on the start of activity
        for(int i = 0; i < btnArray.length; i++) {
            Button b = btnArray[i];
            b.setEnabled(false);
        }

        btnDelete.setEnabled(false);
        btnDelete.setVisibility(View.GONE);
        btnCall.setEnabled(false);

        //add text changed listener to the number field where actual number will be shown
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(number.getText().toString().length() > 0) {
                    btnDelete.setEnabled(true);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnZero.setEnabled(true);
                }

                if (number.getText().toString().contains("0")) {

                    for(int i = 0; i < btnArray.length; i++) {
                        Button b = btnArray[i];
                        b.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(number.getText().toString().length() == 0){
                    btnDelete.setEnabled(false);
                    btnDelete.setVisibility(View.GONE);
                    for(int i = 0; i < btnArray.length; i++) {
                        Button b = btnArray[i];
                        b.setEnabled(false);
                    }
                    btnZero.setEnabled(true);
                }

                if(number.getText().toString().length() < 11) {
                    btnCall.setEnabled(false);
                }

                if(number.getText().toString().length() == 11) {
                    btnCall.setEnabled(true);
                    for(int i = 0; i < btnArray.length; i++) {
                        Button b = btnArray[i];
                        b.setEnabled(false);
                    }
                    btnZero.setEnabled(false);
                    btnDelete.setEnabled(true);
                }
            }
        });
    }

    //method that will add the digit to number be clicking one of the button
    public void onClickCallBack(View view) {
        Button btnPressed = (Button) view;

        int btnPressedId = btnPressed.getId();

        switch(btnPressedId) {
            case R.id.button1:
                number.append("1");
                break;
            case R.id.button2:
                number.append("2");
                break;
            case R.id.button3:
                number.append("3");
                break;
            case R.id.button4:
                number.append("4");
                break;
            case R.id.button5:
                number.append("5");
                break;
            case R.id.button6:
                number.append("6");
                break;
            case R.id.button7:
                number.append("7");
                break;
            case R.id.button8:
                number.append("8");
                break;
            case R.id.button9:
                number.append("9");
                break;
            case R.id.buttonZero:
                number.append("0");
                break;
            case R.id.buttonStar:
                number.append("*");
                break;
            case R.id.buttonPound:
                number.append("#");
                break;
        }
    }

    //start the call activity and put the number
    public void onClickCall(View view) {
        Intent intent = new Intent(KeypadActivity.this, CallActivity.class);
        intent.putExtra("number", number.getText().toString());
        startActivity(intent);
    }

    //deletes the last digit from the number
    public void onClickDelete(View view) {
        String numberText = number.getText().toString();
        numberText = numberText.substring(0, numberText.length() -1);
        number.setText(numberText);
    }

    //back to previous activity with back button in the toolbar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
