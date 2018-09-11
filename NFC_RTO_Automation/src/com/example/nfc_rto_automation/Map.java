package com.example.nfc_rto_automation;

import com.example.data.CarDetails_request;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Map extends Activity {

	GoogleMap googlemap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		String latitude=CarDetails_request.GetLatitude();
		String longitude=CarDetails_request.GetLongitude();
		String fullname=CarDetails_request.GetFullName();
		

		final ActivityManager activitymanager=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = 
				activitymanager.getDeviceConfigurationInfo();
        	final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        	if(supportsEs2)
        	{
        		/*if(latitude.equals(null)||longitude.equals(null))
        		{
        			latitude="0.0";
        			longitude="0.0";
        		}*/
        		
        		MarkerOptions marker=new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude))).title(fullname);
        		CameraPosition camera=new CameraPosition.Builder().target(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude))).zoom(15).build();
        		if(googlemap==null)
        		{
        			googlemap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        			
        			if(googlemap!=null)
        			{
        				//plot point
        				googlemap.addMarker(marker);
        				//focus camera to point
        				googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
         			}

        		}
        		
        	}

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
