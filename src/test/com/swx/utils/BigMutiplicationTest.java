package com.swx.utils;

public class BigMutiplicationTest {
	
	public static void main(String[] args)
	{
		System.out.println(BigMultiplication.multiply("1","11111111111111111111111"));
//		double dd = 11;
//		
//		
//		// 3 * 3 *3 
//		// 2 * 2 * 2
//		// 有与 
//		System.out.println(Double.toHexString(dd));
//		System.out.println(Double.doubleToLongBits(dd));
//		System.out.println(Double.doubleToRawLongBits(dd));
//		System.out.println(Integer.toBinaryString(Float.floatToIntBits(11)));
		
//		float d = Float.MAX_VALUE;//1.02f;
//		//System.out.println();
//		int tt = Float.floatToIntBits(d);//111100011;
//		System.out.println(tt);
//		System.out.println(Float.intBitsToFloat(tt));
	}
	
	public static byte[] intToByte4(int sum) {
        byte[] arr = new byte[4];
        arr[0] = (byte) (sum >> 24);
        arr[1] = (byte) (sum >> 16);
        arr[2] = (byte) (sum >> 8);
        arr[3] = (byte) (sum & 0xff);
        return arr;
    }
}
