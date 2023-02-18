package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
 * Esta screen se mostrará cuando el usuario tenga 999 puntos en la partida
 */
public class WinScreen extends BasicScreen {
    //El escenario, el mundo, la camara y el fondo del screen
    private Sound winSound;
    protected Stage stage;
    protected World world;
    protected OrthographicCamera orthographicCamera;
    protected Image background;
    public WinScreen(MainGame mainGame) {
        super(mainGame);
        //Crearemos el mundo con un vector
        this.world = new World(new Vector2(0, -10), true);
        //Crearemos un fitviewport para tener una forma de escalar sin tener encuenta los pixeles de la pantalla
        FitViewport fitViewport = new FitViewport((Utils.WORLD_WIDTH * 0.84f), (Utils.WORLD_HEIGHT * 1.1f));
        this.stage = new Stage(fitViewport);
        //Crearemos la camara desde el escenario
        this.orthographicCamera = (OrthographicCamera) this.stage.getCamera();
        //Crearemos el sonido de cuando gana la partida
        winSound = this.getMainGame().assetsManager.getWinSound();
    }
    /**
     * Mostraremos el fondo de pantalla y el sonido cuando empezemos la screen
     */
    @Override
    public void show() {
        addBackground();
        winSound.play();
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
            getMainGame().setScreen(new PlayScreen(getMainGame()));
        }
    }
    /**
     * Crearemos y añadiremos la imagen del fondo al stage
     */
    protected void addBackground() {
        this.background = new Image(getMainGame().assetsManager.getBgWin());
        this.background.setPosition(0,0);
        this.background.setSize((Utils.WORLD_WIDTH*0.84f),(Utils.WORLD_HEIGHT*1.1f));
        this.stage.addActor(background);
    }
}
