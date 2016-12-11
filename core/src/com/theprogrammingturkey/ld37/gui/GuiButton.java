package com.theprogrammingturkey.ld37.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.theprogrammingturkey.ld37.graphics.Draw2D;

public class GuiButton extends GuiComponent
{
	private Texture texture;

	public GuiButton(int id, float x, float y, float width, float height, String text, Texture texture)
	{
		super(id, x, y, width, height);
		this.texture = texture;
		this.displayText = text;
	}

	public void render()
	{
		if(!visible)
			return;
		if(texture != null)
			Draw2D.drawTextured(x, y, width, height, texture);
		Draw2D.drawString(x + 60, y + (this.height / 2) + 18, this.displayText, 1.75f, Color.GREEN);
	}

	public Texture getTexture()
	{
		return this.texture;
	}
}
