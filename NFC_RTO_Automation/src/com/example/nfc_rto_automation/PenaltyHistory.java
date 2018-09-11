package com.example.nfc_rto_automation;

import java.util.ArrayList;

import com.example.connectivity.connectionManager;
import com.example.data.fillpenaltyidanddate_request;
import com.example.data.viewpenaltydetails_request;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PenaltyHistory extends Activity {

	Dialog dg;
	int resp;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penalty_history);
		
		ListView lst=(ListView)findViewById(R.id.listView1);
		
		final ArrayList<String> PenaltyIdAndDate;
		PenaltyIdAndDate=fillpenaltyidanddate_request.getPenaltyIdAndDate();
		
		final ArrayList<String> PenaltyDates;
		PenaltyDates=fillpenaltyidanddate_request.getPenaltyDates();
		
		if(PenaltyIdAndDate.isEmpty())
		{
			//Toast.makeText(getApplicationContext(), "No Penalties found", Toast.LENGTH_LONG).show();
			
			AlertDialog alert=new AlertDialog.Builder(this).create();
			alert.setTitle("No Record Found");
			alert.setMessage("No Penalties found");
			alert.show();
		}
		
		final ArrayList<String> PenalatyId;
		PenalatyId=fillpenaltyidanddate_request.getPenaltyId();
		
		lst.setAdapter(new ArrayAdapter<String>(PenaltyHistory.this,
				android.R.layout.simple_expandable_list_item_1,PenaltyDates));
		
		lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//String selecteditem=PenaltyIdAndDate.get(position);
				//String penaltyid=selecteditem.substring(13,17);
				String penaltyid=PenalatyId.get(position);
				viewpenaltydetails_request.SetPenaltyId(penaltyid);
				ShowPenaltyDetails();
			}
		});
		
	}
	
	public void ShowPenaltyDetails()
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
				if(conn.GetPenalyDetails())
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
				
				Intent intent=new Intent(PenaltyHistory.this,PenaltyDetailsScreen.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.penalty_history, menu);
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
