package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.BaseDeDonnee;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FauneDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FloreDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Espece;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Flore;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.outil.AdapteurListeFauneEtFlore;

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

        affichage_liste_espece_flore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positionDansAdapteur, long positionItem) {
                ListView vueListeFlore = (ListView) view.getParent();

                int idFlore = ((Espece)vueListeFlore.getAdapter().getItem((int) positionItem)).getId();

                Intent intentionNaviguerModifierFlore = new Intent(VueFauneEtFloreAfficher.this, VueFloreModifier.class);
                intentionNaviguerModifierFlore.putExtra("Id", (idFlore + ""));
                startActivity(intentionNaviguerModifierFlore);
            }
        });

        affichage_liste_espece_faune.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positionDansAdapteur, long positionItem) {
                ListView vueListeFaune = (ListView) view.getParent();

                int idFaune = ((Espece)vueListeFaune.getAdapter().getItem((int) positionItem)).getId();

                Intent intentionNaviguerModifierFaune = new Intent(VueFauneEtFloreAfficher.this, VueFauneModifier.class);
                intentionNaviguerModifierFaune.putExtra("Id", (idFaune + ""));
                startActivity(intentionNaviguerModifierFaune);
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
        List<Faune> listeFaune = accesseurFaune.listerTouteLaFaune();
        affichage_liste_espece_faune.setAdapter(new AdapteurListeFauneEtFlore(this,(ArrayList) listeFaune));
    }

    private void creerListViewFlore() {
        List<Flore> listeFlore = accesseurFlore.listerTouteLaFlore();
        affichage_liste_espece_flore.setAdapter(new AdapteurListeFauneEtFlore(this,(ArrayList) listeFlore));
    }

    public void actionAjouterUneFaune(View vue){
        Intent intentionNaviguerAjouterFaune = new Intent(VueFauneEtFloreAfficher.this, VueFauneAjouter.class);
        startActivity(intentionNaviguerAjouterFaune);
    }

    public void actionAjouterUneFlore(View vue){
        Intent intentionNaviguerAjouterFlore = new Intent(VueFauneEtFloreAfficher.this, VueFloreAjouter.class);
        startActivity(intentionNaviguerAjouterFlore);
    }

    @Override
    protected void onStart() {
        super.onStart();
        creerListViewFlore();
        creerListViewFaune();
    }
}
