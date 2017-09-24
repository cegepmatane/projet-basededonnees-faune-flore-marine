package ca.qc.cqmatane.informatique.cataloguefauneetflore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class VueFloreModifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_flore_modifier);

        Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("Id"), Toast.LENGTH_SHORT).show();
    }
}
