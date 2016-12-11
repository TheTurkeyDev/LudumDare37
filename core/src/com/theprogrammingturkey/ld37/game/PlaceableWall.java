package com.theprogrammingturkey.ld37.game;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld37.sounds.SoundManager;
import com.theprogrammingturkey.ld37.util.MazeGenerator;
import com.theprogrammingturkey.ld37.util.Timer;

public class PlaceableWall
{
	private Vector2 placedWallLoc;

	private Timer wallTimer = new Timer(2 * 60);
	private Timer wallcoolDown = new Timer(5 * 60);

	public void update(MazeGenerator gen)
	{
		wallcoolDown.update();
		if(wallTimer.update())
		{
			SoundManager.playSound(SoundManager.BUSH_REMOVE, Settings.getSoundAdjusted());
			gen.removeWallAt((int) this.placedWallLoc.x, (int) this.placedWallLoc.y);
			this.placedWallLoc = null;
		}
	}

	public int getX()
	{
		return (int) this.placedWallLoc.x;
	}

	public int getY()
	{
		return (int) this.placedWallLoc.y;
	}

	public boolean isPlaced()
	{
		return !wallTimer.isComplete();
	}
	
	public boolean isAvailable()
	{
		return wallcoolDown.isComplete();
	}

	public void place(MazeGenerator gen, int x, int y)
	{
		SoundManager.playSound(SoundManager.BUSH_PLACE, Settings.getSoundAdjusted());
		this.placedWallLoc = new Vector2(x, y);
		this.wallTimer.start();
		this.wallcoolDown.start();
		gen.putWallAt(x, y);
	}
}
