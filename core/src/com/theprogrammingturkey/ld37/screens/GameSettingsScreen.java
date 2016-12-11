package com.theprogrammingturkey.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld37.game.GameData;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;
import com.theprogrammingturkey.ld37.gui.GuiSlider;

public class GameSettingsScreen extends Screen
{
	private GuiSlider wallsSlider;
	private GuiSlider enemiesSlider;

	public GameSettingsScreen()
	{
		super("Game Settings Screen");
		this.addGuiComponent(new GuiButton(0, ((Gdx.graphics.getWidth() - 400) / 2), 50, 400, 200, "START", Resources.BUTTON));
		wallsSlider = new GuiSlider(1, ((Gdx.graphics.getWidth() - 300) / 2), 250, 300, 200, Resources.SLIDER, Resources.SLIDER_LINE, 1, 10, 5, 1);
		this.addGuiComponent(wallsSlider);
		enemiesSlider = new GuiSlider(2, ((Gdx.graphics.getWidth() - 300) / 2), 450, 300, 200, Resources.SLIDER, Resources.SLIDER_LINE, 1, 25, 2, 1);
		this.addGuiComponent(enemiesSlider);
	}

	public void onScreenUnload()
	{
		GameData.enemies = Integer.parseInt(enemiesSlider.getCurrentValue("#0"));
		GameData.walls = Integer.parseInt(wallsSlider.getCurrentValue("#0"));
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Game Screen");
	}

	public void update()
	{
		super.update();
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.BUSH_BACKGROUND);
		Draw2D.drawString2(520, 425, "Walls Available: " + wallsSlider.getCurrentValue("#0"), 1, Color.WHITE);
		Draw2D.drawString2(550, 625, "Enemies: " + enemiesSlider.getCurrentValue("#0"), 1, Color.WHITE);
		super.render();
	}

}
