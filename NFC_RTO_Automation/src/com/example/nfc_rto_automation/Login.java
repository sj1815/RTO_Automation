package com.example.nfc_rto_automation;

import com.example.connectivity.connectionManager;
import com.example.data.policedetails_request;
import com.example.nfc_rto_automation.R;
import com.example.util.progressdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	Dialog dg;
	int resp;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button btnlogin=(Button)findViewById(R.id.button1);
		btnlogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				login();
			}
		});
		
	}
	
	public void login()
	{
		final EditText txtemailid=(EditText)findViewById(R.id.editText1);
		final EditText txtpassword=(EditText)findViewById(R.id.editText2);
		String emailid=txtemailid.getText().toString();
		String password=txtpassword.getText().toString();
		if(emailid.equals("")||password.equals(""))
		{
			AlertDialog alert=new AlertDialog.Builder(this).create();
			alert.setTitle("Enter All Details");
			alert.setMessage("All Fields Are Mandatory");
			alert.show();
		}
		else
		{
			policedetails_request.SetPoliceEmailId(emailid);
			policedetails_request.SetPolicePassword(password);
			
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
						try
						{
							if(conn.authenticate_user())
							{
								resp=0;
							}
							else
							{
								resp=1;
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
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
	
	public Handler hd=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			switch (resp) 
			{
			case 0:
				EditText txtemailid=(EditText)findViewById(R.id.editText1);
				EditText txtpassword=(EditText)findViewById(R.id.editText2);
				 
				txtemailid.setText("");
				txtpassword.setText("");
				
				Intent intent=new Intent(Login.this,SwipeNFC_Card.class);
				startActivity(intent);
				break;

			case 1:
				 
				EditText txtemailid1=(EditText)findViewById(R.id.editText1);
				EditText txtpassword1=(EditText)findViewById(R.id.editText2);
				 
				txtemailid1.setText("");
				txtpassword1.setText("");
				
				Toast.makeText(getApplicationContext(), "Invalid Email Id Or Password", Toast.LENGTH_LONG).show();
				 
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
