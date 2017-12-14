package ca.qc.cqmatane.informatique.cataloguefauneetflore.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_faune_ajouter);
        accesseurFaune = FauneDAO.getInstance();

        Button buttonActionAjouterFaune = (Button)findViewById(R.id.action_ajouter_une_faune);

        buttonActionAjouterFaune.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                actionAjouterFaune(view);
            }
        });
    }

    public void actionAjouterFaune(View vue){

        champNomFauneAjouter = (EditText)findViewById(R.id.champ_nom_faune_ajouter);
        champNomScientifiqueFauneAjouter = (EditText)findViewById(R.id.champ_nom_scientifique_faune_ajouter);
        champLieuFauneAjouter = (EditText)findViewById(R.id.champ_lieu_faune_ajouter);
        champTypeFauneAjouter = (EditText)findViewById(R.id.champ_type_faune_ajouter);
        champPopulationFauneAjouter = (EditText)findViewById(R.id.champ_population_faune_ajouter);
        champURLFauneAjouter = (EditText)findViewById(R.id.champ_url_faune_ajouter);

        /*Log.d("test", champNomFauneAjouter.getText().toString());
        Log.d("test", champNomScientifiqueFauneAjouter.getText().toString());
        Log.d("test", champLieuFauneAjouter.getText().toString());
        Log.d("test", champTypeFauneAjouter.getText().toString());
        Log.d("test", champPopulationFauneAjouter.getText().toString());
        Log.d("test", champURLFauneAjouter.getText().toString());*/


        Integer population = 0;

        if(!(champPopulationFauneAjouter.getText().toString()).equals(""))
            population = Integer.parseInt(champPopulationFauneAjouter.getText().toString());
        else
            population = 1;

        faune = new Faune(
                champNomFauneAjouter.getText().toString(),
                champNomScientifiqueFauneAjouter.getText().toString(),
                champLieuFauneAjouter.getText().toString(),
                champTypeFauneAjouter.getText().toString(),
                population,
                champURLFauneAjouter.getText().toString()
        );

        //Log.d("test", faune.exporterHashMap().toString());

        if(!(champNomFauneAjouter.getText().toString()).equals("") ||
                !(champNomScientifiqueFauneAjouter.getText().toString()).equals("") ||
                !(champLieuFauneAjouter.getText().toString()).equals("") ||
                !(champTypeFauneAjouter.getText().toString()).equals("") ||
                !(champURLFauneAjouter.getText().toString()).equals("") )
        {
            accesseurFaune.ajouterFaune(faune);
            this.finish();
        }
    }
}
