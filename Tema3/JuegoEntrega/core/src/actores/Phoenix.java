package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import extras.DataPhoenix;

public class Phoenix extends Actor {
    //Este objeto es el mundo donde va a estar nuesto fenix
    protected World world;
    //Este objeto es el cuerpo fisico, caja de colisiones y la posición del fenix
    private Fixture fixture;
    protected Body body;
    protected Vector2 pos;
    //Las animaciones cuando el pajaro mire a la izquierda, a la derecha y abajo
    protected Animation<TextureRegion> animationIz;
    protected Animation<TextureRegion> animationDe;
    protected Animation<TextureRegion> animationAb;

    protected float statetime;
    //Los estados del fenix que son vivo y muerto
    public static final int STATE_LIVE = 1;
    public static final int STATE_DEAD = -1;
    //El estado del fenix individual
    protected int state;
    //El objeto en el que guardaremos tanto el userdata de cada tipo de fenix y el identificador de cada fenix individual
    private DataPhoenix data;
    public Phoenix(World world, Animation<TextureRegion> animaIz, Animation<TextureRegion> animaDe, Animation<TextureRegion> animaAb , Vector2 pos, String data){
        this.animationAb = animaAb;
        this.animationDe = animaDe;
        this.animationIz = animaIz;
        this.pos = pos;
        this.world = world;
        //Indicamos que el estado del fenix es vivo
        this.state = STATE_LIVE;
        statetime = 0;
        //Creaermos el Userdata del fenix
        this.data = new DataPhoenix(data);
        createbody();
        createFixture();
    }

    /**
     * Crearemos el body que será kinematico
     */
    private void createbody() {
        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(pos);
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        this.body = world.createBody(bodyDef);
        this.body.setFixedRotation(true);
    }

    /**
     * Crearemos el fixture cuadrado y que sea un sensor
     */
    private void createFixture() {
        PolygonShape cuadrado = new PolygonShape();

        cuadrado.setAsBox(0.32f,0.133f);

        this.fixture = this.body.createFixture(cuadrado,8f);
        this.fixture.setUserData(data);
        this.fixture.setDensity(100f);
        this.fixture.setSensor(true);
        cuadrado.dispose();
    }

    /**
     * Si el pajaro sale por la derecha de la pantalla su animación será mirando a la izquierda,
     * si aparece por la izquierda su animacion será mirando a la derecha y si muere con la animación
     * de mirando abajo
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.body.getPosition().x-0.4f,this.body.getPosition().y-0.4f);
        if (this.state == this.STATE_LIVE){
            if (this.pos.x >= 0){
                batch.draw(this.animationIz.getKeyFrame(statetime,true),getX(),getY(),0.8f,0.7f);
            }else{
                batch.draw(this.animationDe.getKeyFrame(statetime,true),getX(),getY(),0.8f,0.7f);
            }
        }else {
            batch.draw(this.animationAb.getKeyFrame(statetime,true),getX(),getY(),0.7f,0.8f);
        }
        this.statetime += Gdx.graphics.getDeltaTime();
    }

    /**
     * Eliminaremos el fixture y el body del mundo
     */
    public void detach(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
    }
    //Getter y setters del stado del fenix
    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
    //Devoverá un boleano si el fenix esta fuera de pantalla
    public boolean isOutOfScreen() {
        return this.body.getPosition().x <= -0.8f || this.body.getPosition().x >= 5.6f || this.body.getPosition().y <= -0.8f || this.body.getPosition().x >= 8.8f;
    }
    //Getter del UserData del fenix
    public DataPhoenix getData() {
        return this.data;
    }
}