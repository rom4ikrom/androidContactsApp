package com.example.romanromanov.contactsapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CallActivity extends AppCompatActivity {

    private TextView number;
    private TextView time_view;
    private int seconds = 0;
    private boolean running = true;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        number = findViewById(R.id.number_view);
        time_view = findViewById(R.id.time_view);

        number.setText(getIntent().getStringExtra("number"));

        //run the timer
        runTimer();

    }

    private void runTimer() {
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                time_view.setText(time);
                if(running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickEndCall(View view) {
        running = false;
        this.finish();
    }

}
