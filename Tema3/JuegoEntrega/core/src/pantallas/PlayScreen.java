package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

import actores.Phoenix;
import extras.DataPhoenix;
import actores.BluePhoenix;
import actores.OranjePhoenix;
import actores.PinkPhoenix;
import actores.GunSight;
import extras.Utils;

/**
 * Esta es la pantalla donde mataremos a los fenix
 */

public class PlayScreen extends BasicScreen implements ContactListener{
    //La imagen de las vidas
    private Image heart;
    //La imagen del fenix cazado
    private Image birdHunt;
    //La lista de fenix con el tiempo de aparición por fenix
    private Array<Phoenix> birds;
    private float deltaBirds;
    //La camara donde mostraremos las fuentes
    private OrthographicCamera fontCamera;
    private BitmapFont font;
    //La musica del juego y el sonido del fenix rosa
    private Sound phoenixSound;
    private Music mainTheme;
    //La mira que usaremos como indicador de la pulsacion
    private GunSight gunSight;
    //La vida del jugadores
    private int lifes = 6;

    public PlayScreen(MainGame mainGame) {
        super(mainGame);
        this.world.setContactListener(this);

        this.birds = new Array<>();
        this.points = 0;
        //Prepararemos las fuentes para los puntos y las vidas
        prepareScore();
        //Inicializaremos la musica del juego y el sonido de los fenix
        this.phoenixSound = this.getMainGame().assetsManager.getPhoenixSound();
        this.mainTheme = this.getMainGame().assetsManager.getMainTheme();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Por cada ciclo del render mostraremos los fenix
        addBirds(delta);
        //Mostraremos la mira
        addGunSight();

        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        //Eliminaremos los fenix
        removeBirds();
        //Si la mira no es nulo se maltendra al frente de la pantalla
        if (gunSight != null){
            gunSight.toFront();
        }
        //Si tienes menos de una vida aparecerá la pantalla de gameover
        if (lifes < 1){
            getMainGame().setScreen(new GOverScreen(getMainGame()));
        }
        //Si tiene mas de 999 puntos aparecerá la pantalla de win
        if (points >= 999){
            getMainGame().setScreen(new WinScreen(getMainGame()));
        }
        this.stage.getBatch().setProjectionMatrix(this.fontCamera.combined);
        this.stage.getBatch().begin();
        //Aparecerá los puntos y las vidas con la fuente inicializada en el constructor
        this.font.draw(this.stage.getBatch(),""+points,325,770);
        this.font.draw(this.stage.getBatch(),"x"+lifes,100,770);
        this.stage.getBatch().end();
    }

    /**
     * Este método añade al escenario la mira cuando toquemos por primera vez la pantalla
     */
    private void addGunSight(){
        if (Gdx.input.isTouched() && gunSight == null) {
            Vector3 tocar;
            tocar = orthographicCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            this.gunSight = new GunSight(world,getMainGame().assetsManager.getGunSight(),new Vector2(tocar.x,tocar.y));
            stage.addActor(gunSight);
        }
    }

