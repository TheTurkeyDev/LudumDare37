package com.theprogrammingturkey.ld37;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.theprogrammingturkey.ld37.screens.EndScreen;
import com.theprogrammingturkey.ld37.screens.GameScreen;
import com.theprogrammingturkey.ld37.screens.GameSettingsScreen;
import com.theprogrammingturkey.ld37.screens.HelpScreen;
import com.theprogrammingturkey.ld37.screens.LeaderBoardScreen;
import com.theprogrammingturkey.ld37.screens.MainScreen;
import com.theprogrammingturkey.ld37.screens.ScreenManager;
import com.theprogrammingturkey.ld37.screens.SettingsScreen;
import com.theprogrammingturkey.ld37.sounds.SoundManager;

public class LudumDare37 extends ApplicationAdapter
{

	@Override
	public void create()
	{
		ScreenManager.INSTANCE.addScreen(new MainScreen());
		ScreenManager.INSTANCE.addScreen(new GameScreen());
		ScreenManager.INSTANCE.addScreen(new GameSettingsScreen());
		ScreenManager.INSTANCE.addScreen(new EndScreen());
		ScreenManager.INSTANCE.addScreen(new HelpScreen());
		ScreenManager.INSTANCE.addScreen(new SettingsScreen());
		ScreenManager.INSTANCE.addScreen(new LeaderBoardScreen());

		ScreenManager.INSTANCE.setCurrentScreen("Main Screen");
	}

	@Override
	public void render()
	{
		ScreenManager.INSTANCE.updateScreen();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ScreenManager.INSTANCE.renderScreen();
	}

	@Override
	public void dispose()
	{
		SoundManager.disposeSounds();
	}

}
