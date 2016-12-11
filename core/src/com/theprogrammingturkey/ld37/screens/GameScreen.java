package com.theprogrammingturkey.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld37.game.GameManager;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;

public class GameScreen extends Screen
{
	private GameManager game;

	private GuiButton resume;
	private GuiButton restart;
	private GuiButton main;

	public GameScreen()
	{
		super("Game Screen");
		game = new GameManager();
		this.addGuiComponent(resume = new GuiButton(0, 25, 150, 400, 200, "Resume", Resources.BUTTON));
		this.addGuiComponent(restart = new GuiButton(1, 425, 150, 400, 200, "Restart", Resources.BUTTON));
		this.addGuiComponent(main = new GuiButton(2, 825, 150, 400, 200, "Main", Resources.BUTTON));
	}

	public void onScreenLoad()
	{
		game.initGame();
		resume.setVisible(false);
		restart.setVisible(false);
		main.setVisible(false);
	}

	public void update()
	{
		game.update();
	}

	public void render()
	{
		game.renderMaze();
		game.renderEntities();
		Draw2D.drawString(20, Gdx.graphics.getHeight() - 20, "Game Time:  " + game.getGameTime(), 1, Color.WHITE);

		Draw2D.drawString(500, Gdx.graphics.getHeight() - 20, "WallsAvailable : " + game.getWallsAvailable(), 1, Color.WHITE);

		if(game.isPaused)
		{
			Draw2D.drawString(375, 500, "PAUSED", 3, Color.RED);
		}

		super.render();
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			togglePause();
		else if(guic.getId() == 1)
			ScreenManager.INSTANCE.setCurrentScreen("Game Screen");
		else if(guic.getId() == 2)
			ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(!game.isPaused)
		{
			game.placeWall(screenX, Gdx.graphics.getHeight() - screenY);
			return true;
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.ESCAPE)
		{
			this.togglePause();
			return true;
		}
		return super.keyDown(keycode);
	}

	public void togglePause()
	{
		game.togglePause();
		resume.setVisible(game.isPaused);
		restart.setVisible(game.isPaused);
		main.setVisible(game.isPaused);
	}

}