    /**
     * Cada 0.85s saldra un fenix, calcularemos la ubicación de aparición del fenix.
     * hay un 50% de posibildades de que salga un fenix naranga
     * hay un 30% de posibildades de que salga un fenix azul
     * hay un 20% de posibildades de que salga un fenix rosa
     * @param delta
     */
    public void addBirds(float delta){
        //Vamos sumando y guardando delta en una variable de clase
        this.deltaBirds += delta;
        //Si es mayor o igual a 0.85 le restaremos ese mismo tiempo
        if (this.deltaBirds >= 0.85f) {
            this.deltaBirds -= 0.85f;
            //Calcularemos la posicion donde sale el fenix
            Vector2 xybird = getxyBird();
            //Saquaremos un numero entre el 1 al 10 para saber que fenix va a salir
            int rnd = MathUtils.random(1,10);
            if (rnd <=5){
                OranjePhoenix FNAux = new OranjePhoenix(world,
                        getMainGame().assetsManager.getBirdNIAnimation(),
                        getMainGame().assetsManager.getBirdNDAnimation(),
                        getMainGame().assetsManager.getBirdNAAnimation(),
                        xybird);
                this.birds.add(FNAux);
                this.stage.addActor(FNAux);
            }else{
                if (rnd <=8){
                    BluePhoenix FAAux = new BluePhoenix(world,
                            getMainGame().assetsManager.getBirdAIAnimation(),
                            getMainGame().assetsManager.getBirdADAnimation(),
                            getMainGame().assetsManager.getBirdAAAnimation(),
                            xybird);
                    this.birds.add(FAAux);
                    this.stage.addActor(FAAux);

                }else{
                    PinkPhoenix FRAux = new PinkPhoenix(world,
                            getMainGame().assetsManager.getBirdRIAnimation(),
                            getMainGame().assetsManager.getBirdRDAnimation(),
                            getMainGame().assetsManager.getBirdRAAnimation(),
                            xybird);
                    this.birds.add(FRAux);
                    this.stage.addActor(FRAux);
                    this.stage.addAction(Actions.sequence(Actions.delay(0.5f),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            phoenixSound.play();
                        }
                    })));
                }
            }
        }
    }

    /**
     * Mostraremos por pantalla el background, la imagen de las vidas y la fenix cazados.
     */
    @Override
    public void show() {
        addBackground();
        addHeart();
        addBirdHunt();
        this.mainTheme.play();
    }

    /**
     * Metodo que mostrará gracias al escenario el corazon
     */
    private void addHeart() {
        this.heart = new Image(getMainGame().assetsManager.getHeart());
        this.heart.setPosition(-0.3f,7.5f);
        this.heart.setSize(1.5f,1.5f);
        this.stage.addActor(heart);
    }
    /**
     * Metodo que mostrará gracias al escenario el fenix cazado
     */
    private void addBirdHunt() {
        this.birdHunt = new Image(getMainGame().assetsManager.getBirdHunt());
        this.birdHunt.setPosition(1.695f,7.65f);
        this.birdHunt.setSize(1.1f,1f);
        this.stage.addActor(birdHunt);
    }

    /**
     * Calcularemos la posición de aparición de cada fenix
     * @return
     */
    private Vector2 getxyBird() {
        float y = MathUtils.random(1.25f,6.85f);
        float x;
        if (MathUtils.random(1,2) == 1){
            x = -0.7f;
        }else{
            x = 5.6f;
        }
        return new Vector2(x,y);
    }

    /**
     * Eliminaremos los fenix que salgan de la pantalla y eliminaremos una vida si salen fenix vivos
     */
    private void removeBirds(){
        for (Phoenix fenix : birds) {
            if (fenix.isOutOfScreen() && fenix != null){
                if (fenix.getState() == Phoenix.STATE_LIVE){
                    lifes --;
                }
                fenix.detach();
                fenix.remove();
                birds.removeValue(fenix,false);
            }
        }
    }

    /**
     * Prepararemos la fuente donde mostraremos las vidas y los puntos
     */
    private void prepareScore(){
        this.font = this.getMainGame().assetsManager.getFontRed();
        this.font.getData().scale(1f);
        this.fontCamera = new OrthographicCamera();
        this.fontCamera.setToOrtho(false, Utils.SCREEN_WIDTH,Utils.SCREEN_HEIGHT);
        this.fontCamera.update();
    }
    /**
     * Crearemos y añadiremos la imagen del fondo al stage
     */
    protected void addBackground() {
        this.background = new Image(getMainGame().assetsManager.getBgPlay());
        this.background.setPosition(0,0);
        this.background.setSize((Utils.WORLD_WIDTH*0.84f),(Utils.WORLD_HEIGHT*1.1f));
        this.stage.addActor(background);
    }

    /**
     * Cuando ocultemos la pantalla eliminaremos la mira y pausermos la musica
     */
    @Override
    public void hide() {
        this.gunSight.detach();
        this.gunSight.remove();
        this.mainTheme.stop();
    }

    /**
     * Cuandos salgamos de la aplicacion eliminaremos el escenario, el mundo y la misica
     */
    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
        this.mainTheme.stop();
    }

    /**
     * Recogeremos el data del fenix de la colisionando
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {
        DataPhoenix fenix = new DataPhoenix();
        if (contact.getFixtureA() != null || contact.getFixtureB() != null){
            if (contact.getFixtureA().getUserData() instanceof DataPhoenix){
                fenix = (DataPhoenix) contact.getFixtureA().getUserData();
            }else{
                if (!(contact.getFixtureA().getUserData() instanceof DataPhoenix)){
                    fenix = (DataPhoenix) contact.getFixtureB().getUserData();
                }
            }
            matarPajaro(fenix);
        }
    }

    /**
     * Comprobaremos la id del fenix y que su estado es vivo.
     * Lo mataremos y sumaremos puntos a la puntuación del usuario
     * @param dataPhoenix
     */
    private void matarPajaro(DataPhoenix dataPhoenix){
        for (Phoenix fenix : birds) {
            if (fenix.getData().getIdIndiv() == dataPhoenix.getIdIndiv() && fenix.getState() == Phoenix.STATE_LIVE) {
                fenix.setState(Phoenix.STATE_DEAD);
                    switch (dataPhoenix.getUserData()){
                        case Utils.DATA_BIRDN:
                            this.points += 5;
                            break;
                        case Utils.DATA_BIRDA:
                            this.points += 10;
                            break;
                        case Utils.DATA_BIRDR:
                            this.points += 20;
                            break;
                    }
                if (this.points >= 1000){
                    this.points = 999;
                }
            }
        }
    }
    //Metodos del contact listenner que no se van a usar
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}