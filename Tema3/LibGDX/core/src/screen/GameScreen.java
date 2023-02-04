package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

import actores.Bird;
import actores.Pipes;
import actores.Suelo;
import actores.Techo;
import extras.Utils;

public class GameScreen extends BasicScreen {
    public Bird bird;
    public Suelo suelo;
    public Techo techo;
    public Pipes pipes;

    public  Stage stage;

    private Image background;

    private World world;

    //Depuracion

    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera ortCamera;

    private Music musicaBG;

    public GameScreen(MainGame mainGame){
        super(mainGame);

        this.world = new World(new Vector2(0,-10),true);
        FitViewport fitViewport = new FitViewport(Utils.WORLD_WIDTH,Utils.WORLD_HEIGHT);
        this.stage = new Stage(fitViewport);

        this.ortCamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();
    }
    public void addBackground(){
        this.background = new Image(mainGame.assetManager.getBackground());
        this.background.setPosition(0,0);
        this.background.setSize(Utils.WORLD_WIDTH,Utils.WORLD_HEIGHT);
        this.stage.addActor(this.background);
    }
    public void addBird(){
        Animation<TextureRegion> birdSprite = mainGame.assetManager.getBirdAnimation();
        this.bird = new Bird(this.world,birdSprite, new Vector2(1f,4f),mainGame.assetManager.getSonido_pajaro1());
        this.stage.addActor(this.bird);
    }
    public void addSueloTecho(){
        TextureRegion textureRegion = mainGame.assetManager.getSuelo();
        this.suelo = new Suelo(this.world,new Vector2(0.02f,0.25f), textureRegion);
        this.techo = new Techo(this.world,new Vector2(0.02f, 7.8f));
        this.stage.addActor(this.suelo);
        this.stage.addActor(this.techo);
    }
    public void addPipes(){
        TextureRegion pipeI = mainGame.assetManager.getColumn1();
        TextureRegion pipeS = mainGame.assetManager.getColumn2();
        this.pipes = new Pipes(this.world,pipeI, pipeS,new Vector2(4f,2f));
        this.stage.addActor(this.pipes);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();

        this.debugRenderer.render(this.world, this.ortCamera.combined);


    }
    @Override
    public void show() {
        addBackground();
        addBird();
        addPipes();
        addSueloTecho();

        musicaBG = mainGame.assetManager.getMusica_bg();
        musicaBG.setLooping(true);
        musicaBG.play();
    }

    @Override
    public void hide() {
        this.bird.detach();
        this.suelo.detach();
        this.techo.detach();
        this.pipes.detach();

        this.bird.remove();
        this.suelo.remove();
        this.techo.remove();
        this.pipes.remove();

        this.musicaBG.stop();
    }

    @Override
    public void dispose() {

        this.stage.dispose();
        this.world.dispose();

        this.musicaBG.stop();
    }
}
