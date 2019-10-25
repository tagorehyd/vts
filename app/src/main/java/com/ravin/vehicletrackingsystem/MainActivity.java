package com.ravin.vehicletrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Student(View view) {
        Intent intent=new Intent(getApplicationContext(),Student.class);
        startActivity(intent);finish();
    }

    public void Driver(View view) {
        Intent intent=new Intent(getApplicationContext(),Driver.class);
        startActivity(intent);finish();

    }
}
