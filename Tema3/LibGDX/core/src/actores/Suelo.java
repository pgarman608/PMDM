package actores;

import com.badlogic.gdx.Gdx;
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

public class Suelo extends Actor {

    private Vector2 posSuelo;
    private TextureRegion textureSuelo;

    private float stateTime;

    private World world;

    private Body bodySuelo;
    private Fixture fixtureSuelo;

    public Suelo(World world, Vector2 posSuelo, TextureRegion textureSuelo){
        this.posSuelo = posSuelo;
        this.world =world;
        this.textureSuelo = textureSuelo;
        this.stateTime = 0f;

        createBody();
        createFixture();
    }
    private void createBody() {
        BodyDef bodyDefSuelo = new BodyDef();
        bodyDefSuelo.position.set(posSuelo);
        bodyDefSuelo.type = BodyDef.BodyType.StaticBody;

        this.bodySuelo = world.createBody(bodyDefSuelo);

    }

    private void createFixture() {
        PolygonShape cuadrado = new PolygonShape();
        cuadrado.setAsBox(4.8f,0.35f);

        this.fixtureSuelo = this.bodySuelo.createFixture(cuadrado,8);
        this.fixtureSuelo.setUserData(Utils.DATA_SUELO);

        cuadrado.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition(bodySuelo.getPosition().x, bodySuelo.getPosition().y);
        batch.draw(textureSuelo,0,-0.36f,4.8f,1f);

        stateTime += Gdx.graphics.getDeltaTime();

    }
    public void detach(){
        this.bodySuelo.destroyFixture(this.fixtureSuelo);
        this.world.destroyBody(this.bodySuelo);
    }
}
