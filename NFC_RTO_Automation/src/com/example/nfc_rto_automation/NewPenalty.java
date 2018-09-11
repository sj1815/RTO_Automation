package com.example.nfc_rto_automation;

import java.util.ArrayList;

import com.example.connectivity.connectionManager;
import com.example.data.filloffences_request;
import com.example.data.newpenalty_request;
import com.example.data.offencedetails_request;
import com.example.data.policedetails_request;
import com.example.data.userdetails_request;
import com.example.nfc_rto_automation.R;
import com.example.util.progressdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewPenalty extends Activity {

	Dialog dg;
	int resp;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_penalty);
		
		TextView txtpoliceid=(TextView)findViewById(R.id.textView5);
		TextView txtuserid=(TextView)findViewById(R.id.textView6);
		TextView txtuserfullname=(TextView)findViewById(R.id.textView10);
		
		String policeid=policedetails_request.GetPoliceId();
		String fullname=userdetails_request.GetUserFullName();
		String userid=userdetails_request.GetUserId();
		txtpoliceid.setText(policeid);
		txtuserid.setText(userid);
		txtuserfullname.setText(fullname);
		
		ArrayList<String> offenceslist=new ArrayList<String>();
		offenceslist=filloffences_request.getOffencelist();
		
		final Spinner spinneroffence=(Spinner)findViewById(R.id.spinner1);
		
		spinneroffence.setAdapter(new ArrayAdapter<String>(NewPenalty.this,
				android.R.layout.simple_spinner_dropdown_item,offenceslist));
		
		
		spinneroffence.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				String offencename=String.valueOf(spinneroffence.getSelectedItem());
				offencedetails_request.SetOffenceName(offencename);
				GetPenaltyAmount();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnsubmit=(Button)findViewById(R.id.button1);
		btnsubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				InsertNewPenalty();
			}
		});
		
	}
	
	public void InsertNewPenalty()
	{
		final EditText txtlocation=(EditText)findViewById(R.id.editText1);
		String location=txtlocation.getText().toString();
		
		if(location.equals(""))
		{
			AlertDialog alert=new AlertDialog.Builder(this).create();
			alert.setTitle("Enter Location");
			alert.setMessage("Location is Mandatory");
			alert.show();
		}
		else
		{
			newpenalty_request.SetLocation(location);
			
			final connectionManager conn=new connectionManager();
			progressdialog dialog=new progressdialog();
			dg=dialog.createDialog(this);
			dg.show();
			
			Thread tthread1=new Thread()
			{
				@Override
				public void run()
				{
					if(conn.CreateNewPenalty())
					{
						resp=0;
					}
					else
					{
						resp=1;
					}
					hd1.sendEmptyMessage(0);
				}
			};
			tthread1.start();
		}
	}
	
	public void GetPenaltyAmount()
	{
		final connectionManager conn=new connectionManager();
		progressdialog dialog=new progressdialog();
		dg=dialog.createDialog(this);
		dg.show();
		
		Thread tthread=new Thread()
		{
			@Override
			public void run()
			{
				if(conn.GetPenaltyAmount())
				{
					resp=0;
				}
				hd.sendEmptyMessage(0);
			}
		};
		tthread.start();
	}
	
	
	public Handler hd=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			switch (resp) {
			case 0:
				TextView txtpenaltyamount=(TextView)findViewById(R.id.textView7);
				String amount=offencedetails_request.GetPenaltyAmount();
				txtpenaltyamount.setText(offencedetails_request.GetPenaltyAmount());
				
				break;

			default:
				break;
			}
		}
	};
	
	public Handler hd1=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			switch (resp)
			{
			case 0:
				Toast.makeText(getApplicationContext(), "Your Penalty is Created Successfully", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(NewPenalty.this,Home.class);
				startActivity(intent);
				finish();
				break;
					
			case 1:
				Toast.makeText(getApplicationContext(), "Insufficient Balance In Your Account. Please Refill Your Account", Toast.LENGTH_LONG).show();
				Intent intent1=new Intent(NewPenalty.this,PendingPenalty.class);
				startActivity(intent1);
				finish();
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_penalty, menu);
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
