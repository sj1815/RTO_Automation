package com.example.data;

import java.util.ArrayList;

public class filloffences_request
{
private static ArrayList<String> stringlist;
	
	public static ArrayList<String> getOffencelist()
	{
		return stringlist;
	}
	public static void setOffencelist(ArrayList<String> Rstringlist)
	{
		stringlist=Rstringlist;
	}

}
