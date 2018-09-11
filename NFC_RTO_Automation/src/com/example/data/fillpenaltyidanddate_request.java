package com.example.data;

import java.util.ArrayList;

public class fillpenaltyidanddate_request
{
	private static ArrayList<String> stringlist;
	private static ArrayList<String> stringlist1;
	private static ArrayList<String> stringlist2;
	
	public static ArrayList<String> getPenaltyIdAndDate()
	{
		return stringlist;
	}
	public static void setPenaltyIdAndDate(ArrayList<String> Rstringlist)
	{
		stringlist=Rstringlist;
	}
	
	public static ArrayList<String> getPenaltyId()
	{
		return stringlist1;
	}
	public static void SetPenaltyId(ArrayList<String> Rstringlist1)
	{
		stringlist1=Rstringlist1;
	}
	
	public static ArrayList<String> getPenaltyDates()
	{
		return stringlist2;
	}
	public static void setPenaltyDates(ArrayList<String> Rstringlist2)
	{
		stringlist2=Rstringlist2;
	}
	
}
