package com.tejas.paras.bpibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText id,pass;
    Button login;
    String id1,pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=(EditText)findViewById(R.id.ID);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.button2);
    }

    public void login2(View v)
    {
        id1=id.getText().toString();
        pass1=pass.getText().toString();
        Toast.makeText(this, "Signing up...", Toast.LENGTH_SHORT).show();
        new LoginActivity(this).execute(id1,pass1);

    }
}
