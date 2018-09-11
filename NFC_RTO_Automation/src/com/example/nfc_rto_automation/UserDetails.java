package com.example.nfc_rto_automation;

import com.example.data.userdetails_request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		
		TextView txtuserid=(TextView)findViewById(R.id.textView9);
		TextView txtusername=(TextView)findViewById(R.id.textView10);
		TextView txaddress=(TextView)findViewById(R.id.textView11);
		TextView txtcontactno=(TextView)findViewById(R.id.textView12);
		TextView txtemailid=(TextView)findViewById(R.id.textView13);
		TextView txtlicencetype=(TextView)findViewById(R.id.textView14);
		TextView txtbalance=(TextView)findViewById(R.id.textView15);
		TextView txtdateofbirth=(TextView)findViewById(R.id.textView16);
		
		txtuserid.setText(userdetails_request.GetUserId());
		txtusername.setText(userdetails_request.GetUserFullName());
		txaddress.setText(userdetails_request.GetAddress());
		txtcontactno.setText(userdetails_request.GetContactNo());
		txtemailid.setText(userdetails_request.GetEmailId());
		txtlicencetype.setText(userdetails_request.GetLicenceType());
		txtbalance.setText(userdetails_request.GetBalance());
		txtdateofbirth.setText(userdetails_request.GetDateOfBirth());
		
		Button btnnext=(Button)findViewById(R.id.button1);
		btnnext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(UserDetails.this,Home.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details, menu);
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
