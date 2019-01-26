package com.example.android.SeatEx;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

/**
 * Created by raj garg on 26-01-2019.
 */

public class seat implements Serializable{
    private int type;
    /*store category of seat
    1,4 for Lower;
    2,5 for Middle;
    3,6 for Upper,
    7 for Side Lower;
    8 for Side Upper;*/
    private int coach;
    private int seat_number;
    private int interested;
    private String source;
    private String destination;
    private String username;
    private int age;
    private int gender;// 0 for male and 1 for female
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    ArrayList<Pair<Integer,Integer>> interstedIn = new ArrayList<>();// first Integer is coach number and second Integer is seat no.
    ArrayList<Pair<Integer,Integer> > interstedInYou = new ArrayList<>();

    public ArrayList<Pair<Integer, Integer> > getInterstedIn() {
        return interstedIn;
    }

    public void setInterstedIn(ArrayList<Pair<Integer, Integer>> interstedIn) {
        this.interstedIn = interstedIn;
    }
    public void addInterestedPerson(Pair<Integer,Integer> p)
    {
        interstedInYou.add(p);
    }
    public void addInterestedSeat(Pair<Integer,Integer> p)
    {
        interstedIn.add(p);
    }
    public void removeInterestedPerson(Pair<Integer,Integer> p)
    {
        interstedInYou.remove(p);
    }
    public void removeInterestedSeat(Pair<Integer,Integer> p)
    {
        interstedIn.remove(p);
    }
    public ArrayList<Pair<Integer, Integer>> getInterstedInYou() {
        return interstedInYou;
    }

    public void setInterstedInYou(ArrayList<Pair<Integer, Integer>> interstedInYou) {
        this.interstedInYou = interstedInYou;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public int getInterested() {
        return interested;
    }

    public void setInterested(int interested) {
        this.interested = interested;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public seat(int type, int coach, int seat_number, int interested, String source, String destination, String username, int age, int gender,String Email) {
        this.type = type;
        this.coach = coach;
        this.seat_number = seat_number;
        this.interested = interested;
        this.source = source;
        this.destination = destination;
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.Email=Email;
    }

    seat(int coach, int seat_number)
    {
        this.type=1+seat_number%8;
        this.coach=coach;
        this.seat_number=seat_number;
        source=NULL;
        destination=NULL;
        interested=-1;
        username=NULL;
    }
    seat()
    {}

}