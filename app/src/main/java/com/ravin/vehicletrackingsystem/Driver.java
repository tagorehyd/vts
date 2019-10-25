package com.ravin.vehicletrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Driver extends AppCompatActivity {
    EditText route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        route=findViewById(R.id.route);
    }

    public void Login(View view) {
        String data=route.getText().toString().trim();
        try{
            Intent intent = new Intent(getApplicationContext(), Transmit.class);
            intent.putExtra("route",data);
            startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No Such Bus or Route Found", Toast.LENGTH_SHORT).show();
        }
    }
}
