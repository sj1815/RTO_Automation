package com.example.nfc_rto_automation;

import com.example.data.CarDetails_request;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserCarDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_car_details);
		
		
		
		TextView lblUsername=(TextView)findViewById(R.id.lblUsername);
		TextView lblAddress=(TextView)findViewById(R.id.lblAddress);
		TextView lblPhoneNumber=(TextView)findViewById(R.id.lblPhoneNumber);
		TextView lblEmailId=(TextView)findViewById(R.id.lblEmailId);
		TextView lblDOB=(TextView)findViewById(R.id.lblDOB);
		
		lblUsername.setText(CarDetails_request.GetFullName());
		lblAddress.setText(CarDetails_request.GetAddress());
		lblPhoneNumber.setText(CarDetails_request.GetPhoneNumber());
		lblEmailId.setText(CarDetails_request.GetEmailId());
		lblDOB.setText(CarDetails_request.GetDOB());
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_car_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
