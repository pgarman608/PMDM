package extras;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.graalvm.compiler.core.common.util.Util;

public class AssetMan {
    // Tendremos una clase para cargar el atlas
    private AssetManager assetManager;
    // Tendremos otra clase que nos permita manejar el atlas
    private TextureAtlas textureAtlas;
    //Musica y sonido
    private Sound sonido_pajaro1;
    private Music musica_bg;

    public AssetMan(){

        this.assetManager = new AssetManager();
        //Gardaremos el atlas para cargar la imagen
        assetManager.load(Utils.ATLAS_MAP, TextureAtlas.class);
        assetManager.load(Utils.SD_FENIX1, Sound.class);
        assetManager.load(Utils.MC_BG1, Music.class);
        assetManager.finishLoading();

        this.textureAtlas = assetManager.get(Utils.ATLAS_MAP);

        this.sonido_pajaro1 = assetManager.get(Utils.SD_FENIX1);

        this.musica_bg = assetManager.get(Utils.MC_BG1);

    }
    //Tendremos unos metodos para coger el background, el pajaro, las columnas...
    public TextureRegion getBackground(){
        return this.textureAtlas.findRegion(Utils.BACKGROUND_IMAGE);
    }
    public Animation<TextureRegion> getBirdAnimation(){
        return new Animation<TextureRegion>(0.166f,
                textureAtlas.findRegion(Utils.BIRD1),
                textureAtlas.findRegion(Utils.BIRD2),
                textureAtlas.findRegion(Utils.BIRD3),
                textureAtlas.findRegion(Utils.BIRD2),
                textureAtlas.findRegion(Utils.BIRD1),
                textureAtlas.findRegion(Utils.BIRD4));
    }
    public TextureRegion getColumn1(){
        return this.textureAtlas.findRegion(Utils.COLUMN1);
    }

    public TextureRegion getColumn2(){
        return this.textureAtlas.findRegion(Utils.COLUMN2);
    }

    public TextureRegion getSuelo(){
        return this.textureAtlas.findRegion(Utils.SUELO);
    }

    public Sound getSonido_pajaro1(){
        return this.sonido_pajaro1;
    }

    public Music getMusica_bg(){
        return this.musica_bg;
    }

    public BitmapFont getFont(){
        return new BitmapFont(Gdx.files.internal(Utils.FONT_FNT),Gdx.files.internal(Utils.FONT_PNG),false);
    }
}
