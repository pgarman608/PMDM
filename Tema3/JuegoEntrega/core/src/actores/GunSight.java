package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import extras.Utils;

/**
 * Este actor lo utilizaremos para visualizar donde hemos pulsado.
 */
public class GunSight extends Actor {
    //Este objeto es el mundo donde va a estar nuesto actor
    private World world;
    //Este objeto es el cuerpo fisico, caja de colisiones y la posición del actor
    private Body body;
    public Fixture fixture;
    private Vector2 pos;
    //Este objeto es la textura del actor
    private TextureRegion textureRegion;
    //Este objeto lo utilizaremos para contar el tiempo del data
    private float timer;
    /**
     * Este es el constructor por parámmetro donde introduciremos el mundo, la textura y la ubicacion
     * del actor
     * @param world
     * @param textureRegion
     * @param pos
     */
    public GunSight(World world, TextureRegion textureRegion, Vector2 pos){
        this.world = world;
        this.textureRegion = textureRegion;
        this.pos = pos;
        //Creareos el fixture y el body con los siguientes metodos
        createBody();
        createFixture();
    }

    private void createFixture() {
        //Crearemos un polygonshape que sera la forma de la hitbox
        PolygonShape square = new PolygonShape();
        //Le diremos el tamaño de la caja
        square.setAsBox(0.3f,0.3f);
        //Crearemos el fixture con ese cuadrado
        this.fixture = this.body.createFixture(square,8f);
        //Introduciremos el userdata al fixture
        this.fixture.setUserData(Utils.DATA_MIRA);
        //Le pondremos una densidad
        this.fixture.setDensity(100f);
        //Eliminaremos el cuadradp
        square.dispose();
    }
    private void createBody() {
        //Crearemos el body, con la posición de la clase y será dinamico
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //Le dirémos al mundo que cree el body anteriormente creado y lo guardaremos ese body en el
        //body de la clase
        this.body = world.createBody(bodyDef);
        //Le quitaremos la rotacion al body
        this.body.setFixedRotation(true);
        //Quitamos la gravedad al body ya que es dinamico y la gravedad le afecta
        this.body.setGravityScale(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //Pondremos la posición del actor con la base del body
        setPosition(body.getPosition().x-0.35f,body.getPosition().y-0.35f);
        //Dibujaremos la imagen con el TextureRegion de la clase, la posicion y un tamaño
        batch.draw(textureRegion,getX(),getY(),0.7f,0.7f);
    }

    /**
     * Este método lo utilizaremos para eliminar el actor del mundo y el escenario donde es generado
     */
    public void detach(){
        if (!body.getFixtureList().isEmpty()){
            this.body.destroyFixture(this.fixture);
        }
        this.world.destroyBody(this.body);
    }

    /**
     * Este método lo utilizaremos para manejar el movimiento del actor sobre el juego
     * @param delta
     */
    @Override
    public void act(float delta) {
        timer += delta;
        super.act(delta);
        //Cuando toquemos la pantalla (Solo tocar, no mover) se creará el fixture ya que se habra
        //eliminado anteriormente, cambiaremos la posición del actor
        if (Gdx.input.justTouched()){
            createFixture();
            Vector3 pos2;
            OrthographicCamera orthographicCamera= (OrthographicCamera) getStage().getCamera();
            pos2 = orthographicCamera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
            body.setTransform(pos2.x, pos2.y, body.getAngle());
            setPosition(body.getPosition().x,body.getPosition().y);
            timer = 0;
        }
        //Cuando pase 0.06s y el actor tenga fixture, eliminaremos el fixture para que no interactue
        //con el entorno
        if (timer >= 0.06f && !body.getFixtureList().isEmpty()){
            Fixture fixture2 = body.getFixtureList().first();
            body.destroyFixture(fixture2);
            timer = 0;
        }
    }
}