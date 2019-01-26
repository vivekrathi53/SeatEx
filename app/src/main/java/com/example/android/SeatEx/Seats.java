package com.example.android.SeatEx;

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

public class Seats extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    seat[][] s = new seat[13][73];
    String TrainNumber;
    String SeatNumber;
    String CoachNumber;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //seat[][] st = (seat[][])getIntent().getSerializableExtra("TrainDetails");
        TrainNumber = getIntent().getStringExtra("TrainNumber");
        SeatNumber = getIntent().getStringExtra("seatNumber");
        CoachNumber = getIntent().getStringExtra("coachNumber");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
     /**   for(int i=1;i<=12;i++)
        {
            for(int j=1;j<=72;j++)
            {
                databaseExpenses.child("Node1").child(TrainNumber).child("S"+Integer.toString(i)).child(Integer.toString(j)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        seat seatInfo = dataSnapshot.getValue(seat.class);
                        s[seatInfo.getCoach()][seatInfo.getSeat_number()]=seatInfo;
                        //System.out.println("Value Changed to "+seatInfo.getCoach());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }**/
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
        Fragment fragment=null;
        if (id == R.id.S1) {
            fragment = new S1();
            Bundle bundle = new Bundle();
            bundle.putString("trainNumber",TrainNumber);
            bundle.putString("seatNumber",SeatNumber);
            bundle.putString("coachNumber",CoachNumber);
           // System.out.println(s[1][1].getEmail().toString());;
            fragment.setArguments(bundle);
        } else if (id == R.id.S2) {
            fragment = new S2();
        } else if (id == R.id.S3) {
            fragment = new S3();
        } else if (id == R.id.S4) {
            fragment = new S4();
        } else if (id == R.id.S5) {
            fragment = new S5();
        } else if (id == R.id.S6) {
            fragment = new S6();
        }else if (id == R.id.S7) {
            fragment = new S7();
        } else if (id == R.id.S8) {
            fragment = new S8();
        } else if (id == R.id.S9) {
            fragment = new S9();
        } else if (id == R.id.S10) {
            fragment = new S10();
        } else if (id == R.id.S11) {
            fragment = new S11();
        } else if (id == R.id.S12) {
            fragment = new S12();
        }
        if(fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
