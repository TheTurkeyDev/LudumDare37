package com.theprogrammingturkey.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;

public class MainScreen extends Screen
{

	public MainScreen()
	{
		super("Main Screen");
		this.addGuiComponent(new GuiButton(0, 100, 200, 400, 200, "Start", Resources.BUTTON));
		this.addGuiComponent(new GuiButton(1, Gdx.graphics.getWidth() - 500, 200, 400, 200, "Help", Resources.BUTTON));
		this.addGuiComponent(new GuiButton(2, 100, 0, 400, 200, "Settings", Resources.BUTTON));
		this.addGuiComponent(new GuiButton(3, Gdx.graphics.getWidth() - 500, 0, 400, 200, "Scores", Resources.BUTTON));
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Game Settings Screen");
		else if(guic.getId() == 1)
			ScreenManager.INSTANCE.setCurrentScreen("Help Screen");
		else if(guic.getId() == 2)
			ScreenManager.INSTANCE.setCurrentScreen("Settings Screen");
		else if(guic.getId() == 3)
			ScreenManager.INSTANCE.setCurrentScreen("Leaderboard Screen");
	}

	public void update()
	{
		super.update();
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.MAIN_BACKGROUND);
		super.render();
	}

}
