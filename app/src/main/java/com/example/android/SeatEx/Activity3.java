package com.example.android.SeatEx;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {
    Button button,button1;
    Button[][] seats = new Button[13][73];
    seat[][] s = new seat[13][72];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        button = findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setBackgroundColor(Color.parseColor("#ff00ff"));
            }
        });
        /*for(int i=1;i<=12;i++)
        {
            for(int j=1;j<=72;j++)
            {
                seats[i][j]=findViewById(R.id.)
            }
        }*/
        s=(seat[][]) getIntent().getSerializableExtra("SeatDetails");
    }

    private void openDialog() {
        Dial dial = new Dial();
        dial.show(getSupportFragmentManager(),"Confirm");
    }
}
