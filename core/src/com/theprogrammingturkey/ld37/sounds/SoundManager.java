package com.theprogrammingturkey.ld37.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.theprogrammingturkey.ld37.game.Settings;

public class SoundManager
{
	public static final Sound BUTTON_PRESS = Gdx.audio.newSound(Gdx.files.internal("sounds/button.wav"));
	public static final Sound BUSH_PLACE = Gdx.audio.newSound(Gdx.files.internal("sounds/bush_place.wav"));
	public static final Sound BUSH_REMOVE = Gdx.audio.newSound(Gdx.files.internal("sounds/bush_remove.wav"));
	public static final Sound WIN = Gdx.audio.newSound(Gdx.files.internal("sounds/win.wav"));
	public static final Sound LOSE = Gdx.audio.newSound(Gdx.files.internal("sounds/lose.wav"));

	public static void playSound(Sound sound, float volume)
	{
		if(Settings.sounds)
			sound.play(volume);
	}

	public static void disposeSounds()
	{
		BUTTON_PRESS.dispose();
		BUSH_PLACE.dispose();
		BUSH_REMOVE.dispose();
		WIN.dispose();
		LOSE.dispose();
	}
}
