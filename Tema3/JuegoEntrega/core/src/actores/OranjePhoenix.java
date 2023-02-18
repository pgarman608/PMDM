package actores;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import extras.Utils;

public class OranjePhoenix extends Phoenix {
    //La velodcida que tendremos
    private float velocityX;

    public OranjePhoenix(World world, Animation<TextureRegion> animaIz, Animation<TextureRegion> animaDe, Animation<TextureRegion> animaAb, Vector2 pos) {
        super(world, animaIz, animaDe, animaAb, pos,Utils.DATA_BIRDN);
        //Calcularemos la velocidada con un randon
        velocityX = MathUtils.random(2.5f,3.5f);
        //Si la posicion del fenix es menor a 0 cambiaremos la direccion del fenix
        if (pos.x > 0){
            velocityX -= (velocityX*2);
        }
    }
    @Override
    public void act(float delta) {
        //Si el estado del pajaro es vivo nos moveremos por el eje x y si esta muerto caerá por el eje y
        if (state == STATE_LIVE){
            this.body.setLinearVelocity(velocityX,0);
        }else{
            this.body.setLinearVelocity(0,-2.5f);
        }
    }
}
