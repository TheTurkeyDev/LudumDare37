package com.theprogrammingturkey.ld37.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class DataBaseConnect
{
	public static void sendData(String userName, long time, int walls, int enemies)
	{
		try
		{
			HttpURLConnection con = (HttpURLConnection) new URL("http://theprogrammingturkey.com/API/LD37LeaderBoard.php?userName=" + userName + "&time=" + time + "&walls=" + walls + "&enemies=" + enemies).openConnection();
			con.setReadTimeout(20000);
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:16.0) Gecko/20100101 Firefox/16.0");
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);

			int responseCode = con.getResponseCode();

			if(responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_MOVED_PERM)
				Gdx.app.log("Warn", "Update request returned response code: " + responseCode + " " + con.getResponseMessage());
			else if(responseCode == HttpURLConnection.HTTP_MOVED_PERM)
				Gdx.app.log("Warn", "URL was moved! Response code: " + responseCode + " " + con.getResponseMessage());
		} catch(IOException e)
		{
		}
	}

	public static void getLeaderBoard(List<LeaderBoardEntry> topTimes, List<LeaderBoardEntry> topWalls, List<LeaderBoardEntry> topEnemies)
	{
		try
		{
			HttpURLConnection con = (HttpURLConnection) new URL("http://theprogrammingturkey.com/API/LD37LeaderBoardGet.php").openConnection();
			con.setReadTimeout(20000);
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:16.0) Gecko/20100101 Firefox/16.0");
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);

			BufferedInputStream in = new BufferedInputStream(con.getInputStream());
			int responseCode = con.getResponseCode();

			if(responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_MOVED_PERM)
				Gdx.app.log("Warn", "Update request returned response code: " + responseCode + " " + con.getResponseMessage());
			else if(responseCode == HttpURLConnection.HTTP_MOVED_PERM)
				Gdx.app.log("Warn", "URL was moved! Response code: " + responseCode + " " + con.getResponseMessage());

			StringBuffer buffer = new StringBuffer();
			int chars_read;
			while((chars_read = in.read()) != -1)
			{
				buffer.append((char) chars_read);
			}
			String[] categories = buffer.toString().split("-----SPLIT-----");
			if(categories.length == 3)
			{
				for(int i = 0; i < categories.length; i++)
				{
					String[] entries = categories[i].split("<br>");
					for(String s : entries)
					{
						s = s.trim();
						int loc = s.indexOf(",");
						if(loc == -1)
							continue;
						String[] parts = s.toString().split(",");
						LeaderBoardEntry lbe = new LeaderBoardEntry(parts[0], Long.parseLong(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

						if(i == 0)
							topTimes.add(lbe);
						if(i == 1)
							topWalls.add(lbe);
						if(i == 2)
							topEnemies.add(lbe);
					}
				}
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
