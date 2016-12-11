package com.theprogrammingturkey.ld37.util;

public class Math
{
	public static float clamp(float low, float high, float value)
	{
		if(value < low)
			return low;
		if(value > high)
			return high;
		return value;
	}
}
