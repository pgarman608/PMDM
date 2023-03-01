package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import extras.Utils;

public class Pipes extends Actor {
    //Pipe abajo
    private TextureRegion texturaPI;
    private TextureRegion texturaPS;

    private Vector2 posPI;

    private float stateTime;
    private World world;

    private Body bodyPI;
    private Fixture fixturePI;

    private Body bodyPS;
    private Fixture fixturePS;

    private Body bodyCont;
    private Fixture fixtureCont;

    //Variables para calculos

    private final static float SPEED = -0.8f;
    private float pipe_width = 0.78f;
    private float pipe_height = 4f;
    private float cont_height = 1.6f;

    public Pipes(World world, TextureRegion texturaPI, TextureRegion texturaPS, Vector2 posPI){
        this.world = world;
        this.texturaPI = texturaPI;
        this.texturaPS = texturaPS;
        this.posPI = posPI;

        stateTime = 0f;

        createBodyPI();
        createBodyPS();
        createFixturePI();
        createFixturePS();
        createCont();
    }

    public boolean isOutOfScreen(){
        return this.bodyPI.getPosition().x <= -2f;
    }

    private void createBodyPI() {
        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(posPI);
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        this.bodyPI = world.createBody(bodyDef);
        this.bodyPI.setLinearVelocity(SPEED,0);
    }

    private void createBodyPS() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.x = bodyPI.getPosition().x;
        bodyDef.position.y = bodyPI.getPosition().y + cont_height + pipe_height;

        bodyDef.type = BodyDef.BodyType.KinematicBody;

        this.bodyPS = world.createBody(bodyDef);
        this.bodyPS.setLinearVelocity(SPEED,0);
    }

    private void createFixturePI() {
        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(pipe_width/2,pipe_height/2);

        this.fixturePI = this.bodyPI.createFixture(cuadrado,8);
        this.fixturePI.setUserData(Utils.DATA_PIPEI);

        cuadrado.dispose();
    }
    private void createCont() {
        BodyDef bodyDef = new BodyDef();

        bodyDef.position.set(new Vector2(this.bodyPI.getPosition().x,(this.bodyPI.getPosition().y + this.bodyPS.getPosition().y) /2f));

        bodyDef.type = BodyDef.BodyType.KinematicBody;

        this.bodyCont = world.createBody(bodyDef);
        this.bodyCont.setLinearVelocity(SPEED,0);

        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(0.1f,0.70f);

        this.fixtureCont = this.bodyCont.createFixture(cuadrado,8);
        this.fixtureCont.setUserData(Utils.DATA_CONT);
        this.fixtureCont.setSensor(true);
        cuadrado.dispose();

    }
    private void createFixturePS() {
        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(pipe_width/2,pipe_height/2);

        this.fixturePS = this.bodyPS.createFixture(cuadrado,8);
        this.fixturePS.setUserData(Utils.DATA_PIPES);
        cuadrado.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyPI.getPosition().x-(pipe_width/2),bodyPI.getPosition().y - (pipe_height/2));

        batch.draw(texturaPI,getX(),getY(),pipe_width,pipe_height);

        setPosition(bodyPS.getPosition().x-(pipe_width/2),bodyPS.getPosition().y - (pipe_height/2));

        batch.draw(texturaPS,getX(),getY(),pipe_width,pipe_height);

        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void detach(){
        this.bodyPI.destroyFixture(this.fixturePI);
        this.world.destroyBody(this.bodyPI);
        this.bodyPS.destroyFixture(this.fixturePS);
        this.world.destroyBody(this.bodyPS);
    }

}
