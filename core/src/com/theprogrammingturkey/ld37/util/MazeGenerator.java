package com.theprogrammingturkey.ld37.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class MazeGenerator
{
	private int xSize;
	private int ySize;
	private int width;
	private int height;
	private int xWallScale, yWallScale;
	private int[][] map;
	private ArrayList<Vector2> walls = new ArrayList<Vector2>();
	private Random r = new Random();

	private int currentX = 1;
	private int currentY = 1;

	private int nonWall = 0;
	private int wall = 1;

	private boolean generated = false;

	/**
	 * creates a maze with the given parameters
	 * 
	 * @param multiple
	 *            solutions
	 * @param x
	 *            maze Size
	 * @param y
	 *            maze size
	 * @param x
	 *            wall size
	 * @param y
	 *            wall size
	 */
	public void generate(boolean multiple, int x1, int y1, int x2, int y2)
	{
		xSize = x1;
		ySize = y1;
		xWallScale = x2;
		yWallScale = y2;
		map = new int[xSize][ySize];

		for(int y = 0; y < ySize; y++)
			for(int x = 0; x < xSize; x++)
				map[x][y] = wall;

		map[1][1] = nonWall;
		currentX = 1;
		currentY = 1;
		Vector2 current = new Vector2(currentX, currentY);
		Vector2 north = current.cpy();
		north.add(0, -1);
		Vector2 east = current.cpy();
		east.add(1, 0);
		Vector2 south = current.cpy();
		south.add(0, 1);
		Vector2 west = current.cpy();
		west.add(-1, 0);

		if((north.y > 0) && (map[(int) north.x][(int) north.y] == wall))
		{
			if(multiple)
				walls.add(north);
			else if((map[(int) north.x][(int) (north.y - 1)] == wall))
				walls.add(north);
		}
		if((east.x < xSize) && (map[(int) east.x][(int) east.y] == wall))
		{
			if(multiple)
				walls.add(east);
			else if((map[(int) (east.x + 1)][(int) east.y] == wall))
				walls.add(east);
		}
		if((south.y < ySize) && (map[(int) south.x][(int) south.y] == wall))
		{
			if(multiple)
				walls.add(south);
			else if((map[(int) south.x][(int) (south.y + 1)] == wall))
				walls.add(south);
		}
		if((west.x > 0) && (map[(int) west.x][(int) west.y] == wall))
		{
			if(multiple)
				walls.add(west);
			else if((map[(int) (west.x - 1)][(int) west.y] == wall))
				walls.add(west);
		}

		while(walls.size() > 0)
		{
			int randomLoc = r.nextInt(walls.size());
			currentX = (int) (walls.get(randomLoc)).x;
			currentY = (int) (walls.get(randomLoc)).y;
			current = new Vector2(currentX, currentY);
			north = current.cpy();
			north.add(0, -1);
			east = current.cpy();
			east.add(1, 0);
			south = current.cpy();
			south.add(0, 1);
			west = current.cpy();
			west.add(-1, 0);

			if(!checkwalls(current))
			{
				map[currentX][currentY] = nonWall;
				walls.remove(randomLoc);

				if((north.y > 0) && (map[(int) north.x][(int) north.y] == wall))
				{
					if(multiple)
						walls.add(north);
					else if((map[(int) north.x][(int) (north.y - 1)] == wall))
						walls.add(north);
				}
				if((east.x + 1 < xSize) && (map[(int) east.x][(int) east.y] == wall))
				{
					if(multiple)
						walls.add(east);
					else if((map[(int) (east.x + 1)][(int) east.y] == wall))
						walls.add(east);
				}
				if((south.y + 1 < ySize) && (map[(int) south.x][(int) south.y] == wall))
				{
					if(multiple)
						walls.add(south);
					else if((map[(int) south.x][(int) (south.y + 1)] == wall))
						walls.add(south);
				}
				if((west.x > 0) && (map[(int) west.x][(int) west.y] == wall))
				{
					if(multiple)
						walls.add(west);
					else if((map[(int) (west.x - 1)][(int) west.y] == wall))
						walls.add(west);
				}
			}
			else
			{
				walls.remove(randomLoc);
			}
		}

		if(multiple)
			for(int y = 1; y < ySize - 1; y++)
				for(int x = 1; x < xSize - 1; x++)
					if(canRemove(new Vector2(x, y)))
						map[x][y] = nonWall;

		for(int y = 1; y < ySize - 1; y++)
		{
			for(int x = 1; x < xSize - 1; x++)
			{
				if(this.isWall(x, y))
					continue;
				if(numWallsAround(new Vector2(x, y)) == 3)
				{
					Vector2 otherLoc = new Vector2(x, y);
					if(!this.isWall(x, y - 1))
						otherLoc = new Vector2(x, y - 1);
					else if(!this.isWall(x, y + 1))
						otherLoc = new Vector2(x, y + 1);
					else if(!this.isWall(x - 1, y))
						otherLoc = new Vector2(x - 1, y);
					else if(!this.isWall(x + 1, y))
						otherLoc = new Vector2(x + 1, y);

					if(numWallsAround(otherLoc) < 2)
						map[x][y] = wall;
				}
			}
		}

		int endX = this.xSize - 2;
		int endY = this.ySize - 2;
		map[endX][endY] = nonWall;
		map[endX + 1][endY] = nonWall;
		boolean Inaccessible = true;
		int i = 1;
		while(Inaccessible)
		{
			map[(endX - i)][endY] = nonWall;
			map[endX][(endY - i)] = nonWall;
			i++;
			if((map[(endX - i)][endY] == nonWall) || (map[endX][(endY - i)] == nonWall) || (map[(endX - i)][endY - 1] == nonWall) || (map[endX - 1][(endY - i)] == nonWall))
				Inaccessible = false;
		}
		map[1][1] = nonWall;
		height = ySize * yWallScale;
		width = xSize * xWallScale;
		generated = true;
	}

	private boolean checkwalls(Vector2 loc)
	{
		Vector2 north = loc.cpy();
		north.add(0, -1);
		Vector2 east = loc.cpy();
		east.add(1, 0);
		Vector2 south = loc.cpy();
		south.add(0, 1);
		Vector2 west = loc.cpy();
		west.add(-1, 0);

		int yes = 0;
		if((north.y >= 0 && map[(int) north.x][(int) north.y] == nonWall) || north.y > ySize)
			yes++;
		if((east.x < xSize && map[(int) east.x][(int) east.y] == nonWall) || east.x > xSize)
			yes++;
		if((south.y < ySize && map[(int) south.x][(int) south.y] == nonWall) || south.y < 0)
			yes++;
		if((west.x >= 0 && map[(int) west.x][(int) west.y] == nonWall) || west.x < 0)
			yes++;
		return yes > 1;
	}

	public boolean canRemove(Vector2 loc)
	{
		if(!this.isWall((int) loc.x, (int) loc.y))
			return false;

		boolean[] directions = new boolean[8];

		for(int i = 0; i < directions.length; i++)
			directions[i] = true;

		if(this.isWall((int) loc.x, (int) loc.y - 1) && this.numWallsAroundFull(new Vector2((int) loc.x, (int) loc.y - 1)) > 4)
			directions[0] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y - 1) && this.numWallsAroundFull(new Vector2((int) loc.x + 1, (int) loc.y - 1)) > 4)
			directions[1] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y) && this.numWallsAroundFull(new Vector2((int) loc.x + 1, (int) loc.y)) > 4)
			directions[2] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y + 1) && this.numWallsAroundFull(new Vector2((int) loc.x + 1, (int) loc.y + 1)) > 4)
			directions[3] = false;
		if(this.isWall((int) loc.x, (int) loc.y + 1) && this.numWallsAroundFull(new Vector2((int) loc.x, (int) loc.y + 1)) > 4)
			directions[4] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y + 1) && this.numWallsAroundFull(new Vector2((int) loc.x - 1, (int) loc.y + 1)) > 4)
			directions[5] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y) && this.numWallsAroundFull(new Vector2((int) loc.x - 1, (int) loc.y)) > 4)
			directions[6] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y - 1) && this.numWallsAroundFull(new Vector2((int) loc.x - 1, (int) loc.y - 1)) > 4)
			directions[7] = false;

		int nonwallSeq = 0;
		boolean ok = true;
		int walls = 0;
		for(int i = 0; i < directions.length * 2; i++)
		{
			boolean bool = directions[i % directions.length];

			if(bool)
			{
				if(i < directions.length && i % 2 == 0)
					walls++;
				nonwallSeq++;
				if(nonwallSeq == 3)
					ok = false;
			}
			else
			{
				nonwallSeq = 0;
			}
		}
		return ok && walls > 1;
	}

	public int numWallsAroundFull(Vector2 loc)
	{

		boolean[] directions = new boolean[8];

		for(int i = 0; i < directions.length; i++)
			directions[i] = true;

		if(this.isWall((int) loc.x, (int) loc.y - 1))
			directions[0] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y - 1))
			directions[1] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y))
			directions[2] = false;
		if(this.isWall((int) loc.x + 1, (int) loc.y + 1))
			directions[3] = false;
		if(this.isWall((int) loc.x, (int) loc.y + 1))
			directions[4] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y + 1))
			directions[5] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y))
			directions[6] = false;
		if(this.isWall((int) loc.x - 1, (int) loc.y - 1))
			directions[7] = false;

		int total = 0;
		for(int i = 0; i < directions.length; i++)
			if(directions[i])
				total++;

		return total;
	}

	public int numWallsAround(Vector2 loc)
	{
		Vector2 north = loc.cpy();
		north.add(0, -1);
		Vector2 east = loc.cpy();
		east.add(1, 0);
		Vector2 south = loc.cpy();
		south.add(0, 1);
		Vector2 west = loc.cpy();
		west.add(-1, 0);

		int yes = 0;
		if(north.y >= 0 && map[(int) north.x][(int) north.y] == wall)
			yes++;
		if(east.x < xSize && map[(int) east.x][(int) east.y] == wall)
			yes++;
		if(south.y < ySize && map[(int) south.x][(int) south.y] == wall)
			yes++;
		if(west.x >= 0 && map[(int) west.x][(int) west.y] == wall)
			yes++;
		return yes;
	}

	public List<Vector2> getNonWallsAround(Vector2 loc)
	{
		List<Vector2> toReturn = new ArrayList<Vector2>();
		Vector2 north = loc.cpy();
		north.add(0, -1);
		Vector2 east = loc.cpy();
		east.add(1, 0);
		Vector2 south = loc.cpy();
		south.add(0, 1);
		Vector2 west = loc.cpy();
		west.add(-1, 0);

		if(north.y >= 0 && map[(int) north.x][(int) north.y] == nonWall)
			toReturn.add(north);
		if(east.x < xSize && map[(int) east.x][(int) east.y] == nonWall)
			toReturn.add(east);
		if(south.y < ySize && map[(int) south.x][(int) south.y] == nonWall)
			toReturn.add(south);
		if(west.x >= 0 && map[(int) west.x][(int) west.y] == nonWall)
			toReturn.add(west);
		return toReturn;
	}

	/**
	 * returns if the maze has been generated
	 * 
	 * @return is generated
	 */
	public boolean isGenrated()
	{
		return generated;
	}

	/**
	 * returns whether or not there is a wall in contact with the give location and size
	 * 
	 * @param x
	 *            location
	 * @param y
	 *            lovation
	 * @param size
	 *            of the object (only square)
	 * @return if there is a wall
	 */
	public boolean isWall(float x, float y, float size)
	{
		int x1 = (int) ((x - size) / xWallScale);
		int x2 = (int) ((x + size) / xWallScale);
		int y1 = (int) ((y - size) / yWallScale);
		int y2 = (int) ((y + size) / yWallScale);

		if(map[x1][y1] == wall)
		{
			return true;
		}
		if(map[x1][y2] == wall)
		{
			return true;
		}
		if(map[x2][y1] == wall)
		{
			return true;
		}
		if(map[x2][y2] == wall)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns if there is a wall at the given location
	 * 
	 * @param x
	 *            location
	 * @param y
	 *            location
	 * @return if there is a wall
	 */
	public boolean isWall(int x, int y)
	{
		if(y < 0)
			return true;
		if(x >= xSize)
			return true;
		if(y >= ySize)
			return true;
		if(x < 0)
			return true;
		if(map[x][y] == wall)
		{
			return true;
		}
		return false;
	}

	public void putWallAt(int x, int y)
	{
		map[x][y] = wall;
	}

	public void removeWallAt(int x, int y)
	{
		map[x][y] = nonWall;
	}

	/**
	 * gets a random location that doesnt have a wall
	 * 
	 * @return open location
	 */
	public Vector2 getFreeLoc()
	{
		int x = r.nextInt(xSize);
		int y = r.nextInt(ySize);
		boolean valid = false;
		while(!valid)
		{
			if(map[x][y] == nonWall)
			{
				valid = true;
			}
			else
			{
				x = r.nextInt(xSize);
				y = r.nextInt(ySize);
			}
		}
		return new Vector2(x, y);
	}

	/**
	 * gets the width of the maze
	 * 
	 * @return width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * gets the height of the maze
	 * 
	 * @return height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * returns the size of the walls x direction
	 * 
	 * @return x Wall size
	 */
	public int getxWallScale()
	{
		return xWallScale;
	}

	/**
	 * returns the size of the walls y direction
	 * 
	 * @return y Wall size
	 */
	public int getyWallScale()
	{
		return yWallScale;
	}

	/**
	 * gets the full width of the maze
	 * 
	 * @return x Size
	 */
	public int getXSize()
	{
		return xSize;
	}

	/**
	 * gets the full height of the maze
	 * 
	 * @return x Size
	 */
	public int getYSize()
	{
		return ySize;
	}

	/**
	 * returns the maze array
	 * 
	 * @return maze array
	 */
	public int[][] getMazeMap()
	{
		return map;
	}
}