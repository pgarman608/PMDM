package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

import extras.Utils;

/**
 * Esta Clase es la primera que se mostrará en el juego, es decir, la portada.
 */
public class StartScreen extends BasicScreen {
    public StartScreen(MainGame mainGame) {
        super(mainGame);
    }
    /**
     * Mostraremos el fondo de pantalla cuando empezemos la screen
     */
    @Override
    public void show() {
        addBackground();
    }
    /**
     * Mostraremos todos los elementos del escenario.
     * Cuandp pulsemos en la pantalla cambiaremos la pantalla
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        if (Gdx.input.justTouched()){
            getMainGame().setScreen(getMainGame().tutorialScreen);
        }
    }
    /**
     * Cuando se cierre o se cambie de ventana se eliminaran el mundo y el escenario
     */
    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }
    /**
     * Crearemos y añadiremos la imagen del fondo al stage
     */
    protected void addBackground() {
        this.background = new Image(getMainGame().assetsManager.getBgStart());
        this.background.setPosition(0,0);
        this.background.setSize((Utils.WORLD_WIDTH*0.84f),(Utils.WORLD_HEIGHT*1.1f));
        this.stage.addActor(background);
    }
}
