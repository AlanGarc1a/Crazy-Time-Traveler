package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.game.CrazyTimeTraveler;

public class GamePreferences {

    private static final String PREF_MUSIC = "music";
    private static final String APP_NAME = "CrazyTimeTraveler";

    private Preferences preferences;
    private CrazyTimeTraveler game;

    public GamePreferences(final CrazyTimeTraveler game){
        this.game = game;
        preferences = Gdx.app.getPreferences(APP_NAME);
    }

    public Preferences getPrefs(){
        if(preferences == null){
            preferences = Gdx.app.getPreferences(APP_NAME);
        }

        return  preferences;
    }

    public void setMusicState(boolean musicState){
        getPrefs().putBoolean(PREF_MUSIC, musicState);
        getPrefs().flush();
    }

    public boolean isMusicEnabled(){
        return getPrefs().getBoolean(PREF_MUSIC, true);
    }
}
