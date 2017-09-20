package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

public class VueFauneEtFloreAfficher extends AppCompatActivity {
    private TabHost tab_host_faune_et_flore;
    private ListView affichage_liste_espece_faune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faune_et_flore_afficher);

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
    }

    private void creerListView() {
        affichage_liste_espece_faune = (ListView) findViewById(R.id.affichage_liste_espece_faune);
    }
}
