package com.example.healthcareplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorList extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_main);
        
        ImageView LogOut = (ImageView) findViewById(R.id.logoff_main);
        LogOut.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
			}
		});
        
        ImageView Search = (ImageView) findViewById(R.id.search_main);
        ImageView Calendar = (ImageView) findViewById(R.id.calendar);
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