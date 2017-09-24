package ca.qc.cqmatane.informatique.cataloguefauneetflore.modele;

import java.util.HashMap;

/**
 * Created by Maxime on 20/09/2017.
 */

public class Flore extends Espece{

    public Flore(String nom, String nomScientifique, String lieu) {
        super(nom, nomScientifique, lieu);
    }

    public Flore(int id, String nom, String nomScientifique, String lieu) {
        super(id, nom, nomScientifique, lieu);
    }

    public HashMap<String, String> exporterHashMap() {
        return super.exporterHashMap();
    }

}
