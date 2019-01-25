package com.example.android.SeatEx;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Calendar;

public class Begin extends AppCompatActivity {
    MaterialSearchView materialSearchView;
    final String TAG = "check";
    String[] list;
    String trainName;
    Button button,button1;
    int year,month,day;
    TextView textView1,textView2,textView3,textView4;
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        String seatNumber;
        String coachNumber;
        String email;
        EditText editText2,editText3,editText4;
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
        button1=findViewById(R.id.dates);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func();
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
            //    intent.putExtra("tab_3","nothing");
                startActivity(intent);
                return true;
            }
        });
        return true;
    }
}
