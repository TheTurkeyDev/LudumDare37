package com.theprogrammingturkey.ld37.game;

public class GameData
{
	public static boolean won = true;
	public static long gameTime = 0;
	public static int walls = 0;
	public static int enemies = 0;
	
	public static void reset()
	{
		won = false;
		gameTime = 0;
		walls = 0;
		enemies = 0;
	}
}
