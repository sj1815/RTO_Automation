package com.example.nfc_rto_automation;

import com.example.connectivity.connectionManager;
import com.example.data.CarDetails_request;
import com.example.util.progressdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CarLocation extends Activity {

	Dialog dg;
	int resp;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_location);
		
		
		ImageButton btnmap=(ImageButton)findViewById(R.id.btnMap);
		btnmap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getCarLocation();
			}
		});
		
		Button btnGetDetails=(Button)findViewById(R.id.btnGetDetails);
		btnGetDetails.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				GetUserDetailsNew();
			}
		});
	}
	
	public void getCarLocation()
	{
		EditText txtcarno=(EditText)findViewById(R.id.editText1);
		String carno=txtcarno.getText().toString();
		if(carno.equals(""))
		{
			AlertDialog alert=new AlertDialog.Builder(this).create();
			alert.setTitle("Enter Car No.");
			alert.setMessage("Car No. Is Mandatory");
			alert.show();
		}
		else
		{
			CarDetails_request.SetCarNoPlate(carno);
			
			final connectionManager conn=new connectionManager();
			if(connectionManager.checkNetworkAvailable(this))
			{
				progressdialog dialog=new progressdialog();
				dg=dialog.createDialog(this);
				dg.show();
				
				Thread tthread=new Thread()
				{
					@Override
					public void run()
					{
						conn.GetCarLocation();
						resp=CarDetails_request.GetResult();
						hd.sendEmptyMessage(0);
					}
				};
				tthread.start();
			}
			else
			{
				Toast.makeText(this, "Sorry no network access.", 10).show();
			}
		}
		
	}
	
	public void GetUserDetailsNew()
	{
		EditText txtcarno=(EditText)findViewById(R.id.editText1);
		String carno=txtcarno.getText().toString();
		if(carno.equals(""))
		{
			AlertDialog alert=new AlertDialog.Builder(this).create();
			alert.setTitle("Enter Car No.");
			alert.setMessage("Car No. Is Mandatory");
			alert.show();
		}
		else
		{
			CarDetails_request.SetCarNoPlate(carno);
			
			final connectionManager conn=new connectionManager();
			if(connectionManager.checkNetworkAvailable(this))
			{
				progressdialog dialog=new progressdialog();
				dg=dialog.createDialog(this);
				dg.show();
				
				Thread tthread=new Thread()
				{
					@Override
					public void run()
					{
						conn.GetUserDetailsNew();
						resp=CarDetails_request.GetResult();
						hd2.sendEmptyMessage(0);
					}
				};
				tthread.start();
			}
			else
			{
				Toast.makeText(this, "Sorry no network access.", 10).show();
			}
		}
	}
	
	Handler hd=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			
			switch (resp) {
			case 0:
				
				Intent intent=new Intent(CarLocation.this,Map.class);
				startActivity(intent);	
				break;

			case 1:
				
				Toast.makeText(getApplicationContext(), "Invalid Car Plate No.", Toast.LENGTH_LONG).show();
				break;
			}
			
		}
	};
	
	Handler hd2=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			
			switch (resp) {
			case 0:
				
				Intent intent=new Intent(CarLocation.this,UserCarDetails.class);
				startActivity(intent);	
				break;

			case 1:
				
				Toast.makeText(getApplicationContext(), "Invalid Car Plate No.", Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.car_location, menu);
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
