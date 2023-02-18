package extras;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsManager {
    //Clase que se encarga de cargar el atlas, las musicas y sonidos
    private AssetManager assetManager;
    //Clase que se encarge de manejar el atlas
    private TextureAtlas textureAtlas;

    /**
     * En el constructor nos encargaremos de cargar el assets, sonido y musica
     */
    public AssetsManager() {
        this.assetManager = new AssetManager();
        //Guardaremos el atlas en el AssetManager
        assetManager.load(Utils.ATLAS_MAP, TextureAtlas.class);
        //Guardaremos las misicas y los sonidos en el AssetManager
        assetManager.load(Utils.MUSIC_THEME,Music.class);
        assetManager.load(Utils.SOUNDPHOENIX,Sound.class);
        assetManager.load(Utils.SOUNDGOVER,Sound.class);
        assetManager.load(Utils.SOUNDWIN,Sound.class);
        //Terminaremos de guardar informacion en el AssetManager
        assetManager.finishLoading();
        //Cargaremos el atlas en la clase
        this.textureAtlas = assetManager.get(Utils.ATLAS_MAP);
    }
    //Recogeremos los fondos
    public TextureRegion getBgStart(){
        return this.textureAtlas.findRegion(Utils.BG_START);
    }
    public TextureRegion getBgPlay(){
        return this.textureAtlas.findRegion(Utils.BG_PLAY);
    }
    public TextureRegion getBgGO(){
        return this.textureAtlas.findRegion(Utils.BG_GO);
    }
    public TextureRegion getBgWin(){
        return this.textureAtlas.findRegion(Utils.BG_WINING);
    }
    public TextureRegion getBgTuto(){
        return this.textureAtlas.findRegion(Utils.BG_TUTORIAL);
    }
    //Sonidos y musicas
    public Sound getPhoenixSound(){
        return this.assetManager.get(Utils.SOUNDPHOENIX);
    }
    public Sound getWinSound(){
        return this.assetManager.get(Utils.SOUNDWIN);
    }
    public Sound getloseSound(){
        return this.assetManager.get(Utils.SOUNDGOVER);
    }
    public Music getMainTheme(){
        return this.assetManager.get(Utils.MUSIC_THEME);
    }
    //Recogeremos la mira
    public TextureRegion getGunSight(){
        return this.textureAtlas.findRegion(Utils.GUNSIGHT);
    }
    //Recogeremos las animaciones de los fenix
    public Animation<TextureRegion> getBirdNDAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDND1),
                textureAtlas.findRegion(Utils.BIRDND2),
                textureAtlas.findRegion(Utils.BIRDND3),
                textureAtlas.findRegion(Utils.BIRDND2),
                textureAtlas.findRegion(Utils.BIRDND1),
                textureAtlas.findRegion(Utils.BIRDND4));
    }
    public Animation<TextureRegion> getBirdNIAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDNI1),
                textureAtlas.findRegion(Utils.BIRDNI2),
                textureAtlas.findRegion(Utils.BIRDNI3),
                textureAtlas.findRegion(Utils.BIRDNI2),
                textureAtlas.findRegion(Utils.BIRDNI1),
                textureAtlas.findRegion(Utils.BIRDNI4));
    }
    public Animation<TextureRegion> getBirdNAAnimation(){
        return new Animation<TextureRegion>(0.5f,
                textureAtlas.findRegion(Utils.BIRDNA1),
                textureAtlas.findRegion(Utils.BIRDNA2));
    }
    public Animation<TextureRegion> getBirdADAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDAD1),
                textureAtlas.findRegion(Utils.BIRDAD2),
                textureAtlas.findRegion(Utils.BIRDAD3),
                textureAtlas.findRegion(Utils.BIRDAD2),
                textureAtlas.findRegion(Utils.BIRDAD1),
                textureAtlas.findRegion(Utils.BIRDAD4));
    }
    public Animation<TextureRegion> getBirdAIAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDAI1),
                textureAtlas.findRegion(Utils.BIRDAI2),
                textureAtlas.findRegion(Utils.BIRDAI3),
                textureAtlas.findRegion(Utils.BIRDAI2),
                textureAtlas.findRegion(Utils.BIRDAI1),
                textureAtlas.findRegion(Utils.BIRDAI4));
    }
    public Animation<TextureRegion> getBirdAAAnimation(){
        return new Animation<TextureRegion>(0.5f,
                textureAtlas.findRegion(Utils.BIRDAA1),
                textureAtlas.findRegion(Utils.BIRDAA2));
    }
    public Animation<TextureRegion> getBirdRDAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDRD1),
                textureAtlas.findRegion(Utils.BIRDRD2),
                textureAtlas.findRegion(Utils.BIRDRD3),
                textureAtlas.findRegion(Utils.BIRDRD2),
                textureAtlas.findRegion(Utils.BIRDRD1),
                textureAtlas.findRegion(Utils.BIRDRD4));
    }
    public Animation<TextureRegion> getBirdRIAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRDRI1),
                textureAtlas.findRegion(Utils.BIRDRI2),
                textureAtlas.findRegion(Utils.BIRDRI3),
                textureAtlas.findRegion(Utils.BIRDRI2),
                textureAtlas.findRegion(Utils.BIRDRI1),
                textureAtlas.findRegion(Utils.BIRDRI4));
    }
    public Animation<TextureRegion> getBirdRAAnimation(){
        return new Animation<TextureRegion>(0.5f,
                textureAtlas.findRegion(Utils.BIRDRA1),
                textureAtlas.findRegion(Utils.BIRDRA2));
    }
    //Recogeremos la textura del fenix con la mira
    public TextureRegion getBirdHunt(){
        return this.textureAtlas.findRegion(Utils.BIRDHUNT);
    }
    //Recogeremos la textura del corazon
    public TextureRegion getHeart(){
        return this.textureAtlas.findRegion(Utils.CORAZON);
    }
    //Recogeremos las fuentes del juego
    public BitmapFont getFontWhite(){
        return new BitmapFont(Gdx.files.internal(Utils.FONT_FNT),Gdx.files.internal(Utils.FONT_PNG),false);
    }
    public BitmapFont getFontRed(){
        return new BitmapFont(Gdx.files.internal(Utils.FONT_RED_FNT),Gdx.files.internal(Utils.FONT_RED_PNG),false);
    }
}