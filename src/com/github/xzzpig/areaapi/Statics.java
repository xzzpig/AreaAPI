package com.github.xzzpig.areaapi;
import java.util.*;

public class Statics
{
	public String prefix(int color){
		return "§6[AreaAPI]§"+color;
	}
	public String prefix(String color){
		return "§6[AreaAPI]§"+color;
	}
	
	public static int getMin(int[] ints)
	{
		if(ints == null || ints.length == 0)
			return 0;
		int[] cints = ints.clone();
		Arrays.sort(cints);
		return cints[0];
	}
	public static int getMinid(int[] ints)
	{		
		if(ints == null || ints.length == 0)
			return -1;
		int min = getMin(ints);
		Debuger.print(Arrays.toString(ints)+min);
		for(int i =0;i<ints.length;i++)
		{
			if(ints[i]== min)
			{
				return i;
			}
		}
		return -1;
	}
}
