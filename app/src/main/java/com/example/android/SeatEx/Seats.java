package com.example.android.SeatEx;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.SeatEx.Coaches.S1;
import com.example.android.SeatEx.Coaches.S10;
import com.example.android.SeatEx.Coaches.S11;
import com.example.android.SeatEx.Coaches.S12;
import com.example.android.SeatEx.Coaches.S2;
import com.example.android.SeatEx.Coaches.S3;
import com.example.android.SeatEx.Coaches.S4;
import com.example.android.SeatEx.Coaches.S5;
import com.example.android.SeatEx.Coaches.S6;
import com.example.android.SeatEx.Coaches.S7;
import com.example.android.SeatEx.Coaches.S8;
import com.example.android.SeatEx.Coaches.S9;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Seats extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    seat[][] s = new seat[13][73];
    Button[] buttons = new Button[75];
    seat[] seatDetails = new seat[73];
    int[] visited = new int[73];
    int TrainNumber;
    int MyCoach;
    int MySeat;
    TextView curCoachDisplay;
    String curCoach="S1";
    private static final int[] BUTTON_IDS={R.id.seat1,R.id.seat2,R.id.seat3,R.id.seat4,R.id.seat5,R.id.seat6,R.id.seat7,R.id.seat8,R.id.seat9,R.id.seat10,R.id.seat11,R.id.seat12,R.id.seat13,R.id.seat14,R.id.seat15,R.id.seat16,R.id.seat17,R.id.seat18,R.id.seat19,R.id.seat20,R.id.seat21,R.id.seat22,R.id.seat23,R.id.seat24,R.id.seat25,R.id.seat26,R.id.seat27,R.id.seat28,R.id.seat29,
            R.id.seat30,R.id.seat31,R.id.seat32,R.id.seat33,R.id.seat34,R.id.seat35,R.id.seat36,R.id.seat37,R.id.seat38,R.id.seat39,R.id.seat40,R.id.seat41,R.id.seat42,R.id.seat43,R.id.seat44,
            R.id.seat45,R.id.seat46,R.id.seat47,R.id.seat48,R.id.seat49,R.id.seat50,R.id.seat51,R.id.seat52,R.id.seat53,R.id.seat54,R.id.seat55,R.id.seat56,R.id.seat57,R.id.seat58,R.id.seat59,R.id.seat60,
            R.id.seat61,R.id.seat62,R.id.seat63,R.id.seat64,R.id.seat65,R.id.seat66,R.id.seat67,R.id.seat68,R.id.seat69,R.id.seat70,R.id.seat71,R.id.seat72};

    ArrayList<Pair<Integer,Integer>> interstedInYou = new ArrayList<>();
    ArrayList<Pair<Integer,Integer>> interstedIn = new ArrayList<>();
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        curCoachDisplay = findViewById(R.id.Coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TrainNumber = Integer.parseInt(getIntent().getStringExtra("TrainNumber"));
        MyCoach = Integer.parseInt(getIntent().getStringExtra("coachNumber"));
        MySeat = Integer.parseInt(getIntent().getStringExtra("seatNumber"));

        //seat[][] st = (seat[][])getIntent().getSerializableExtra("TrainDetails");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        for(int i=1; i<=72; i++)
        {
            buttons[i]=findViewById(BUTTON_IDS[i-1]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    public void refresh()
    {
        for(int j=1;j<=72;j++)
        {
            //System.out.println(TrainNumber);
            databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(j)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    seat seatInfo = dataSnapshot.getValue(seat.class);
                    seatDetails[seatInfo.getSeat_number()]=seatInfo;
                    System.out.println("Value Changed to "+seatInfo.getCoach());
                    int i = seatInfo.getSeat_number();
                    int in = seatDetails[i].getInterested();
                    if(in==-1)
                    {
                        buttons[i].setBackgroundColor(Color.parseColor("#2196f3"));
                    }
                    else if(i==MySeat&&curCoach.equals("S"+MyCoach))
                    {
                        buttons[i].setBackgroundColor(Color.parseColor("#FFC107"));
                        // button color is yellow
                    }
                    else if(in==0&&visited[i]==0)
                    {
                        buttons[i].setBackgroundColor(Color.parseColor("#FBE9E7"));
                        // button color is grey
                    }
                    else if(in==1&&visited[i]==0)
                    {
                        // button color is green
                        buttons[i].setBackgroundColor(Color.parseColor("#00C853"));
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                seat temp = dataSnapshot.getValue(seat.class);
                if(temp==null) System.out.println("Crashed");
                interstedInYou = temp.getInterstedInYou();
                for(int i=0;i<interstedInYou.size();i++)
                {
                    if(interstedInYou.get(i).first==1)
                    {
                        visited[interstedInYou.get(i).second]=1;
                        buttons[interstedInYou.get(i).second].setBackgroundColor(Color.parseColor("#EF5350"));
                        // button color is pink
                    }
                }
                for(int i=0;i<interstedIn.size();i++)
                {
                    if(interstedIn.get(i).first==1)
                    {
                        visited[interstedIn.get(i).second]=2;
                        buttons[interstedIn.get(i).second].setBackgroundColor(Color.parseColor("#3949AB"));
                        // button color is blue
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        for(int i=1;i<=72;i++)
        {
            buttons[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // if pink then swap their seats and make them grey and make both array list empty
                    // if green then change to blue and add to interestedIn arraylist
                    // if grey then no operation and show then prompt
                    int id = view.getId();

                    final Button thisSeat = view.findViewById(id);
                    final int seatNumber=Integer.parseInt(thisSeat.getText().toString());
                    if(visited[seatNumber]==1)// PINK
                    {
                        // show dialog box and notification and timer
                        // make them grey
                        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                seat temp = dataSnapshot.getValue(seat.class);//user seat
                                temp.setInterstedInYou(null);
                                temp.setInterstedIn(null);
                                temp.setInterested(0);
                                databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).setValue(temp);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(seatNumber)).addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                seat temp = dataSnapshot.getValue(seat.class);//user seat
                                temp.setInterstedInYou(null);
                                temp.setInterstedIn(null);
                                temp.setInterested(0);
                                // make their color grey here or above :P
                                visited[seatNumber]=0;
                                databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(seatNumber)).setValue(temp);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        // delete their presence from all nodes of train
                        for(int i=1;i<=12;i++)
                        {
                            for(int j=1;j<=72;j++)
                            {
                                databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(j)).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        seat temp = dataSnapshot.getValue(seat.class);
                                        ArrayList<Pair<Integer,Integer> > temp2 = temp.getInterstedInYou();
                                        temp2.remove(new Pair(MyCoach,MySeat));
                                        temp2.remove(new Pair(curCoach,seatNumber));
                                        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(temp.getCoach())).child(Integer.toString(temp.getCoach())).setValue(temp);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                        }
                        finishActivity(1);
                    }
                    else if(visited[seatNumber]==2)// blue
                    {
                        // No Changes
                    }
                    else if(seatDetails[seatNumber].getInterested()==1&&visited[seatNumber]==0)// green color
                    {
                        // add dialog box code here
                        // then add following code
                        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                seat temp = dataSnapshot.getValue(seat.class);//user seat
                                ArrayList<Pair<Integer,Integer>> temp2 = temp.getInterstedIn();
                                temp2.add(new Pair(Integer.parseInt(""+curCoach.charAt(1)),seatNumber));
                                temp.setInterstedIn(temp2);
                                visited[seatNumber]=2;
                                databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).setValue(temp);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(seatNumber)).addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                seat temp = dataSnapshot.getValue(seat.class);//user seat
                                ArrayList<Pair<Integer,Integer>> temp2 = temp.getInterstedInYou();
                                temp2.add(new Pair(MyCoach,MySeat));
                                temp.setInterstedInYou(temp2);
                                s[][seatNumber]
                                databaseExpenses.child("Node1").child(Integer.toString(TrainNumber)).child(curCoach).child(Integer.toString(seatNumber)).setValue(temp);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }
                    else if(seatDetails[seatNumber].getInterested()==0)// grey color
                    {
                        // No Changes
                    }
                }



            });
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.S1) {
            curCoach="S1";
            curCoachDisplay.setText("S1");
            refresh();
        } else if (id == R.id.S2) {
            curCoach="S2";
            curCoachDisplay.setText("S2");
            refresh();
        } else if (id == R.id.S3) {
            curCoach="S3";
            curCoachDisplay.setText("S3");
            refresh();
        } else if (id == R.id.S4) {
            curCoach="S4";
            curCoachDisplay.setText("S4");
            refresh();
        } else if (id == R.id.S5) {
            curCoach="S5";
            refresh();
        } else if (id == R.id.S6) {
            curCoach="S6";
            refresh();
        }else if (id == R.id.S7) {
            curCoach="S7";
            refresh();
        } else if (id == R.id.S8) {
            curCoach="S8";
            refresh();
        } else if (id == R.id.S9) {
            curCoach="S9";
            refresh();
        } else if (id == R.id.S10) {
            curCoach="S10";
            refresh();
        } else if (id == R.id.S11) {
            curCoach="S11";
            refresh();
        } else if (id == R.id.S12) {
            curCoach="S12";
            refresh();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
