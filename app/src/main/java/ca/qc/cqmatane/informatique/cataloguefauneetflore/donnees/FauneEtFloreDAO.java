package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

/**
 * Created by Maxime on 18/09/2017.
 */

public class FauneEtFloreDAO {

    private static FauneEtFloreDAO instance;
    private BaseDeDonnee accesseurBaseDeDonnee;

    public static FauneEtFloreDAO getInstance(){
        if(instance == null) instance = new FauneEtFloreDAO();
        return instance;
    }

    private FauneEtFloreDAO() {
        accesseurBaseDeDonnee = BaseDeDonnee.getInstance();
    }

    public void modifierFaunne(){

    }

    public void modifierFlore(){

    }
}
