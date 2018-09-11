package com.example.nfc_rto_automation;

import com.example.connectivity.connectionManager;
import com.example.data.policedetails_request;
import com.example.data.userdetails_request;
import com.example.nfc_rto_automation.R;
import com.example.util.progressdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SwipeNFC_Card extends Activity {

	Dialog dg;
	int resp;
	Context context;
	
	private NfcAdapter mNfCAdapter;
	 boolean isNFCReadFlag=false; 
	 boolean isHandleIntent=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_nfc__card);
		
		TextView txtpolicename=(TextView)findViewById(R.id.textView2);
		TextView txtpoliceid=(TextView)findViewById(R.id.textView4);
		
		String policename=policedetails_request.GetPoliceName();
		
		if(policename==null)
		{
			Jump();
		}
		
		txtpolicename.setText(policedetails_request.GetPoliceName());
		txtpoliceid.setText(policedetails_request.GetPoliceId());
		
		/*if(policedetails_request.GetPoliceName().equals(null))
		{
			Intent intent=new Intent(SwipeNFC_Card.this,Login.class);
			startActivity(intent);
		}
		else
		{*/
			/*mNfCAdapter=NfcAdapter.getDefaultAdapter(this);
			if(mNfCAdapter==null)
			{
				Toast.makeText(getApplicationContext(), "This device does not support NFC.", Toast.LENGTH_SHORT).show();
				finish();
				return;    
			}
			
			if (!mNfCAdapter.isEnabled()) 
			{            
				AlertDialog dialog=new AlertDialog.Builder(context).create();
				dialog.setMessage("NFC Disabled! Please enable NFC before proceeding.");
				dialog.show();		
			}
			else
			{*/
			handleIntent(getIntent());
			//}
		
			Button btnnext=(Button)findViewById(R.id.button1);
			btnnext.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
				
					selelctuserdetails();
				}
			});
			
			Button btnlogout=(Button)findViewById(R.id.button2);
			btnlogout.setOnClickListener(new View.OnClickListener() {
						
				@Override
				public void onClick(View v) {
							
					Intent intent=new Intent(SwipeNFC_Card.this,CarLocation.class);
					startActivity(intent);
				}
			});
		//}
	}
	
	public void Jump()
	{
		Intent intent=new Intent(SwipeNFC_Card.this,Login.class);
		startActivity(intent);
		finish();
		
	}

	
	public void selelctuserdetails()
	{
		final connectionManager conn=new connectionManager();
		progressdialog  dialog=new progressdialog();
		dg=dialog.createDialog(this);
		dg.show();
		
		Thread tthread=new Thread()
		{
			@Override
			public void run()
			{
				if(conn.GetUserDetails())
				{
					resp=0;
				}
				hd.sendEmptyMessage(0);
			}
		};
		tthread.start();
	}
	
	private void handleIntent(Intent intent)
	{
		String action=intent.getAction();
		if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action))
    	{
			String type=intent.getType();
    		Object MIME_TEXT_PLAIN = "text/plain";
    		if (MIME_TEXT_PLAIN.equals(type)) 
    		{
    			 Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
 	             String[] test= tag.getTechList();
	             Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	             if (rawMsgs != null) 
	             {
	            	 TextView txt=(TextView)findViewById(R.id.textView1);
	            	 txt.setText("NFC ticket detected.");
	            	 isNFCReadFlag=true;
	                 NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
	                 for (int i = 0; i < rawMsgs.length; i++)
	                 {
	                        msgs[i] = (NdefMessage) rawMsgs[i];
	                 }
	                 NdefMessage ndefmsg=msgs[0];
	                 NdefRecord[] records= ndefmsg.getRecords();
	                 NdefRecord currRecord=records[0];
	                 byte[] payload=currRecord.getPayload();
	                 byte[] id=currRecord.getId();
	                 Log.d("ID", id.toString());
	                 int encoding=payload[0] & 128;
	                 
	                 // Get the Text Encoding
	                 String textEncoding = (encoding == 0) ? "UTF-8" : "UTF-16";
	                 
	                 // Get the Language Code
	                    int languageCodeLength = payload[0] & 0063;
	                    
	                    try
	                    {
	                    	
	                    	final String Userid=new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
	                    	
	                    	userdetails_request.SetUserId(Userid);
	                    	final connectionManager conn=new connectionManager();
	                    	if(connectionManager.checkNetworkAvailable(this))
	                    	{
	                    		
	                    		progressdialog dialog=new progressdialog();
								dg=dialog.createDialog(this);
								dg.show();		
	                    		isHandleIntent=true;
	                    		
	                    		Thread tsthread=new Thread()
	                    		{
	                    			@Override
	                    			public void run()
	                    			{
	                    				try
	                    				{
	                    					if(conn.GetUserDetails1())
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
	                    		tsthread.start();
	                    		
	                    	}
	                    	
	                    }
	                    catch(Exception e)
	                    {
	                    	e.printStackTrace();
	                    }
	             }
    		}
    	}
	}
	
	
	public Handler hd=new Handler()
	{
		
		public void handleMessage(Message msg)
		{
			dg.cancel();
			switch (resp) {
			case 0:
				Intent  intent=new Intent(SwipeNFC_Card.this,UserDetails.class);
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
		getMenuInflater().inflate(R.menu.swipe_nfc__card, menu);
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
