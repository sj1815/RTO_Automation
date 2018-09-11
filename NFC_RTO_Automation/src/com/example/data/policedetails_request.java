package com.example.data;

public class policedetails_request 
{
	public static String policeid;
	public static String policename;
	public static String policeemailid;
	public static String password;
	
	public static String GetPoliceId()
	{
		return policeid;
	}
	public static void SetPoliceId(String Rpoliceid)
	{
		policeid=Rpoliceid;
	}
	
	public static String GetPoliceName()
	{
		return policename;
	}
	public static void SetPoliceName(String Rpolicename)
	{
		policename=Rpolicename;
	}
	
	public static String GetPoliceEmailId()
	{
		return policeemailid;
	}
	public static void SetPoliceEmailId(String Rpoliceemailid)
	{
		policeemailid=Rpoliceemailid;
	}
	
	public static String GetPolicePassword()
	{
		return password;
	}
	public static void SetPolicePassword(String Rpassword)
	{
		password=Rpassword;
	}

	
}
