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
