package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Flore;

/**
 * Created by Maxime on 20/09/2017.
 */

public class FloreDAO {

    private static FloreDAO instance;
    private BaseDeDonnee accesseurBaseDeDonnee;

    private final List<Flore> listeFlore = new ArrayList<Flore>();

    public static FloreDAO getInstance() {
        if(instance == null)instance = new FloreDAO();
        return instance;
    }

    private FloreDAO() {
        accesseurBaseDeDonnee = BaseDeDonnee.getInstance();
    }

    public List<Flore> listerTouteLaFlore() {
        String SQL_SELECT = "SELECT * FROM flore";
        Cursor curseurFlore =accesseurBaseDeDonnee.getWritableDatabase().rawQuery(SQL_SELECT, null);
        listeFlore.clear();

        int indexId = curseurFlore.getColumnIndex("idFlore");
        int indexNom = curseurFlore.getColumnIndex("nom");
        int indexNomScientifique = curseurFlore.getColumnIndex("nomScientifique");
        int indexLieu = curseurFlore.getColumnIndex("lieu");

        for(curseurFlore.moveToFirst(); !curseurFlore.isAfterLast(); curseurFlore.moveToNext()) {
            int id = curseurFlore.getInt(indexId);
            String nom = curseurFlore.getString(indexNom);
            String nomScientifique = curseurFlore.getString(indexNomScientifique);
            String lieu = curseurFlore.getString(indexLieu);
            listeFlore.add(new Flore(id,nom,nomScientifique,lieu));
        }
        return listeFlore;
    }

    public void modifierFlore(Flore flore) {
        String SQL_UPDATE = "UPDATE flore set " +
                "nom = '" + flore.getNom() + "', " +
                "nomScientifique = '" + flore.getNomScientifique() + "', " +
                "lieu = '" + flore.getLieu()+"'";
        accesseurBaseDeDonnee.getWritableDatabase().execSQL(SQL_UPDATE);
    }

    public List<HashMap<String, String>> listerLaFauneEnHashmap() {
        listerTouteLaFlore();
        List<HashMap<String, String>> listeFauneHashmap = new ArrayList<>();
        for (Flore flore : this.listeFlore) {
            listeFauneHashmap.add(flore.exporterHashMap());
        }
        return listeFauneHashmap;
    }

    public Flore trouverFlore(int id) {
        for(Flore flore: listeFlore){
            if(flore.getId() == id){
                return flore;
            }
        }
        return null;
    }
}
