package actores;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import extras.Utils;

public class PinkPhoenix extends Phoenix {
    //La velocidad en x e y (Dos Y que una es cuando sube y otra cuando baja)
    private float velocityX;
    private float velocityY;
    private float velocityYb;
    //El tiempo que tarda en subir y bajar
    private float time;
    //Cuento de esquinas que crea el movimiento del pajaro
    private int corners;
    public PinkPhoenix(World world, Animation<TextureRegion> animaIz, Animation<TextureRegion> animaDe, Animation<TextureRegion> animaAb, Vector2 pos) {
        super(world, animaIz, animaDe, animaAb, pos,Utils.DATA_BIRDR);
        //Calcularemos la velocidad X con un random
        velocityX = MathUtils.random(1.8f,2.6f);
        //Si la posicion del fenix es menor a 0 cambiaremos la direccion del feni
        if (pos.x > 0){
            velocityX -= (velocityX*2);
        }
        //Calcularemos la velocidad X con un random
        velocityY = MathUtils.random(1.40f,2.22f);
        //Si la posicion de la y esta entre
        if (pos.y >= 1.2f && pos.y <= 6.8) {
            /**
             * Segun el numero que saque el rndAux subira o bajara el fenix cuando aparezca
             */
            int rndAux = MathUtils.random(1,2);

            if (rndAux == 1){
                velocityYb = velocityY - (velocityY * 2);
            }else {
                velocityYb = velocityY;
                velocityY = velocityYb - (velocityYb * 2);
            }
        }else{
            velocityYb = velocityY - (velocityY * 2);
        }
        corners =2;
        time = 0;
    }

    @Override
    public void act(float delta) {
        //Si el estado del fenix es vivo el fenix volará, si esta muerto caerá
        if (state == STATE_LIVE){
            /**
             * Cada 0.8s cambiará velocidad de Y una positiva y otra negativa
             */
            time += delta;
            if (time >= 0.8f){
                time -= 0.8f;
                corners++;
            }else{
                if (corners % 2 == 0){
                    this.body.setLinearVelocity(velocityX,velocityY);
                }else{
                    this.body.setLinearVelocity(velocityX,velocityYb);
                }
            }
        }else{
            this.body.setLinearVelocity(0,-2.5f);
        }
    }
}