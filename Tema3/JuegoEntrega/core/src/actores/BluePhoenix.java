package actores;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import extras.Utils;

public class BluePhoenix extends Phoenix {
    //La velocidad en x e y
    private float velocityX;
    private float velocityY;
    public BluePhoenix(World world, Animation<TextureRegion> animaIz, Animation<TextureRegion> animaDe, Animation<TextureRegion> animaAb, Vector2 pos) {
        super(world, animaIz, animaDe, animaAb, pos,Utils.DATA_BIRDA);
        //Calcularemos la velocidad X con un random
        velocityX = MathUtils.random(2.1f,3.0f);
        //Si la posicion del fenix es menor a 0 cambiaremos la direccion del fenix
        if (pos.x > 0){
            velocityX -= (velocityX*2);
        }
        //Si la posicion del fenix es menor a 6 el fenix subirá
        if (pos.y <= 6){
            velocityY = 2f;
        }else{
            //Si la posicion del fenix es mayor a 6 el fenix bajará
            if(pos.y >= 6){
                velocityY = -1f;
            }
        }
    }

    @Override
    public void act(float delta) {
        //Si el estado del fenix es vivo el fenix volará, si esta muerto caerá
        if (state == STATE_LIVE){
            this.body.setLinearVelocity(velocityX,velocityY);
        }else{
            this.body.setLinearVelocity(0,-2.5f);
        }
    }
}
