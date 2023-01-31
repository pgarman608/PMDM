package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import extras.Utils;

public class Techo extends Actor {

    private Vector2 posTecho;

    private float stateTime;

    private World world;

    private Body bodyTecho;
    private Fixture fixtureTecho;

    public Techo(World world, Vector2 posTecho){
        this.posTecho = posTecho;
        this.world =world;

        this.stateTime = 0f;

        createBody();
        createFixture();
    }
    private void createBody() {
        BodyDef bodyDefTecho = new BodyDef();
        bodyDefTecho.position.set(posTecho);
        bodyDefTecho.type = BodyDef.BodyType.StaticBody;

        this.bodyTecho = world.createBody(bodyDefTecho);
    }

    private void createFixture() {
        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(4.8f,0.2f);

        this.fixtureTecho = this.bodyTecho.createFixture(cuadrado,8);
        this.fixtureTecho.setUserData(Utils.DATA_TECHO);

        cuadrado.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        stateTime += Gdx.graphics.getDeltaTime();

    }
    public void detach(){
        this.bodyTecho.destroyFixture(this.fixtureTecho);
        this.world.destroyBody(this.bodyTecho);
    }
}
