//Author- pranav	
//Version-12/18/15	
// calculate relative humidity

package com.heatindex.florida;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeatIndex {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String index="Heat Index";
		String Key=" Key West Florida";
		 
		System.out.printf("%35s%1s\n ", index, Key);
		 
		System.out.println("");
		
		System.out.printf("%40s\n ", "Months");
		 
		
		String[] months = { " Jan ", " Feb ", " Mar ", " Apr ", " May ", " Jun ", " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec " };
		
		
		for (String name : months) 
		{
		
			System.out.print(name);//month[i];
		}
		
		
		
		System.out.println("");
		 
		System.out.println("****************************** *********************************************");
		 
		String temp1 = "";
		File fileName = new File("KeyWestTemp.txt");
		System.out.println("Temp (F)");
		Scanner inFile = new Scanner(fileName);
		 
		ArrayList keyWestTemp = new ArrayList();
		while (inFile.hasNext()) 
		 
		{
		temp1 = inFile.next();
		 
		keyWestTemp.add(temp1);
		 
		System.out.printf("%6s", temp1);
		 
		}   inFile.close(); 
		 
		 
		 
		System.out.println("");
		 
		System.out.println("");	
		
		
		String hum1 = "";
		File fileName1 = new File("KeyWestHumid.txt");
		System.out.println("Humidity");
		Scanner inFile1 = new Scanner(fileName1);
		List keyWestHumid = new ArrayList();
		 
		while (inFile1.hasNext()) 
	
		 
		 
		{
		hum1 = inFile1.next();
		 
		keyWestHumid.add(hum1);
		System.out.printf("%6s", hum1);
		}
		
		if (keyWestHumid.size() != keyWestTemp.size()) {
			 
			System.out.println("Data does not match, please check your .txt file");
			}
		System.out.print('\n');
		System.out.println();
		
		List HI = new ArrayList();
		
		double calcHI;
		System.out.print("Relative humidity");
		System.out.print('\n');
		for (int i = 0; i < keyWestTemp.size(); i++)
		{
			
			double temp = Double.valueOf((String) keyWestTemp.get(i));
			double hum = Double.valueOf((String) keyWestHumid.get(i));
			
			
			calcHI=(-42.375+2.04901523*temp+10.14333127*hum-0.22475541*temp*hum-0.00683783*Math.pow(temp,2)-0.05481717*Math.pow(hum,2)+0.00122874*Math.pow(temp,2)*hum+0.00085282*temp*Math.pow(hum,2)-0.00000199*Math.pow(temp*hum,2) );
			calcHI = Math.round(100 * calcHI) / 100;
			//System.out.println(calcHI);
			
			System.out.printf("%6.1f", calcHI);
			
		

		}
		System.out.print('\n');
		
		}
			
			
		}
		
	
	
	
	
	
	
	


