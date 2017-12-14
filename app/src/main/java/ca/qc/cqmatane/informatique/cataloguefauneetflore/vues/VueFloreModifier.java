package ca.qc.cqmatane.informatique.cataloguefauneetflore.vues;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.R;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FloreDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Flore;

public class VueFloreModifier extends AppCompatActivity {

    private FloreDAO accesseurFlore;
    private Flore flore;

    private EditText champNomFloreModifier;
    private EditText champNomScientifiqueFloreModifier;
    private EditText champLieuFloreModifier;
    private EditText champURLFloreModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_flore_modifier);

        accesseurFlore = FloreDAO.getInstance();

        int id = Integer.parseInt(getIntent().getStringExtra("Id"));
        flore = accesseurFlore.trouverFlore(id);

        champNomFloreModifier = (EditText)findViewById(R.id.champ_nom_flore_modifier);
        champNomScientifiqueFloreModifier = (EditText)findViewById(R.id.champ_nom_scientifique_flore_modifier);
        champLieuFloreModifier = (EditText)findViewById(R.id.champ_lieu_flore_modifier);
        champURLFloreModifier = (EditText)findViewById(R.id.champ_url_flore_modifier);

        champNomFloreModifier.setText(flore.getNom());
        champNomScientifiqueFloreModifier.setText(flore.getNomScientifique());
        champLieuFloreModifier.setText(flore.getLieu());
        champURLFloreModifier.setText(flore.getUrl());
    }

    public void actionModifierFlore(View vue){
        flore.setNom(champNomFloreModifier.getText().toString());
        flore.setNomScientifique(champNomScientifiqueFloreModifier.getText().toString());
        flore.setLieu(champLieuFloreModifier.getText().toString());
        flore.setUrl(champURLFloreModifier.getText().toString());
        accesseurFlore.modifierFlore(flore);
        finish();
    }

    public void actionSupprimerFlore(View vue)
    {
        accesseurFlore.supprimerFlore(flore);
        finish();
    }
}
