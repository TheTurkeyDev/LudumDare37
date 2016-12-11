package com.theprogrammingturkey.ld37.util;

public class Timer
{
	private int delay = 0;
	private int length = 0;

	boolean complete = true;

	public Timer(int length)
	{
		this.length = length;
	}

	public boolean update()
	{
		if(delay > 0)
		{
			delay--;
			if(delay == 0)
			{
				complete = true;
				return true;
			}
		}
		return false;
	}

	public void start()
	{
		this.delay = length;
		complete = false;
	}

	public boolean isComplete()
	{
		return this.complete;
	}

	public int gettimeLeft()
	{
		return this.delay;
	}
}
