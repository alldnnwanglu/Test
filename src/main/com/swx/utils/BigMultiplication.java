package com.swx.utils;

/**
 * @author rodking
 * 超出long * long 乘法运算器
 */
public class BigMultiplication {
	
	public static String multiply(String one,String two)
	{
		int[] ones,twos;
		
		ones = _Str2Ints(one);
		twos = _Str2Ints(two);
		
		int[] overNums = new int[ones.length + twos.length];
		
		for(int i=0;i<ones.length;i++){
			for(int j=0;j<twos.length;j++){
				overNums[i+j] += ones[i] * twos[j];
			}
		}
		
		
		String tempStr ="";
		for(int i = overNums.length -2;i>=0;i--){
			overNums[i] = overNums[i] + overNums[i + 1] / 10;
		}
		
		for(int i = 0;i<overNums.length-1;i++){
			tempStr += overNums[i]%10; 
		}
		
		
		//System.out.println(one +" * " + two + " = "+ (Long.parseLong(one) * Long.parseLong(two)));
		System.out.println(one +" * " + two + " = "+ tempStr);
	
		return tempStr;
	}
	
	private static int[] _Str2Ints(String str){
		int[] arrays = new int[str.length()];
		
		for(int i=0;i<str.length();i++){
			arrays[i] = Integer.parseInt(str.substring(i, i+1));
		}
		return arrays;
	}
}
