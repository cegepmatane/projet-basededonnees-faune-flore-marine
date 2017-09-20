package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Flore;

/**
 * Created by Maxime on 20/09/2017.
 */

public class FloreDAO {

    private static FloreDAO instance;
    private BaseDeDonnee accesseurBaseDeDonnee;

    public static FloreDAO getInstance() {
        if(instance == null)instance = new FloreDAO();
        return instance;
    }

    private FloreDAO() {
        accesseurBaseDeDonnee = BaseDeDonnee.getInstance();
    }

    public void modifierFlore(Flore flore) {
        String SQL_UPDATE = "UPDATE flore set " +
                "nom=" + flore.getNom() + ", " +
                "nomScientifique=" + flore.getNomScientifique() + ", " +
                "lieu=" + flore.getLieu();
        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }

}
