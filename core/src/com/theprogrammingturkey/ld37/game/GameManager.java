package com.theprogrammingturkey.ld37.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld37.entities.Entity;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.screens.ScreenManager;
import com.theprogrammingturkey.ld37.sounds.SoundManager;
import com.theprogrammingturkey.ld37.util.MazeGenerator;

public class GameManager
{
	private MazeGenerator gen;

	public Random rand = new Random();

	public boolean isPaused = false;

	private Entity player;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<PlaceableWall> walls = new ArrayList<PlaceableWall>();

	public void initGame()
	{
		isPaused = false;
		entities.clear();
		walls.clear();
		
		GameData.gameTime = 0;
		GameData.won = false;

		gen = new MazeGenerator();
		gen.generate(true, 42, 22, 30, 30);
		player = new Entity(this, 45, 45);
		player.setAsPlayer();

		for(int i = 0; i < GameData.enemies; i++)
			spawnRandomBadGuy();
		for(int i = 0; i < GameData.walls; i++)
			walls.add(new PlaceableWall());
	}

	public void renderMaze()
	{
		if(!gen.isGenrated())
			return;
		for(int y = 0; y < gen.getYSize(); y++)
		{
			for(int x = 0; x < gen.getXSize(); x++)
			{
				if(gen.isWall(x, y))
					Draw2D.drawTextured(x * 30, y * 30, Resources.BUSH);
				else
					Draw2D.drawTextured(x * 30, y * 30, Resources.GROUND);
			}
		}

		for(PlaceableWall wall : walls)
			if(wall.isPlaced())
				Draw2D.drawTextured(wall.getX() * 30, wall.getY() * 30, Resources.BUSH_PLACED);
	}

	public void renderEntities()
	{
		player.render();
		for(Entity ent : this.entities)
			ent.render();
	}

	public void update()
	{
		if(this.isPaused)
			return;

		GameData.gameTime++;

		for(PlaceableWall wall : walls)
			wall.update(gen);

		player.update();
		Vector2 pgp = player.getGridPosition();

		if(41 == pgp.x && 20 == pgp.y)
		{
			GameData.won = true;
			SoundManager.playSound(SoundManager.WIN, Settings.getSoundAdjusted());
			ScreenManager.INSTANCE.setCurrentScreen("End Screen");
		}

		for(Entity ent : this.entities)
		{
			ent.update();
			Vector2 gp = ent.getGridPosition();
			if(gp.x == pgp.x && gp.y == pgp.y)
			{
				GameData.won = false;
				SoundManager.playSound(SoundManager.LOSE, Settings.getSoundAdjusted());
				ScreenManager.INSTANCE.setCurrentScreen("End Screen");
			}
		}
	}

	public void placeWall(int x, int y)
	{
		for(PlaceableWall wall : walls)
		{
			if(!wall.isAvailable())
				continue;
			int wallX = x / 30;
			int wallY = y / 30;
			if(!gen.isWall(wallX, wallY))
				wall.place(gen, wallX, wallY);
		}
	}

	public void spawnRandomBadGuy()
	{
		float distance;
		Vector2 v;
		do
		{
			v = gen.getFreeLoc();
			distance = v.dst(player.getGridPosition());
		} while(distance < 10);

		entities.add(new Entity(this, (int) (v.x * 30 + 15), (int) (v.y * 30 + 15)));
	}

	public String getGameTime()
	{
		int minutes = (int) (GameData.gameTime / (60 * 60));
		int seconds = (int) ((GameData.gameTime / (60)) % 60);
		int milis = (int) ((1000 / 60) * (GameData.gameTime % 60));

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

	public MazeGenerator getMazeGen()
	{
		return this.gen;
	}

	public int getWallsAvailable()
	{
		int avail = 0;
		for(PlaceableWall wall : walls)
			if(wall.isAvailable())
				avail++;
		return avail;
	}

	public void togglePause()
	{
		this.isPaused = !this.isPaused;
	}

	public boolean isPaused()
	{
		return this.isPaused;
	}
}
