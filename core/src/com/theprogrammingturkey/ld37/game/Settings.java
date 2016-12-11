package com.theprogrammingturkey.ld37.game;

public class Settings
{
	public static boolean sounds = true;
	public static float soundLevel = 100;

	public static float getSoundAdjusted()
	{
		return soundLevel / 100f;
	}
}
