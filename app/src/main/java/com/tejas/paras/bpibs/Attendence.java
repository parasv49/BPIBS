package com.tejas.paras.bpibs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Attendence extends AppCompatActivity{

    String date, course, year,ID;
    String myJSON;

    private static final String TAG_RESULTS = "result";

    String link;
    JSONArray peoples = null;
    BufferedReader bufferedReader;
    String result,data;
    ArrayList<HashMap<String, String>> personList;
    Button b5;
    ListView list,listView;
    private DataModel dataModel;
    private ArrayList<DataModel> dataModels;
    private static CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        b5=(Button)findViewById(R.id.button5);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("Date");
            course = extras.getString("Course");
            year = extras.getString("Year");
        }
        getData();


        Toast.makeText(Attendence.this,"3",Toast.LENGTH_LONG).show();

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Attendence.this,"1."+view, Toast.LENGTH_SHORT).show();
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                 dataModel = dataModels.get(position);
                Toast.makeText(Attendence.this,"2.1",Toast.LENGTH_LONG).show();
                 ID=dataModel.getID();
                    Toast.makeText(Attendence.this,dataModel.getRoll(),Toast.LENGTH_LONG).show();

            }
        });
    }


    protected void showList() {
        dataModels= new ArrayList<>();
        list=(ListView)findViewById(R.id.listview);
        try {

            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);

                dataModels.add(new DataModel(c.getString("name"),c.getString("id"),  c.getString("roll")));
            }
            adapter = new CustomAdapter(dataModels, getApplicationContext());
            list.setAdapter(adapter);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                try {
                    data = "?date=" + URLEncoder.encode(date, "UTF-8");
                    data += "&course=" + URLEncoder.encode(course, "UTF-8");
                    data += "&year=" + URLEncoder.encode(year, "UTF-8");

                    link = "http://painnation.esy.es/attendence.php" + data;
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    result = bufferedReader.readLine();

                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }


}




