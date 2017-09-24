package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.HashMap;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.BaseDeDonnee;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FauneDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FloreDAO;

public class VueFauneEtFloreAfficher extends AppCompatActivity {
    public static final int ACTIVITE_MODIFIER_FLORE = 1;
    public static final int ACTIVITE_MODIFIER_FAUNE = 2;

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

        affichage_liste_espece_flore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positionDansAdapteur, long positionItem) {
                ListView vueListeFlore = (ListView) view.getParent();

                HashMap<String, String> flore = (HashMap<String, String>) vueListeFlore.getItemAtPosition((int) positionItem);

                Intent intentionNaviguerModifierFlore = new Intent(VueFauneEtFloreAfficher.this, VueFloreModifier.class);
                intentionNaviguerModifierFlore.putExtra("Id", flore.get("Id"));
                startActivityForResult(intentionNaviguerModifierFlore, ACTIVITE_MODIFIER_FLORE);
            }
        });

        affichage_liste_espece_faune.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positionDansAdapteur, long positionItem) {
                ListView vueListeFaune = (ListView) view.getParent();

                HashMap<String, String> faune = (HashMap<String, String>) vueListeFaune.getItemAtPosition((int) positionItem);

                Intent intentionNaviguerModifierFaune = new Intent(VueFauneEtFloreAfficher.this, VueFauneModifier.class);
                intentionNaviguerModifierFaune.putExtra("idFaune", faune.get("idFaune"));
                startActivityForResult(intentionNaviguerModifierFaune, ACTIVITE_MODIFIER_FAUNE);
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
