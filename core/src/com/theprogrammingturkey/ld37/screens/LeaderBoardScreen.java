package com.theprogrammingturkey.ld37.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;
import com.theprogrammingturkey.ld37.util.DataBaseConnect;
import com.theprogrammingturkey.ld37.util.LeaderBoardEntry;

public class LeaderBoardScreen extends Screen
{
	private List<LeaderBoardEntry> topTimes = new ArrayList<LeaderBoardEntry>();
	private List<LeaderBoardEntry> topWalls = new ArrayList<LeaderBoardEntry>();
	private List<LeaderBoardEntry> topEnemies = new ArrayList<LeaderBoardEntry>();

	private DisplayType display = DisplayType.Time;

	public LeaderBoardScreen()
	{
		super("Leaderboard Screen");
		this.addGuiComponent(new GuiButton(0, (Gdx.graphics.getWidth() / 2) - 169, 10, 338, 75, "[Back]", null));
		this.addGuiComponent(new GuiButton(1, 460, Gdx.graphics.getHeight() - 150, 250, 75, "Time", null));
		this.addGuiComponent(new GuiButton(2, 740, Gdx.graphics.getHeight() - 150, 200, 75, "Walls", null));
		this.addGuiComponent(new GuiButton(3, 920, Gdx.graphics.getHeight() - 150, 500, 75, "Enemies", null));
	}


	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
		else if(guic.getId() == 1)
			display = DisplayType.Time;
		else if(guic.getId() == 2)
			display = DisplayType.Walls;
		else if(guic.getId() == 3)
			display = DisplayType.Enemies;
	}

	public void onScreenLoad()
	{
		topTimes.clear();
		topWalls.clear();
		topEnemies.clear();
		DataBaseConnect.getLeaderBoard(topTimes, topWalls, topEnemies);
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.BUSH_BACKGROUND);
		Draw2D.drawString(150, Gdx.graphics.getHeight() - 10, "LEADERBOARD", 3f, Color.WHITE);
		Draw2D.drawString(100, Gdx.graphics.getHeight() - 100, "Username", 1.5f, Color.WHITE);

		int i = 0;
		if(display.equals(DisplayType.Time))
		{
			for(LeaderBoardEntry entry : this.topTimes)
			{
				Draw2D.drawString(10, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + (i + 1) + ")", 1.5f, Color.WHITE);
				Draw2D.drawString(100, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getUserName(), 1.5f, Color.WHITE);
				Draw2D.drawString(500, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getTime(), 1.5f, Color.WHITE);
				Draw2D.drawString(875, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getWalls(), 1.5f, Color.WHITE);
				Draw2D.drawString(1100, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getEnemies(), 1.5f, Color.WHITE);
				i++;
			}
		}
		else if(display.equals(DisplayType.Walls))
		{
			for(LeaderBoardEntry entry : this.topWalls)
			{
				Draw2D.drawString(10, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + (i + 1) + ")", 1.5f, Color.WHITE);
				Draw2D.drawString(100, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getUserName(), 1.5f, Color.WHITE);
				Draw2D.drawString(500, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getTime(), 1.5f, Color.WHITE);
				Draw2D.drawString(875, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getWalls(), 1.5f, Color.WHITE);
				Draw2D.drawString(1100, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getEnemies(), 1.5f, Color.WHITE);
				i++;
			}
		}
		else if(display.equals(DisplayType.Enemies))
		{
			for(LeaderBoardEntry entry : this.topEnemies)
			{
				Draw2D.drawString(10, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + (i + 1) + ")", 1.5f, Color.WHITE);
				Draw2D.drawString(100, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getUserName(), 1.5f, Color.WHITE);
				Draw2D.drawString(500, Gdx.graphics.getHeight() - (150 + (i * 50)), entry.getTime(), 1.5f, Color.WHITE);
				Draw2D.drawString(875, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getWalls(), 1.5f, Color.WHITE);
				Draw2D.drawString(1100, Gdx.graphics.getHeight() - (150 + (i * 50)), "" + entry.getEnemies(), 1.5f, Color.WHITE);
				i++;
			}
		}
		super.render();
	}

	enum DisplayType
	{
		Time, Walls, Enemies;
	}
}
