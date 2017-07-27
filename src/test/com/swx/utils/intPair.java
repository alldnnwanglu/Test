package com.swx.utils;

public class intPair{
	public int key;
	public int value;
	
	public intPair(int key,int value)
	{
		this.key = key;
		this.value = value;
	}
	
	public String toString()
	{
		return key+","+value;
	}
}