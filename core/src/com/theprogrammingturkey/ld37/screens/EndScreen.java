package com.theprogrammingturkey.ld37.screens;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld37.game.GameData;
import com.theprogrammingturkey.ld37.graphics.Draw2D;
import com.theprogrammingturkey.ld37.graphics.Resources;
import com.theprogrammingturkey.ld37.gui.GuiButton;
import com.theprogrammingturkey.ld37.gui.GuiComponent;
import com.theprogrammingturkey.ld37.gui.GuiTextBox;
import com.theprogrammingturkey.ld37.util.DataBaseConnect;

public class EndScreen extends Screen
{
	private boolean isValidUsername =  false;
	private boolean wasScoreSent =  false;

	private GuiButton submit;
	private GuiTextBox userName;

	public EndScreen()
	{
		super("End Screen");
		this.addGuiComponent(new GuiButton(0, 800, 10, 400, 200, "Menu", Resources.BUTTON));
		this.addGuiComponent(new GuiButton(1, 800, 225, 400, 200, "Retry", Resources.BUTTON));
		this.addGuiComponent(submit = new GuiButton(2, 200, 10, 400, 200, "Submit", Resources.BUTTON));
		this.addGuiComponent(userName = new GuiTextBox(3, 200, 225, 400, 200, Resources.BUTTON));
	}

	public void onScreenLoad()
	{
		isValidUsername = false;
		wasScoreSent = false;
		submit.setVisible(GameData.won);
		userName.setVisible(GameData.won);
	}

	public void onComponentClicked(GuiComponent guic)
	{
		if(guic.getId() == 0)
			ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
		else if(guic.getId() == 1)
			ScreenManager.INSTANCE.setCurrentScreen("Game Screen");
		else if(guic.getId() == 2)
		{
			if(userName.getDisplayText().length() > 0)
			{
				DataBaseConnect.sendData(userName.getDisplayText().trim(), GameData.gameTime, GameData.walls, GameData.enemies);
				this.isValidUsername = false;
				this.wasScoreSent = true;
				guic.setVisible(false);
			}
			else
			{
				this.isValidUsername = true;
			}
		}
	}

	public void update()
	{
		super.update();
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, Resources.BUSH_BACKGROUND);
		if(GameData.won)
			Draw2D.drawString(350, 600, "YOU WIN!", 3, Color.BROWN);
		else
			Draw2D.drawString(275, 600, "GAME OVER", 3, Color.BROWN);

		if(GameData.won)
		{
			Draw2D.drawString(200, 475, "Username:", 2f, Color.WHITE);
			if(this.isValidUsername)
				Draw2D.drawString(100, 250, "Invalid Username!", 2f, Color.RED);
			if(this.wasScoreSent)
				Draw2D.drawString(200, 250, "Score Sent!", 2f, Color.GREEN);
		}
		super.render();
	}
}