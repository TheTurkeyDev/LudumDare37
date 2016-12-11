package com.theprogrammingturkey.ld37.util;

public class LeaderBoardEntry
{
	private String userName;
	private long time;
	private int walls;
	private int enemies;
	
	public LeaderBoardEntry(String userName, long time, int walls, int enemies)
	{
		this.userName = userName;
		this.time = time;
		this.walls = walls;
		this.enemies = enemies;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getTime()
	{
		int minutes = (int) (this.time / (60 * 60));
		int seconds = (int) ((this.time / (60)) % 60);
		int milis = (int) ((1000 / 60) * (this.time % 60));

		StringBuilder builder = new StringBuilder();
		if(minutes < 10)
			builder.append("0");
		builder.append(minutes);
		builder.append(":");
		if(seconds < 10)
			builder.append("0");
		builder.append(seconds);
		builder.append(".");
		if(milis < 100)
			builder.append("0");
		if(milis < 10)
			builder.append("0");
		builder.append(milis);
		return builder.toString();
	}

	public int getWalls()
	{
		return walls;
	}

	public int getEnemies()
	{
		return enemies;
	}
}
