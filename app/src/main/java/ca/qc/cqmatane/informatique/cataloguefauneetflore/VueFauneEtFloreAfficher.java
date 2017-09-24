package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.HashMap;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.BaseDeDonnee;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FauneDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FloreDAO;

public class VueFauneEtFloreAfficher extends AppCompatActivity {
    private TabHost tab_host_faune_et_flore;
    private ListView affichage_liste_espece_faune;
    private ListView affichage_liste_espece_flore;
    private FauneDAO accesseurFaune;
    private FloreDAO accesseurFlore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faune_et_flore_afficher);
        BaseDeDonnee baseDeDonnee = BaseDeDonnee.getInstance(getApplicationContext());
        accesseurFaune = FauneDAO.getInstance();
        accesseurFlore = FloreDAO.getInstance();
        affichage_liste_espece_faune = (ListView) findViewById(R.id.affichage_liste_espece_faune);
        affichage_liste_espece_flore = (ListView) findViewById(R.id.affichage_liste_espece_flore);
        creerTab();


    }

    private void creerTab() {
        tab_host_faune_et_flore = (TabHost) findViewById(R.id.tab_host_faune_et_flore);
        tab_host_faune_et_flore.setup();

        TabHost.TabSpec tabSpecFaune = tab_host_faune_et_flore.newTabSpec("Faune");
        tabSpecFaune.setContent(R.id.affichage_liste_espece_faune);
        tabSpecFaune.setIndicator("Faune");
        tab_host_faune_et_flore.addTab(tabSpecFaune);

        TabHost.TabSpec tabSpecFlore = tab_host_faune_et_flore.newTabSpec("Flore");
        tabSpecFlore.setContent(R.id.affichage_liste_espece_flore);
        tabSpecFlore.setIndicator("Flore");
        tab_host_faune_et_flore.addTab(tabSpecFlore);

        creerListViewFaune();
        creerListViewFlore();
    }

    private void creerListViewFaune() {
        List<HashMap<String, String>> listeFaune = accesseurFaune.listerLaFauneEnHashmap();
        SimpleAdapter adapteurVueListeRendezVous = new SimpleAdapter(
                this,
                listeFaune,
                R.layout.faune_et_flore_afficher_layout_custom_element_liste,
                new String[] {"Nom", "NomScientifique", "Lieu"},
                new int[] {R.id.affichage_nom, R.id.affichage_nom_scientifique,  R.id.affichage_lieu});

        affichage_liste_espece_faune.setAdapter(adapteurVueListeRendezVous);
    }

    private void creerListViewFlore() {
        List<HashMap<String, String>> listeFlore = accesseurFlore.listerLaFauneEnHashmap();
        SimpleAdapter adapteurVueListeRendezVous = new SimpleAdapter(
                this,
                listeFlore,
                R.layout.faune_et_flore_afficher_layout_custom_element_liste,
                new String[] {"Nom", "NomScientifique", "Lieu"},
                new int[] {R.id.affichage_nom, R.id.affichage_nom_scientifique,  R.id.affichage_lieu});

        affichage_liste_espece_flore.setAdapter(adapteurVueListeRendezVous);
    }
}
