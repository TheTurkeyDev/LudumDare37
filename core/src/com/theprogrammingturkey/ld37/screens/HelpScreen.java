package com.theprogrammingturkey.ld37.screens;

import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;

public class HelpScreen extends Screen
{
	public HelpScreen()
	{
		super("Help Screen");
		this.addGuiComponent(new GuiButton(0, 475, -50, 400, 200, "[Back]", null));
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
	}

	public void update()
	{
		super.update();
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.HELP_BACKGROUND);
		super.render();
	}
}
