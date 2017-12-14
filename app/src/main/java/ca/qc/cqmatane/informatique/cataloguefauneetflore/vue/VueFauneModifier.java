package ca.qc.cqmatane.informatique.cataloguefauneetflore.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.R;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FauneDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Faune;

public class VueFauneModifier extends AppCompatActivity {

    private FauneDAO accesseurFaune;
    private Faune faune;

    private EditText champNomFauneModifier;
    private EditText champNomScientifiqueFauneModifier;
    private EditText champLieuFauneModifier;
    private EditText champTypeFauneModifier;
    private EditText champPopulationFauneModifier;
    private EditText champURLFauneModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_faune_modifier);

        accesseurFaune = FauneDAO.getInstance();

        int id = Integer.parseInt(getIntent().getStringExtra("Id"));
        faune = accesseurFaune.trouverFaune(id);

        champNomFauneModifier = (EditText)findViewById(R.id.champ_nom_faune_modifier);
        champNomScientifiqueFauneModifier = (EditText)findViewById(R.id.champ_nom_scientifique_faune_modifier);
        champLieuFauneModifier = (EditText)findViewById(R.id.champ_lieu_faune_modifier);
        champTypeFauneModifier = (EditText)findViewById(R.id.champ_type_faune_modifier);
        champPopulationFauneModifier = (EditText)findViewById(R.id.champ_population_faune_modifier);
        champURLFauneModifier = (EditText)findViewById(R.id.champ_url_faune_modifier);

        champNomFauneModifier.setText(faune.getNom());
        champNomScientifiqueFauneModifier.setText(faune.getNomScientifique());
        champLieuFauneModifier.setText(faune.getLieu());
        champTypeFauneModifier.setText(faune.getType());
        champPopulationFauneModifier.setText(faune.getPopulation()+"");
        champURLFauneModifier.setText(faune.getUrl());
    }

    public void actionModifierFaune(View vue){
        faune.setNom(champNomFauneModifier.getText().toString());
        faune.setNomScientifique(champNomScientifiqueFauneModifier.getText().toString());
        faune.setLieu(champLieuFauneModifier.getText().toString());
        faune.setType(champTypeFauneModifier.getText().toString());
        faune.setPopulation(Integer.parseInt(champPopulationFauneModifier.getText().toString()));
        faune.setUrl(champURLFauneModifier.getText().toString());
        accesseurFaune.modifierFaune(faune);
        this.finish();
    }

    public void actionSupprimerFaune(View vue)
    {
        accesseurFaune.supprimerFaune(faune);
        this.finish();
    }
}
