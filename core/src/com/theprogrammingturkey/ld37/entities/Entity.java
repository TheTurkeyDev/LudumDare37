package com.theprogrammingturkey.ld37.entities;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld37.game.GameManager;
import com.theprogrammingturkey.ld37.graphics.Draw2D;

public class Entity
{
	private static final float MOVE_SPEED = 1f;

	private GameManager game;
	protected Vector2 position;
	protected Vector2 lastGrid;

	private Vector2 velocity;

	private boolean isPlayer = false;

	private Texture player = new Texture("textures/player.png");
	private Texture skull = new Texture("textures/bad_guy.png");

	public Entity(GameManager manager, int x, int y)
	{
		game = manager;
		position = new Vector2(x, y);
		lastGrid = new Vector2(x, y);
		velocity = new Vector2();
	}

	public void setAsPlayer()
	{
		this.isPlayer = true;
	}

	public void update()
	{
		position.add(velocity);
		if(within(position.x, 15, MOVE_SPEED) && within(position.y, 15, MOVE_SPEED))
		{
			Vector2 gridloc = new Vector2((int) (position.x / 30), (int) (position.y / 30));
			if(gridloc.x == lastGrid.x && gridloc.y == lastGrid.y && velocity.x != 0 && velocity.y != 0)
				return;
			List<Vector2> paths = game.getMazeGen().getNonWallsAround(gridloc);
			this.removeLastGridPos(paths);
			if(paths.size() == 0)
				velocity = new Vector2(0, 0);
			else
				velocity = paths.get(game.rand.nextInt(paths.size())).sub(gridloc).scl(MOVE_SPEED);
			this.lastGrid = gridloc;
		}
	}

	public void removeLastGridPos(List<Vector2> paths)
	{
		if(paths.size() == 1)
			return;

		for(int i = paths.size() - 1; i >= 0; i--)
		{
			Vector2 v = paths.get(i);
			if(v.x == lastGrid.x && v.y == lastGrid.y)
				paths.remove(i);
		}

	}

	public void render()
	{
		if(this.isPlayer)
			Draw2D.drawTextured(position.x - 8, position.y - 8, player);
		else
			Draw2D.drawTextured(position.x - 8, position.y - 8, skull);

	}

	public boolean within(float loc, float wantedLoc, float bounds)
	{
		return Math.abs(wantedLoc - (loc % 30)) < bounds;
	}

	public Vector2 getGridPosition()
	{
		return new Vector2((int) (position.x / 30), (int) (position.y / 30));
	}
}
