package com.tejas.paras.bpibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class teacher_home extends AppCompatActivity {

    private SimpleDateFormat mFormatter = new SimpleDateFormat("dd-MM-yyyy");
    private TextView tv5;
    private Spinner sp1,sp2;
    private Button b4;

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {

            tv5.setText(mFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            Toast.makeText(teacher_home.this,
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };
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
        year.add("1");
        year.add("2");
        year.add("3");


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, year);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp2.setAdapter(dataAdapter2);

// textView is the TextView view that should display it
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        //.setIs24HourTime(true)
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
                        //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });
    }
    public void attendence(View v)
    {
        String date=tv5.getText().toString();
        String course=sp1.getSelectedItem().toString();
        String year=sp2.getSelectedItem().toString();
        Intent in=new Intent(teacher_home.this,Attendence.class);
        in.putExtra("Date",date);
        in.putExtra("Course",course);
        in.putExtra("Year",year);
        startActivity(in);

    }

}
