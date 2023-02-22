package pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

import extras.Utils;

public class BasicScreen implements Screen {
    //El escenario, el mundo, la camara y el fondo del screen
    protected OrthographicCamera orthographicCamera;
    protected Stage stage;
    protected World world;
    protected Image background;
    //Tendremos un int estático que guardará los puntos del jugador conseguidos
    protected static int points;
    //Tendremos un objeto que guarde el MainGame donde estemos jugando
    private MainGame mainGame;
    /**
     * Un constructor por parametro donde guardaremos el mainGame donde estemos jugando
     * @param mainGame
     */
    public BasicScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        //Crearemos el mundo con un vector
        this.world = new World(new Vector2(0, -10), true);
        //Crearemos un fitviewport para tener una forma de escalar sin tener encuenta los pixeles de la pantalla
        FitViewport fitViewport = new FitViewport((Utils.WORLD_WIDTH * 0.84f), (Utils.WORLD_HEIGHT * 1.1f));
        this.stage = new Stage(fitViewport);
        //Crearemos la camara desde el escenario
        this.orthographicCamera = (OrthographicCamera) this.stage.getCamera();
    }

    /**
     * Métodos que implementa la clase Screen vacios
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //Getter del maingame
    public MainGame getMainGame() {
        return mainGame;
    }
}
