package com.tejas.paras.bpibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class teacher_home extends AppCompatActivity {

    private TextView tv5;
    private Spinner sp1,sp2;
    private Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        tv5=(TextView)findViewById(R.id.textView5);
        sp1=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        b4=(Button)findViewById(R.id.button4);

        List<String> course = new ArrayList<>();
        course.add("--Select--");
        course.add("MCA");
        course.add("MBA");
        course.add("BBA");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, course);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp1.setAdapter(dataAdapter);

        List<String> year = new ArrayList<>();
        year.add("--Select--");
        year.add("1st");
        year.add("2nd");
        year.add("3rd");


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, year);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp2.setAdapter(dataAdapter2);

// textView is the TextView view that should display it
        tv5.setText("yo");
    }
    public void attendence(View v)
    {
        Intent in=new Intent(teacher_home.this,Attendence.class);
        startActivity(in);
    }

}
