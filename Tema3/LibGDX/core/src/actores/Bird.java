package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

import org.graalvm.compiler.core.common.util.Util;

import extras.Utils;


public class Bird extends Actor {
    private Animation<TextureRegion> animation;
    private Vector2 pos;
    private float stateTime;
    private World world;
    //Body = colision
    private Body body;
    private Fixture fixture;
    private static final float JUMP_SPEED = 4.5f;



    //Estados del pajaro
    public static final int STATE_LIVE = 1;
    public static final int STATE_DEAD = -1;

    private int estado;

    private Sound sonidoSalto;

    public Bird(World world, Animation<TextureRegion> animation, Vector2 pos,Sound sonidoSalto){
        this.animation = animation;
        this.pos = pos;
        this.world = world;
        
        this.stateTime = 0f;
        estado = STATE_LIVE;

        this.sonidoSalto = sonidoSalto;

        createBody();
        createFixture();
    }

    private void createBody() {
        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(pos);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.body = world.createBody(bodyDef);
        this.body.setFixedRotation(true);
    }

    private void createFixture() {
        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(0.32f,0.133f);

        this.fixture = this.body.createFixture(cuadrado,8);
        this.fixture.setUserData(Utils.DATA_BIRD);
        this.fixture.setDensity(100f);
        cuadrado.dispose();
    }

    @Override
    public void act(float delta) {
        boolean jump = Gdx.input.justTouched();
        if ( jump && estado == STATE_LIVE){
            sonidoSalto.play();
            this.body.setLinearVelocity(0,JUMP_SPEED);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(body.getPosition().x-0.4f, body.getPosition().y-0.4f);
        batch.draw(this.animation.getKeyFrame(stateTime, true), getX(), getY(), 0.8f,0.7f );

        stateTime += Gdx.graphics.getDeltaTime();
    }
    public void detach(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
