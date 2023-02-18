package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.World;

import extras.AssetsManager;
import pantallas.GOverScreen;
import pantallas.PlayScreen;
import pantallas.StartScreen;
import pantallas.TutorialScreen;
import pantallas.WinScreen;

/**
 * Tendremos la clase MainActivity que la utilizaremos para ir cambiando de Pantalla principal del
 * juego
 */
public class MainGame extends Game {
    //Tendremos un objeto de AssetsManager que nos permite
    public AssetsManager assetsManager;
    //Tendremos las tres primeras pantallas del juego
    public PlayScreen playScreen;
    public StartScreen startScreen;
    public TutorialScreen tutorialScreen;

    @Override
    public void create() {
        //Inicializaremos las variables
        this.assetsManager = new AssetsManager();
        this.tutorialScreen = new TutorialScreen(this);
        this.playScreen = new PlayScreen(this);
        this.startScreen = new StartScreen(this);
        //Pondremos como primera pantalla el startScreen
        setScreen(this.startScreen);
    }
}
