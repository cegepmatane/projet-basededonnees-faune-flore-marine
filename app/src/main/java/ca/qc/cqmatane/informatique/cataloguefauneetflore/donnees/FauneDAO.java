package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;

/**
 * Created by Maxime on 20/09/2017.
 */

public class FauneDAO {

    private static FauneDAO instance;
    private BaseDeDonnee accesseurBaseDeDonnee;

    public static FauneDAO getInstance() {
        if(instance == null)instance = new FauneDAO();
        return instance;
    }

    private FauneDAO(){
        accesseurBaseDeDonnee = BaseDeDonnee.getInstance();
    }

    public void listerTouteLaFaune() {
        String SQL_SELECT = "SELECT * FROM faune";

    }

    public void modifierFaune(Faune faune) {
        String SQL_UPDATE = "UPDATE faune set " +
                "nom=" + faune.getNom() + ", " +
                "nomScientifique=" + faune.getNomScientifique() + ", " +
                "lieu=" + faune.getLieu() + ", " +
                "type=" + faune.getType() + ", " +
                "population=" + faune.getPopulation();
        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }
}
