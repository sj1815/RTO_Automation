package com.example.nfc_rto_automation;

import com.example.connectivity.connectionManager;
import com.example.util.progressdialog;

import android.app.Activity;
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

public class Home extends Activity {

	Dialog dg;
	int resp;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Button btnpenaltyhistory=(Button)findViewById(R.id.button1);
		btnpenaltyhistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				PenaltyHistory();
			}
		});
		
		Button btnnewpenalty=(Button)findViewById(R.id.button2);
		btnnewpenalty.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				GetOffencelist();
				
			}
		});
		
		Button btnexit=(Button)findViewById(R.id.button3);
		btnexit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(Home.this,SwipeNFC_Card.class);
				startActivity(intent);
			}
		});
	}
	
	
	public void PenaltyHistory()
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
				if(conn.GetPenaltyHistory())
				{
					resp=0;
				}
				hd.sendEmptyMessage(0);
			}
		};
		tthread.start();
	}
	
	public void GetOffencelist()
	{
		final connectionManager conn=new connectionManager();
		progressdialog dialog=new progressdialog();
		dg=dialog.createDialog(this);
		dg.show();
		
		Thread tthread1=new Thread()
		{
			@Override
			public void run()
			{
				if(conn.GetOffenceList())
				{
					resp=0;
				}
				hd1.sendEmptyMessage(0);
			}
		};
		tthread1.start();
	}
	
	public Handler hd=new Handler()
	{
		public void handleMessage(Message msg)
		{
			dg.cancel();
			switch (resp)
			{
			case 0:
				
				Intent intent=new Intent(Home.this,PenaltyHistory.class);
				startActivity(intent);
				finish();
				
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
				Intent intent=new Intent(Home.this,NewPenalty.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
