package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import android.database.Cursor;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;

/**
 * Created by Maxime on 20/09/2017.
 */

public class FauneDAO {

    private static FauneDAO instance;
    private BaseDeDonnee accesseurBaseDeDonnee;

    private final List<Faune> listeFaune = new ArrayList<Faune>();

    public static FauneDAO getInstance() {
        if(instance == null)instance = new FauneDAO();
        return instance;
    }

    private FauneDAO(){
        accesseurBaseDeDonnee = BaseDeDonnee.getInstance();
    }

    public List<Faune> listerTouteLaFaune() {
        String SQL_SELECT = "SELECT * FROM faune";
        Cursor curseurFaune = accesseurBaseDeDonnee.getWritableDatabase().rawQuery(SQL_SELECT, null);
        listeFaune.clear();
        int indexId = curseurFaune.getColumnIndex("idFaune");
        int indexNom = curseurFaune.getColumnIndex("nom");
        int indexNomScientifique = curseurFaune.getColumnIndex("nomScientifique");
        int indexLieu = curseurFaune.getColumnIndex("lieu");
        int indexType = curseurFaune.getColumnIndex("type");
        int indexPopulation = curseurFaune.getColumnIndex("population");

        System.out.println(curseurFaune.moveToFirst());
        for(curseurFaune.moveToFirst(); !curseurFaune.isAfterLast(); curseurFaune.moveToNext()) {
            int id = curseurFaune.getInt(indexId);
            String nom = curseurFaune.getString(indexNom);
            String nomScientifique = curseurFaune.getString(indexNomScientifique);
            String lieu = curseurFaune.getString(indexLieu);
            String type = curseurFaune.getString(indexType);
            int population = curseurFaune.getInt(indexPopulation);
            listeFaune.add(new Faune(id,nom,nomScientifique,lieu,type,population));
        }
        return listeFaune;
    }

    public void ajouterFaune(Faune faune){
        String SQL_UPDATE = "INSERT INTO faune (nom, nomScientifique, lieu, type, population) VALUES ('" + faune.getNom() + "','" + faune.getNomScientifique() + "','" + faune.getLieu() + "','" + faune.getType() + "'," + faune.getPopulation() +")";
        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }

    public void modifierFaune(Faune faune) {
        String SQL_UPDATE = "UPDATE faune set " +
                "nom = '" + faune.getNom() + "', " +
                "nomScientifique = '" + faune.getNomScientifique() + "', " +
                "lieu = '" + faune.getLieu() + "', " +
                "type = '" + faune.getType() + "', " +
                "population=" + faune.getPopulation() +
                " WHERE idFaune = " + faune.getId();
        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }

    public void supprimerFaune(Faune faune)
    {
        String SQL_UPDATE = "DELETE FROM faune WHERE idFaune = " + faune.getId();

        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }
    public List<HashMap<String, String>> listerLaFauneEnHashmap() {
        listerTouteLaFaune();
        List<HashMap<String, String>> listeFauneHashmap = new ArrayList<>();
        for (Faune faune : this.listeFaune) {
            listeFauneHashmap.add(faune.exporterHashMap());
        }
        return listeFauneHashmap;
    }

    public Faune trouverFaune(int id) {
        for(Faune faune : listeFaune){
            if(faune.getId() == id){
                return faune;
            }
        }
        return null;
    }
}
