package com.swx.utils;

import java.util.ArrayList;
import java.util.List;

public class TestList {
	
	public static void main(String[] args)
	{
		
		List<intPair> array = new ArrayList<>();
		for(int i=10;i<20;i++)
		{
			//intPair p = new intPair(3,1);
			//insertObj(array,new intPair(i-9,i));
			array.set(0,new intPair(i-9,i));
		}
		
		
		
		printArray(array);
		System.out.println("------------------------------");
		
		insertObj(array,new intPair(3,100));
		
		printArray(array);
		
		//System.out.println("------" + array.r.get(new Integer(110)));
	}
	
	public static void printArray(List<intPair> list)
	{
		for(int i=list.size()-1;i>=0;i--)
		{
			System.out.print(list.get(i)+"|");
		}
	
		System.out.println("");
	}
	
	public static void insertObj(List<intPair> list,intPair pair)
	{
		if(list == null)
			list = new ArrayList<>();
		
		for(int i=list.size()-1;i>=0;i--)
		{
			intPair lpair = list.get(i);
			if(null == lpair)
				continue;
			
			if(lpair.key == pair.key)
				list.remove(lpair);
			
			list.add(1,pair);
			break;
			
//			if(pair.value<=lpair.value)
//				list.add(i, pair);
//			else if(i == 0)
//				list.add(i,pair);
		}
		
		if(list.isEmpty()){
			list.add(pair);
			return;
		}
	}
}
