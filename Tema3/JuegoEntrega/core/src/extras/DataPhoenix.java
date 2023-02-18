package extras;

public class DataPhoenix {
    //String donde guardaremos el user data de cada fenix
    private String userData;
    //Int que guardaremos un numero incremental que se guardara en la clase
    private static int idGeneral;
    //Int que identifica a cada fenix gracias al int anterior
    private int idIndiv;

    /**
     * Constructor por defecto donde no inicializaremos nada
     */
    public DataPhoenix() {
    }

    /**
     * Constructor por parámetro donde introduciremos el userdata, auto incrementara el idGeneral y
     * guardaremos el auto incremental en la variable no estatica de idIndiv
     * @param userData
     */
    public DataPhoenix(String userData){
        this.userData = userData;
        this.idGeneral ++;
        this.idIndiv = idGeneral;
    }
    //Getters del userData y del idIndiv
    public int getIdIndiv() {
        return idIndiv;
    }

    public String getUserData() {
        return userData;
    }
}
