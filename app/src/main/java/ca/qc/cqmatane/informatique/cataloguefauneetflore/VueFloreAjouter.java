package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees.FloreDAO;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Flore;

public class VueFloreAjouter extends AppCompatActivity {

    private FloreDAO accesseurFlore;
    private Flore flore;

    private EditText champNomFloreAjouter;
    private EditText champNomScientifiqueFloreAjouter;
    private EditText champLieuFloreAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_flore_ajouter);
        accesseurFlore = FloreDAO.getInstance();
    }

    public void actionAjouterFlore(View vue){

        champNomFloreAjouter = (EditText)findViewById(R.id.champ_nom_flore_ajouter);
        champNomScientifiqueFloreAjouter = (EditText)findViewById(R.id.champ_nom_scientifique_flore_ajouter);
        champLieuFloreAjouter = (EditText)findViewById(R.id.champ_lieu_flore_ajouter);

        flore = new Flore(champNomFloreAjouter.getText().toString(),champNomScientifiqueFloreAjouter.getText().toString(),champLieuFloreAjouter.getText().toString());

        accesseurFlore.ajouterFlore(flore);
        finish();
    }
}
