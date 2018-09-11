package com.example.connectivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.data.CarDetails_request;
import com.example.data.filloffences_request;
import com.example.data.fillpenaltyidanddate_request;
import com.example.data.newpenalty_request;
import com.example.data.offencedetails_request;
import com.example.data.policedetails_request;
import com.example.data.userdetails_request;
import com.example.data.viewpenaltydetails_request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.tech.TagTechnology;

public class connectionManager
{

	public static boolean checkNetworkAvailable(Context context)
	{
		try {
			ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo(); 
			return activeNetworkInfo != null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	
	public boolean authenticate_user()
	{
		String responseString;
		try
		{
			final String TAG_POLICEID="PoliceId";
			final String TAG_POLICENAME="PoliceFullname";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/Login/"+policedetails_request.GetPoliceEmailId()+"/"+policedetails_request.GetPolicePassword()+"");
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String policeid=jsonObj.getString(TAG_POLICEID);
		        String policename=jsonObj.getString(TAG_POLICENAME);
		        
		        if(policename!="null")
		        {
		        	policedetails_request.SetPoliceId(policeid);
		        	policedetails_request.SetPoliceName(policename);
		        	return true;
		        }
		        else
		        {
		        	return false;
		        }
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
		}
		catch(Exception e)
		{
			return false;
		}
				
	}
	
	public boolean GetUserDetails()
	{
		String responseString;
		try
		{
			final String TAG_USERID="UserId";
			final String TAG_USERFULLNAME="FullName";
			final String TAG_USERADDRESS="Address";
			final String TAG_USERCONTACTNO="ContactNo";
			final String TAG_USEREMAILID="EmailId";
			final String TAG_LICENCETYPE="LicenceType";
			final String TAG_IMAGEPATH="ImagePath";
			final String TAG_BALANCE="Balance";
			final String TAG_DATEOFBIRTH="DateOfBirth";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetUserDetails/4");
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String userid=jsonObj.getString(TAG_USERID);
		        String userfullname=jsonObj.getString(TAG_USERFULLNAME);
		        String useraddress=jsonObj.getString(TAG_USERADDRESS);
		        String usercontactno=jsonObj.getString(TAG_USERCONTACTNO);
		        String useremailid=jsonObj.getString(TAG_USEREMAILID);
		        String userlicencetype=jsonObj.getString(TAG_LICENCETYPE);
		        String userimagepath=jsonObj.getString(TAG_IMAGEPATH);
		        String userbalance=jsonObj.getString(TAG_BALANCE);
		        String userdateofbirth=jsonObj.getString(TAG_DATEOFBIRTH);
		        
		        userdetails_request.SetUserId(userid);
		        userdetails_request.SetUserFullName(userfullname);
		        userdetails_request.SetAddress(useraddress);
		        userdetails_request.SetContactNo(usercontactno);
		        userdetails_request.SetEmailId(useremailid);
		        userdetails_request.SetLicenceType(userlicencetype);
		        userdetails_request.SetImagePath(userimagepath);
		        userdetails_request.SetBalance(userbalance);
		        userdetails_request.SetDateOfBirth(userdateofbirth);
		        
		        return true;
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean GetUserDetails1()
	{
		String responseString;
		try
		{
			final String TAG_USERID="UserId";
			final String TAG_USERFULLNAME="FullName";
			final String TAG_USERADDRESS="Address";
			final String TAG_USERCONTACTNO="ContactNo";
			final String TAG_USEREMAILID="EmailId";
			final String TAG_LICENCETYPE="LicenceType";
			final String TAG_IMAGEPATH="ImagePath";
			final String TAG_BALANCE="Balance";
			final String TAG_DATEOFBIRTH="DateOfBirth";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetUserDetails/"+userdetails_request.GetUserId());
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String userid=jsonObj.getString(TAG_USERID);
		        String userfullname=jsonObj.getString(TAG_USERFULLNAME);
		        String useraddress=jsonObj.getString(TAG_USERADDRESS);
		        String usercontactno=jsonObj.getString(TAG_USERCONTACTNO);
		        String useremailid=jsonObj.getString(TAG_USEREMAILID);
		        String userlicencetype=jsonObj.getString(TAG_LICENCETYPE);
		        String userimagepath=jsonObj.getString(TAG_IMAGEPATH);
		        String userbalance=jsonObj.getString(TAG_BALANCE);
		        String userdateofbirth=jsonObj.getString(TAG_DATEOFBIRTH);
		        
		        userdetails_request.SetUserId(userid);
		        userdetails_request.SetUserFullName(userfullname);
		        userdetails_request.SetAddress(useraddress);
		        userdetails_request.SetContactNo(usercontactno);
		        userdetails_request.SetEmailId(useremailid);
		        userdetails_request.SetLicenceType(userlicencetype);
		        userdetails_request.SetImagePath(userimagepath);
		        userdetails_request.SetBalance(userbalance);
		        userdetails_request.SetDateOfBirth(userdateofbirth);
		        
		        return true;
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
		        throw new IOException(statusLine.getReasonPhrase());
		    }
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean GetPenaltyHistory()
	{
		 String responseString;
		 JSONArray newJsonArray;
		 ArrayList<String> stringArray;
		 ArrayList<String> stringarray1;
		 ArrayList<String> stringarray2;
		 
		 try
		 {
			 final String TAG_PENALTYID="PenaltyId";
			 final String TAG_PENALTYIDDATE="PenaltyAndDate";
			 final String TAG_PENALTYDATE="PenaltyDate";
			 String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetPenaltiesAndDate/"+userdetails_request.GetUserId());
				HttpClient httpclient = new DefaultHttpClient();
			    HttpResponse response = httpclient.execute(new HttpGet(url));
			    StatusLine statusLine = response.getStatusLine();
			    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
			    {
			    	ByteArrayOutputStream out = new ByteArrayOutputStream();
			        response.getEntity().writeTo(out);
			        out.close();
			        responseString = out.toString();
			        
			        newJsonArray=new JSONArray(responseString);
				    stringArray=new ArrayList<String>(newJsonArray.length());
				    stringarray1=new ArrayList<String>(newJsonArray.length());
				    stringarray2=new ArrayList<String>(newJsonArray.length());
				    
				    for(int i=0;i<newJsonArray.length();i++)
				     {
				    	 JSONObject jsonObj = newJsonArray.getJSONObject(i);
				    	 String penaltyidanddate=jsonObj.optString(TAG_PENALTYIDDATE);
				    	 String penaltyid=jsonObj.optString(TAG_PENALTYID);
				    	 String penaltyDates=jsonObj.optString(TAG_PENALTYDATE);
				    	 
				    	 stringArray.add(penaltyidanddate);
				    	 stringarray1.add(penaltyid);
				    	 stringarray2.add(penaltyDates);
				     }
				    fillpenaltyidanddate_request.setPenaltyIdAndDate(stringArray);
				    fillpenaltyidanddate_request.SetPenaltyId(stringarray1);
				    fillpenaltyidanddate_request.setPenaltyDates(stringarray2);
				    return true;
			    }
			    else
			    {
			    	response.getEntity().getContent().close();
				    throw new IOException(statusLine.getReasonPhrase());
			    }
		 }
		 catch(Exception e)
		 {
			 return false;
		 }
	}
	
	public boolean GetPenalyDetails()
	{
		String responseString;
		try
		{
			final String TAG_PENALTYID="PenaltyId";
			final String TAG_POLICENAME="PoliceName";
			final String TAG_USERID="UserId";
			final String TAG_USERNAME="UserName";
			final String TAG_OFFENCE="Offence";
			final String TAG_PENALTYAMOUNT="PenaltyAmount";
			final String TAG_DATE="Date";
			final String TAG_LOCATION="Location";
			final String TAG_STATUS="Status";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetPenaltyDetails/"+viewpenaltydetails_request.GetPenaltyId().trim());
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String penaltyid=jsonObj.getString(TAG_PENALTYID);
		        String policename=jsonObj.getString(TAG_POLICENAME);
		        String userid=jsonObj.getString(TAG_USERID);
		        String username=jsonObj.getString(TAG_USERNAME);
		        String offence=jsonObj.getString(TAG_OFFENCE);
		        String penaltyamount=jsonObj.getString(TAG_PENALTYAMOUNT);
		        String date=jsonObj.getString(TAG_DATE);
		        String location=jsonObj.getString(TAG_LOCATION);
		        String status=jsonObj.getString(TAG_STATUS);
		        
		        viewpenaltydetails_request.SetPenaltyId(penaltyid);
		        viewpenaltydetails_request.SetPoliceName(policename);
		        viewpenaltydetails_request.SetUserId(userid);
		        viewpenaltydetails_request.SetUserName(username);
		        viewpenaltydetails_request.SetOffence(offence);
		        viewpenaltydetails_request.SetPenaltyAmount(penaltyamount);
		        viewpenaltydetails_request.SetDate(date);
		        viewpenaltydetails_request.SetLocation(location);
		        viewpenaltydetails_request.SetStatus(status);
		        
		        return true;
		        
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
			    throw new IOException(statusLine.getReasonPhrase());

		    }
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean GetOffenceList()
	{
		String responseString;
		JSONArray newJsonArray;
		ArrayList<String> stringArray;
		
		try
		{
			final String TAG_OFFENCENAME="OffencesNames";
			 String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetOffences");
				HttpClient httpclient = new DefaultHttpClient();
			    HttpResponse response = httpclient.execute(new HttpGet(url));
			    StatusLine statusLine = response.getStatusLine();
			    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
			    {
			    	ByteArrayOutputStream out = new ByteArrayOutputStream();
			        response.getEntity().writeTo(out);
			        out.close();
			        responseString = out.toString();
			        
			        newJsonArray=new JSONArray(responseString);
				    stringArray=new ArrayList<String>(newJsonArray.length());
				    
				    for(int i=0;i<newJsonArray.length();i++)
				     {
				    	 JSONObject jsonObj = newJsonArray.getJSONObject(i);
				    	 String offencename=jsonObj.optString(TAG_OFFENCENAME);
				    	 
				    	 stringArray.add(offencename);
				     }
				    filloffences_request.setOffencelist(stringArray);
				    return true;
			    }
			    else
			    {
			    	response.getEntity().getContent().close();
				    throw new IOException(statusLine.getReasonPhrase());
			    }
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean GetPenaltyAmount()
	{
		String responseString;
		try
		{
			final String TAG_PENALTYAMOUNT="Penalty";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetOffencePenalty/"+URLEncoder.encode(offencedetails_request.GetOffenceName(),"utf-8"));
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String penaltyamount=jsonObj.getString(TAG_PENALTYAMOUNT);
		        offencedetails_request.SetPenaltyAmount(penaltyamount);
		        return true;
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
			    throw new IOException(statusLine.getReasonPhrase());
		    }
		    
		 }
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean CreateNewPenalty()
	{
		String responseString;
		try
		{
			
			final String TAG_RESULT="result";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/CreateNewPenalty/"+policedetails_request.GetPoliceId()+"/"+userdetails_request.GetUserId()+"/"+URLEncoder.encode(offencedetails_request.GetOffenceName(),"utf-8")+"/"+offencedetails_request.GetPenaltyAmount()+"/"+URLEncoder.encode(newpenalty_request.GetLocation(),"utf-8"));
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String result=jsonObj.getString(TAG_RESULT);
		        
		        if(result.equals("true"))
		        {
		        	return true;
		        }
		        else if(result.equals("false"))
		        {
		        	return false;
		        }
		        
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
			    throw new IOException(statusLine.getReasonPhrase());
		    }
		 }
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public void GetCarLocation()
	{
		String responseString;
		try
		{
			
			final String TAG_LATITUDE="latitude";
			final String TAG_LONGITUDE="longitude";
			final String TAG_FULLANME="fullname";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetUserLocation/"+URLEncoder.encode(CarDetails_request.GetCarNoPlate(),"utf-8"));
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String latitude=jsonObj.getString(TAG_LATITUDE);
		        String longitude=jsonObj.getString(TAG_LONGITUDE);
		        String fullname=jsonObj.getString(TAG_FULLANME);
		        
		        CarDetails_request.SetLatitude(latitude);
		        CarDetails_request.SetLongitude(longitude);
		        CarDetails_request.SetFullName(fullname);
		        
		        if(latitude!="null")
		        {
		        	CarDetails_request.SetResult(0);
		        }
		        else
		        {
		        	CarDetails_request.SetResult(1);
		        }
		        
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
			    throw new IOException(statusLine.getReasonPhrase());
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void GetUserDetailsNew()
	{
		String responseString;
		try
		{
			
			final String TAG_FullName="FullName";
			final String TAG_address="address";
			final String TAG_contact_no="contact_no";
			final String TAG_email_id="email_id";
			final String TAG_date_of_birth="date_of_birth";
			
			String url=String.format("http://my-demo.in/RTO_Automation_Service_VIT/Service1.svc/GetUserDetailsNew/"+URLEncoder.encode(CarDetails_request.GetCarNoPlate(),"utf-8"));
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response = httpclient.execute(new HttpGet(url));
		    StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
		    {
		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        responseString = out.toString();
		        
		        JSONObject jsonObj=new JSONObject(responseString);
		        String FullName=jsonObj.getString(TAG_FullName);
		        String Address=jsonObj.getString(TAG_address);
		        String Contact_No=jsonObj.getString(TAG_contact_no);
		        String EmailId=jsonObj.getString(TAG_email_id);
		        String DOB=jsonObj.getString(TAG_date_of_birth);
		        
		       CarDetails_request.SetFullName(FullName);
		       CarDetails_request.SetAddress(Address);
		       CarDetails_request.SetPhoneNumber(Contact_No);
		       CarDetails_request.SetEmailId(EmailId);
		       CarDetails_request.SetDOB(DOB);
		        
		        if(FullName!="null")
		        {
		        	CarDetails_request.SetResult(0);
		        }
		        else
		        {
		        	CarDetails_request.SetResult(1);
		        }
		        
		    }
		    else
		    {
		    	response.getEntity().getContent().close();
			    throw new IOException(statusLine.getReasonPhrase());
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
