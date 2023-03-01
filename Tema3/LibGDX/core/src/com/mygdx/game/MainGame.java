package com.mygdx.game;

import com.badlogic.gdx.Game;

import extras.AssetMan;
import screen.GameScreen;

/**
 * TODO : TENDREMOS LA PANTALLA PRINCIPAL CON LOS ASSETS ASIGNADOS
 */
public class MainGame extends Game {
    protected GameScreen gameScreen;
    public AssetMan assetManager;
    @Override
    public void create() {
        this.assetManager = new AssetMan();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }
}
