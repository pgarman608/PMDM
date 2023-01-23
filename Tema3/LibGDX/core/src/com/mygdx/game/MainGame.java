package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

import Screen.GameScreen;

/**
 * TODO 1: Tendremos los
 */
public class MainGame extends Game {
    protected GameScreen gameScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
