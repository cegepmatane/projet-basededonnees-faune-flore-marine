package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class VueFauneModifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_faune_modifier);

        Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("idFaune"), Toast.LENGTH_SHORT).show();
    }
}