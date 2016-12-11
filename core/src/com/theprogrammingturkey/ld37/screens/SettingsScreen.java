package com.theprogrammingturkey.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld37.game.Settings;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;
import com.theprogrammingturkey.ld37.gui.GuiRadioButton;
import com.theprogrammingturkey.ld37.gui.GuiSlider;

public class SettingsScreen extends Screen
{
	private GuiRadioButton sounds;
	private GuiSlider soundLevel;

	public SettingsScreen()
	{
		super("Settings Screen");
		this.addGuiComponent(new GuiButton(0, (Gdx.graphics.getWidth() / 2) - 169, 10, 338, 75, "[Back]", null));
		super.addGuiComponent(sounds = new GuiRadioButton(1, 300, Gdx.graphics.getHeight() - 225, 16, 16, true));
		soundLevel = new GuiSlider(1, 425, Gdx.graphics.getHeight() - 410, 300, 200, Resources.SLIDER, Resources.SLIDER_LINE, 0, 100, 100, 1);
		super.addGuiComponent(soundLevel);
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.BUSH_BACKGROUND);
		super.render();
		Draw2D.drawString(350, Gdx.graphics.getHeight() - 10, "SETTINGS", 3f, Color.WHITE);
		Draw2D.drawString(100, Gdx.graphics.getHeight() - 200, "Sounds", 1f, Color.WHITE);
		Draw2D.drawString(100, Gdx.graphics.getHeight() - 300, "Sound Level : " + soundLevel.getCurrentValue("##0"), 1f, Color.WHITE);
	}

	public void update()
	{
		Settings.sounds = sounds.isSelected();
		Settings.soundLevel = Integer.parseInt(soundLevel.getCurrentValue("##0"));
	}
}
