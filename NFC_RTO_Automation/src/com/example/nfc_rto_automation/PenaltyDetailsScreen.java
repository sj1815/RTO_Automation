package com.example.nfc_rto_automation;

import com.example.data.viewpenaltydetails_request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PenaltyDetailsScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penalty_details_screen);
		
		TextView txtpenaltyid=(TextView)findViewById(R.id.textView9);
		TextView txtpolicename=(TextView)findViewById(R.id.textView10);
		TextView txtuserid=(TextView)findViewById(R.id.textView11);
		TextView txtusername=(TextView)findViewById(R.id.textView12);
		TextView txtoffence=(TextView)findViewById(R.id.textView13);
		TextView txtpenaltyamount=(TextView)findViewById(R.id.textView14);
		TextView txtdate=(TextView)findViewById(R.id.textView15);
		TextView txtlocation=(TextView)findViewById(R.id.textView16);
		TextView txtstatus=(TextView)findViewById(R.id.textView18);
		
		txtpenaltyid.setText(viewpenaltydetails_request.GetPenaltyId());
		txtpolicename.setText(viewpenaltydetails_request.GetPolicaeName());
		txtuserid.setText(viewpenaltydetails_request.GetUserId());
		txtusername.setText(viewpenaltydetails_request.GetUserName());
		txtoffence.setText(viewpenaltydetails_request.GetOffence());
		txtpenaltyamount.setText(viewpenaltydetails_request.GetPenaltyAmount());
		txtdate.setText(viewpenaltydetails_request.GetDate());
		txtlocation.setText(viewpenaltydetails_request.GetLocation());
		txtstatus.setText(viewpenaltydetails_request.GetStatus());
		
		Button btnhome=(Button)findViewById(R.id.button1);
		btnhome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(PenaltyDetailsScreen.this,Home.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.penalty_details_screen, menu);
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
