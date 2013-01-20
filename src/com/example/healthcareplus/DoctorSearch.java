package com.example.healthcareplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorSearch extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	overridePendingTransition(0,0);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_search);
        
        ImageView LogOut = (ImageView) findViewById(R.id.logoff_search);
        ImageView List = (ImageView) findViewById(R.id.icon_list);
        ImageView Calendar = (ImageView) findViewById(R.id.calendar);
        
        LogOut.setOnClickListener(new View.OnClickListener() {
			
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
        
        Calendar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), DoctorCalendar.class);
				startActivity(i);
			}
		});
        
        
        
    }
}