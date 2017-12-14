package ca.qc.cqmatane.informatique.cataloguefauneetflore.vues;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.R;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FauneDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;

public class VueFauneAjouter extends AppCompatActivity {

    private FauneDAO accesseurFaune;
    private Faune faune;

    private EditText champNomFauneAjouter;
    private EditText champNomScientifiqueFauneAjouter;
    private EditText champLieuFauneAjouter;
    private EditText champTypeFauneAjouter;
    private EditText champPopulationFauneAjouter;
    private EditText champURLFauneAjouter;
    private TextView affichageVueFloreAjouterTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_faune_ajouter);
        accesseurFaune = FauneDAO.getInstance();

        champNomFauneAjouter = (EditText)findViewById(R.id.champ_nom_faune_ajouter);
        champNomScientifiqueFauneAjouter = (EditText)findViewById(R.id.champ_nom_scientifique_faune_ajouter);
        champLieuFauneAjouter = (EditText)findViewById(R.id.champ_lieu_faune_ajouter);
        champTypeFauneAjouter = (EditText)findViewById(R.id.champ_type_faune_ajouter);
        champPopulationFauneAjouter = (EditText)findViewById(R.id.champ_population_faune_ajouter);
        champURLFauneAjouter = (EditText)findViewById(R.id.champ_url_faune_ajouter);
        affichageVueFloreAjouterTitre = (TextView) findViewById(R.id.affichage_titre);
        System.out.println("affichage :" + (TextView) findViewById(R.id.affichage_titre));
        ajouterFont();
    }

    public void actionAjouterFaune(View vue){
        faune = new Faune(
                champNomFauneAjouter.getText().toString(),
                champNomScientifiqueFauneAjouter.getText().toString(),
                champLieuFauneAjouter.getText().toString(),
                champTypeFauneAjouter.getText().toString(),
                Integer.parseInt(champPopulationFauneAjouter.getText().toString()),
                champURLFauneAjouter.getText().toString()
        );
        accesseurFaune.ajouterFaune(faune);
        finish();
    }

    private void ajouterFont() {
        String cheminPolice = "fonts/font1.ttf";
        Typeface typePolice = Typeface.createFromAsset(getAssets(), cheminPolice);
        champNomFauneAjouter.setTypeface(typePolice);
        champNomScientifiqueFauneAjouter.setTypeface(typePolice);
        champLieuFauneAjouter.setTypeface(typePolice);
        champTypeFauneAjouter.setTypeface(typePolice);
        champPopulationFauneAjouter.setTypeface(typePolice);
        champURLFauneAjouter.setTypeface(typePolice);
        //affichageVueFloreAjouterTitre.setTypeface(typePolice);
    }
}
