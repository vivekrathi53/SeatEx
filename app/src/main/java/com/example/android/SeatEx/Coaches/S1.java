package com.example.android.SeatEx.Coaches;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.SeatEx.R;
import com.example.android.SeatEx.seat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by raj garg on 26-01-2019.
 */

public class S1 extends Fragment {
    Button[] buttons = new Button[75];
    seat[] seatDetails = new seat[73];
    int[] visited = new int[73];
    String TrainNumber;
    int MySeat=1,MyCoach=1;
    final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference();
    ArrayList<Pair<Integer,Integer>> interstedInYou = new ArrayList<>();
    ArrayList<Pair<Integer,Integer>> interstedIn = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s1,null);
    }
    private static final int[] BUTTON_IDS={R.id.seat1,R.id.seat2,R.id.seat3,R.id.seat4,R.id.seat5,R.id.seat6,R.id.seat6,R.id.seat7,R.id.seat8,R.id.seat9,R.id.seat10,R.id.seat11,R.id.seat12,R.id.seat13,R.id.seat14,R.id.seat15,R.id.seat16,R.id.seat17,R.id.seat18,R.id.seat19,R.id.seat20,R.id.seat21,R.id.seat22,R.id.seat23,R.id.seat24,R.id.seat25,R.id.seat26,R.id.seat27,R.id.seat28,R.id.seat29,
            R.id.seat30,R.id.seat31,R.id.seat32,R.id.seat33,R.id.seat34,R.id.seat35,R.id.seat36,R.id.seat37,R.id.seat38,R.id.seat39,R.id.seat40,R.id.seat41,R.id.seat42,R.id.seat43,R.id.seat44,
            R.id.seat45,R.id.seat46,R.id.seat47,R.id.seat48,R.id.seat49,R.id.seat50,R.id.seat51,R.id.seat52,R.id.seat53,R.id.seat54,R.id.seat55,R.id.seat56,R.id.seat57,R.id.seat58,R.id.seat59,R.id.seat60,
            R.id.seat61,R.id.seat62,R.id.seat63,R.id.seat64,R.id.seat65,R.id.seat66,R.id.seat67,R.id.seat68,R.id.seat69,R.id.seat70,R.id.seat71,R.id.seat72};
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i=1; i<=72; i++)
        {
            buttons[i]=view.findViewById(BUTTON_IDS[i-1]);
        }
        TrainNumber = getArguments().getString("trainNumber");
        MySeat = Integer.parseInt(getArguments().getString("seatNumber"));
        String x=getArguments().getString("coachNumber");
        MyCoach = Integer.parseInt(""+x.charAt(1));
        System.out.println(getArguments().getString("coachNumber"));
        for(int j=1;j<=72;j++)
        {
            System.out.println(TrainNumber);
            databaseExpenses.child("Node1").child(TrainNumber).child("S1").child(Integer.toString(j)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    seat seatInfo = dataSnapshot.getValue(seat.class);
                    seatDetails[seatInfo.getSeat_number()]=seatInfo;
                    System.out.println("Value Changed to "+seatInfo.getCoach());
                    int i = seatInfo.getSeat_number();
                    int in = seatDetails[i].getInterested();
                    if(i==MySeat&&1==MyCoach)
                    {
                        buttons[i].setBackgroundColor(Color.parseColor("#FFC107"));
                        // button color is yellow
                    }
                    else if(in==0&&visited[i]!=1)
                    {
                        buttons[i].setBackgroundColor(Color.parseColor("#FBE9E7"));
                        // button color is grey
                    }
                    else if(in==1&&visited[i]!=1)
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
        databaseExpenses.child("Node1").child(TrainNumber).child("S"+Integer.toString(MyCoach)).child(Integer.toString(MySeat)).addValueEventListener(new ValueEventListener() {
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
        for(int i=1; i<=72; i++)
        {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),((Button)view.findViewById(view.getId())).getText().toString(),Toast.LENGTH_LONG).show();}
            });
        }
    }
}
