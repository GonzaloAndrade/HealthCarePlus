package secretaria;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.healthcareplus.JSONParser;
import com.example.healthcareplus.MainActivityDoctor;
import com.example.healthcareplus.R;



import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddPatient extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputPac_Cedula;
	EditText inputPac_Nombres;
	EditText inputPac_Apellidos;
	EditText inputPac_Tipo_Sangre;
	EditText inputPac_Telefono;
	EditText inputPac_Edad;
	 EditText input_Date;  
	EditText input_Hora;
    Button mPickDate;    
    int mYear;    
    int mMonth;    
    int mDay;    
    static final int DATE_DIALOG_ID = 0;
    
    TextView tvDisplayTime;
	Button btnChangeTime;
	private int hour;
	private int minute;

	static final int TIME_DIALOG_ID = 999;


	// url to create new product
	private static String url_create_paciente = "http://www.ecuaconnect.com/ihm_android/crud/create_paciente.php";
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secretaria_add);

		// Edit Text
		inputPac_Cedula = (EditText) findViewById(R.id.pac_cedula);
		inputPac_Nombres = (EditText) findViewById(R.id.pac_nombres);
		inputPac_Apellidos = (EditText) findViewById(R.id.pac_apellidos);
		inputPac_Tipo_Sangre = (EditText) findViewById(R.id.pac_tipo_sangre);
		inputPac_Telefono = (EditText) findViewById(R.id.pac_telefono);
		inputPac_Edad = (EditText) findViewById(R.id.pac_edad);
		input_Date = (EditText) findViewById(R.id.dateDisplay);
		input_Hora = (EditText) findViewById(R.id.tvDate);
    	mPickDate = (Button) findViewById(R.id.pickDate); 
    	
    	// add a click listener to the button        
    	mPickDate.setOnClickListener(new View.OnClickListener() 
    		{            
    		public void onClick(View v) {                
    			showDialog(DATE_DIALOG_ID);            
    			}        
    		});        
    	// get the current date        
    	final Calendar c = Calendar.getInstance();        
    	mYear = c.get(Calendar.YEAR);        
    	mMonth = c.get(Calendar.MONTH);        
    	mDay = c.get(Calendar.DAY_OF_MONTH);        
    	// display the current date (this method is below)        
    	updateDisplay();
    	setCurrentTimeOnView();
		addListenerOnButton();


		// Create button
		ImageView btnCreatePaciente= (ImageView) findViewById(R.id.icon_create_paciente);

		// button click event
		btnCreatePaciente.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AddPatient.this);
			pDialog.setMessage("Guardando Paciente..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String pac_cedula = inputPac_Cedula.getText().toString();
			String pac_nombres = inputPac_Nombres.getText().toString();
			String pac_apellidos = inputPac_Apellidos.getText().toString();
			String pac_tipo_sangre = inputPac_Tipo_Sangre.getText().toString();
			String pac_telefono = inputPac_Telefono.getText().toString();
			String pac_edad = inputPac_Edad.getText().toString();
			String cit_fecha = input_Date.getText().toString();
			String cit_hora = input_Hora.getText().toString();
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pac_cedula", pac_cedula));
			params.add(new BasicNameValuePair("pac_nombres", pac_nombres));
			params.add(new BasicNameValuePair("pac_apellidos", pac_apellidos));
			params.add(new BasicNameValuePair("pac_tipo_sangre", pac_tipo_sangre));
			params.add(new BasicNameValuePair("pac_telefono", pac_telefono));
			params.add(new BasicNameValuePair("pac_edad", pac_edad));
			params.add(new BasicNameValuePair("cit_fecha",cit_fecha));
			params.add(new BasicNameValuePair("cit_hora",cit_hora));
			

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_paciente,"GET", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), MainActivityDoctor.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}


 
	
    
    // the callback received when the user "sets" the date in the dialog    
    private DatePickerDialog.OnDateSetListener mDateSetListener =            
    	new DatePickerDialog.OnDateSetListener() {                
    	public void onDateSet(DatePicker view, int year,                                       
    			int monthOfYear, int dayOfMonth) {                    
    		mYear = year;                    
    		mMonth = monthOfYear;                    
    		mDay = dayOfMonth;                    
    		updateDisplay();                
    		}            
    	};
    
    /** Called when the activity is first created. */
    
    
    
    // updates the date in the TextView    
    private void updateDisplay() {        
    	input_Date.setText(            
    			new StringBuilder()                    
    			// Month is 0 based so add 1                    
    			.append(mMonth + 1).append("-")                    
    			.append(mDay).append("-")                    
    			.append(mYear).append(" "));    
    }
    
    
    @Override
    protected Dialog onCreateDialog(int id) {    
    	switch (id) {   
    		case DATE_DIALOG_ID:        
    			return new DatePickerDialog(this,                    
    					mDateSetListener,                    
    					mYear, mMonth, mDay); 
    		case TIME_DIALOG_ID:
    			// set time picker as current time
    			return new TimePickerDialog(this, timePickerListener, hour, minute,
    					false);

    			}    
    	return null;
    	}
    

	
	
	

	// display current time
	public void setCurrentTimeOnView() {

		tvDisplayTime = (TextView) findViewById(R.id.tvDate);
		
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		// set current time into textview
		tvDisplayTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));

		// set current time into timepicker
		
	}

	public void addListenerOnButton() {

		btnChangeTime = (Button) findViewById(R.id.btnChangeDate);

		btnChangeTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

	}

	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// set current time into textview
			tvDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

			// set current time into timepicker
			
		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}

