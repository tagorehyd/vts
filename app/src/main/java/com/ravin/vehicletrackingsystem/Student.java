package com.ravin.vehicletrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Inet4Address;

public class Student extends AppCompatActivity {
EditText route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        route=findViewById(R.id.route);
    }
    public void Track(View view) {
        String data=route.getText().toString().trim();
        try{
            Intent intent = new Intent(getApplicationContext(), Mp.class);
            intent.putExtra("route",data);
            startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No Such Bus or Route Found", Toast.LENGTH_SHORT).show();
        }
    }
}
