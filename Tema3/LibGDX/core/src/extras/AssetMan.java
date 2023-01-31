package extras;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetMan {
    // Tendremos una clase para cargar el atlas
    private AssetManager assetManager;
    // Tendremos otra clase que nos permita manejar el atlas
    private TextureAtlas textureAtlas;
    public AssetMan(){

        this.assetManager = new AssetManager();
        //Gardaremos el atlas para cargar la imagen
        assetManager.load(Utils.ATLAS_MAP, TextureAtlas.class);
        assetManager.finishLoading();

        this.textureAtlas = assetManager.get(Utils.ATLAS_MAP);
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
}
