package ca.qc.cqmatane.informatique.cataloguefauneetflore.vues;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.FontsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.R;
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
    private Button action_ajouter_flore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faune_et_flore_afficher);
        BaseDeDonnee baseDeDonnee = BaseDeDonnee.getInstance(getApplicationContext());
        accesseurFaune = FauneDAO.getInstance();
        accesseurFlore = FloreDAO.getInstance();
        affichage_liste_espece_faune = (ListView) findViewById(R.id.affichage_liste_espece_faune);
        affichage_liste_espece_flore = (ListView) findViewById(R.id.affichage_liste_espece_flore);
        action_ajouter_flore = (Button) findViewById(R.id.action_ajouter_flore);
        creerTab();
        ajouterFont();

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

        tab_host_faune_et_flore.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(tab_host_faune_et_flore.getCurrentTab()==0) {
                    tab_host_faune_et_flore.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#0698D7"));
                    tab_host_faune_et_flore.getTabWidget().getChildAt(tab_host_faune_et_flore.getCurrentTab()).setBackgroundColor(Color.parseColor("#003399")); //1st tab selected
                }
                else {
                    tab_host_faune_et_flore.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#0698D7"));
                    tab_host_faune_et_flore.getTabWidget().getChildAt(tab_host_faune_et_flore.getCurrentTab()).setBackgroundColor(Color.parseColor("#003399")); //2nd tab selected
                }
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

        changerLesOngletDeCouleur();

        creerListViewFaune();
        creerListViewFlore();
    }

    private void changerLesOngletDeCouleur() {
        tab_host_faune_et_flore.getTabWidget().getChildAt(1).setBackgroundColor(Color.BLUE);
        for(int i=0;i<tab_host_faune_et_flore.getTabWidget().getChildCount();i++)
            tab_host_faune_et_flore.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#0698D7"));
    }

    private void ajouterFont() {
        String cheminPolice = "fonts/font1.ttf";
        Typeface typePolice = Typeface.createFromAsset(getAssets(), cheminPolice);
        action_ajouter_flore.setTypeface(typePolice);
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
