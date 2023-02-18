package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

import extras.Utils;

public class GOverScreen extends BasicScreen {
    //El escenario, el mundo, la camara y el fondo del screen
    protected Stage stage;
    protected World world;
    protected OrthographicCamera orthographicCamera;
    protected Image background;
    //El objeto que maneja el sonido de perder
    private Sound loseSound;
    //La camara que pondremos la fuente
    private OrthographicCamera fontCamera;
    private BitmapFont font;

    public GOverScreen(MainGame mainGame) {
        super(mainGame);
        //Crearemos el mundo con un vector
        this.world = new World(new Vector2(0, -10), true);
        //Crearemos un fitviewport para tener una forma de escalar sin tener encuenta los pixeles de la pantalla
        FitViewport fitViewport = new FitViewport((Utils.WORLD_WIDTH * 0.84f), (Utils.WORLD_HEIGHT * 1.1f));
        this.stage = new Stage(fitViewport);
        //Crearemos la camara desde el escenario
        this.orthographicCamera = (OrthographicCamera) this.stage.getCamera();
        this.loseSound = this.getMainGame().assetsManager.getloseSound();
        prepareScore();
    }
    //Mostraremos el background y el sonido de perder cuando mostremos la screen
    @Override
    public void show() {
        addBackground();
        this.loseSound.play();
    }

    /**
     * Representaremos la puntuacion de la partida anterior y si pulsamos en la pantalla jugermos
     * otra vez
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
        this.stage.getBatch().setProjectionMatrix(this.fontCamera.combined);
        this.stage.getBatch().begin();
        this.font.draw(this.stage.getBatch(),"Puntuacion = "+points,40,400);
        this.stage.getBatch().end();
    }

    /**
     * Prepararemos la fuente para representar la puntuacion de la partida anterior
     */
    private void prepareScore(){
        this.font = this.getMainGame().assetsManager.getFontWhite();
        this.font.getData().scale(1f);
        this.fontCamera = new OrthographicCamera();
        this.fontCamera.setToOrtho(false, Utils.SCREEN_WIDTH,Utils.SCREEN_HEIGHT);
        this.fontCamera.update();
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
        this.background = new Image(getMainGame().assetsManager.getBgGO());
        this.background.setPosition(0,0);
        this.background.setSize((Utils.WORLD_WIDTH*0.84f),(Utils.WORLD_HEIGHT*1.1f));
        this.stage.addActor(background);
    }
}
