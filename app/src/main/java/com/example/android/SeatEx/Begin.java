package com.example.android.SeatEx;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Calendar;
import java.util.Random;

public class Begin extends AppCompatActivity {
    MaterialSearchView materialSearchView;
    final String TAG = "check";
  //  seat[][] st=new seat[13][73];
    String[] list;
    String trainName;
    Button button,button1,buttonSubmit;
    int year,month,day;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference();
  //  TextView textView1,textView2,textView3,textView4;
    String seatNumber,coachNumber,Date,emaill;
    EditText editText1;
    EditText editText2,editText3,editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        editText1=findViewById(R.id.date);
        list= new String[]{"raj","gaurav"};
        materialSearchView=findViewById(R.id.mySearch);
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                trainName=query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
      for(int i=1;i<=12;i++)
        {
            for(int j=1;j<=72;j++)
            {
                Toast.makeText(Begin.this,"Hello",Toast.LENGTH_LONG).show();
                seat s = new seat(j%8,i,j,0,"Indore","Khandwa",generateRandom((int)((Math.random())*10)+1),(int)(Math.random()*70),(int)(Math.random()*2),"vivekrathi53@gmail.com");
                databaseExpenses.child("Node1").child(String.valueOf("3933")).child("S"+Integer.toString(i)).child(Integer.toString(j)).setValue(s);
            }
        }
        editText2=findViewById(R.id.email);
        editText3=findViewById(R.id.seatNumber);
        editText4=findViewById(R.id.coachNumber);
        button1=findViewById(R.id.dates);
        buttonSubmit = findViewById(R.id.submit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func();
            }
        });


    }
    public static String generateRandom(int n)
    {
        String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234";
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < n; i++) {
            int randIndex=rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }
    @Override
    protected void onStart() {
        super.onStart();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emaill=editText2.getText().toString();
                seatNumber=editText3.getText().toString();
                coachNumber=editText4.getText().toString();
                if(trainName!=null && emaill!=null && seatNumber!=null && coachNumber!=null)
                {
                    databaseExpenses.child("Node1").child(trainName).child(coachNumber).child(seatNumber).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            seat s = dataSnapshot.getValue(seat.class);
                            if(s!=null)
                            {
                                Toast.makeText(Begin.this,emaill,Toast.LENGTH_LONG).show();
                                if(emaill.equals(s.getEmail()))
                                {
                                    Toast.makeText(Begin.this,"Verified",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Begin.this,Seats.class);
                                    intent.putExtra("seatNumber",seatNumber);
                                    intent.putExtra("coachNumber",coachNumber);
                      //              intent.putExtra("TrainDetails",st);
                                    intent.putExtra("TrainNumber",trainName);
                                    startActivity(intent);
                                    s.setInterested(1);
                                    databaseExpenses.child("Node1").child(trainName).child(coachNumber).child(seatNumber).setValue(s);
                                }
                                else
                                {
                                    Toast.makeText(Begin.this,"Invalid Details",Toast.LENGTH_LONG).show();
                                }
                            }
                            else Toast.makeText(Begin.this,"NULL",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(Begin.this,"Some field is left vacant",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void func() {
        final Calendar c = Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editText1.setText(i2+"-"+(i1+1)+"-"+i);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        MenuItem item1 = menu.findItem(R.id.all_tabs);
        materialSearchView.setMenuItem(item);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(Begin.this,Activity3.class);
            //    intent.putExtra("tab_1",book);
            //    intent.putExtra("tab_2","nothing");
            //    intent.putExtra("tab_3","nothing");`
                startActivity(intent);
                return true;
            }
        });
        return true;
    }
}
