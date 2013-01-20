package com.example.healthcareplus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityDoctor extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_main);
        
        ImageView LogOff = (ImageView) findViewById(R.id.logoff_main);
        ImageView List = (ImageView) findViewById(R.id.list_selected);
        ImageView Search = (ImageView) findViewById(R.id.search_main);
        ImageView Calendar = (ImageView) findViewById(R.id.calendar);
        
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.doctor_notificacion,
       (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text2 = (TextView) layout.findViewById(R.id.text);
        
        
        text2.setText("Bienvenido Doctor Makubex");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 50, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        
        
        // Listening to register new account link
        LogOff.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
			}
		});
        
        List.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), DoctorList.class);
				startActivity(i);
			}
		});
        
        Search.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), DoctorSearch.class);
				startActivity(i);
			}
		});
        
        Calendar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), DoctorCalendar.class);
				startActivity(i);
			}
		});
        
        
    }
	
	
	
	

	
	

}
