package com.tejas.paras.bpibs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

public class Attendence extends AppCompatActivity {

    String date, course, year;
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "roll";

    String link;
    JSONArray peoples = null;
    BufferedReader bufferedReader;
    String result,data;
    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        list = (ListView) findViewById(R.id.listview);
        personList = new ArrayList<HashMap<String, String>>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("Date");
            course = extras.getString("Course");
            year = extras.getString("Year");
        }
        getData();



    }

    protected void showList() {

        try {

            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString("id");
                String name = c.getString("name");
                String roll = c.getString("roll");

                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put("roll", roll);
                persons.put("name", name);
                persons.put("id", id);

                personList.add(persons);
            }
            ListAdapter adapter = new SimpleAdapter(
                    Attendence.this, personList, R.layout.item_layout,
                    new String[]{"roll","id","name"},
                    new int[]{R.id.roll, R.id.id, R.id.name}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
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




