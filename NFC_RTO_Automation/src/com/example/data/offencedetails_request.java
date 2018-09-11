package com.example.data;

public class offencedetails_request 
{
	public static String offencename;
	public static String penaltyamount;
	
	public static String GetOffenceName()
	{
		return offencename;
	}
	public static void SetOffenceName(String Roffencename)
	{
		offencename=Roffencename;
	}
	
	public static String GetPenaltyAmount()
	{
		return penaltyamount;
	}
	public static void SetPenaltyAmount(String Rpenaltyamount)
	{
		penaltyamount=Rpenaltyamount;
	}
}
