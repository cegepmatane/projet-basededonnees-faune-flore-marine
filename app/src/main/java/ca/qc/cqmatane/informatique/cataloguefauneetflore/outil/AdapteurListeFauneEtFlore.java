package ca.qc.cqmatane.informatique.cataloguefauneetflore.outil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ca.qc.cqmatane.informatique.cataloguefauneetflore.R;
import ca.qc.cqmatane.informatique.cataloguefauneetflore.modele.Espece;

public class AdapteurListeFauneEtFlore extends BaseAdapter {
    private ArrayList listeDonnees;
    private LayoutInflater inflateurVue;

    public AdapteurListeFauneEtFlore(Context contexte, ArrayList listeDonnees){
        this.listeDonnees = listeDonnees;
        inflateurVue = LayoutInflater.from(contexte);
    }

    @Override
    public int getCount() {
        return listeDonnees.size();
    }

    @Override
    public Object getItem(int position) {
        return listeDonnees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View vue, ViewGroup parent) {
        ConteneurVue conteneur;
        if(vue == null){
            vue = inflateurVue.inflate(R.layout.faune_et_flore_afficher_layout_custom_element_liste, null);
            conteneur = new ConteneurVue();
            conteneur.affichageImage = (ImageView) vue.findViewById(R.id.affichage_image);
            conteneur.affichageNom = (TextView) vue.findViewById(R.id.affichage_nom);
            conteneur.affichageNomScientifique = (TextView) vue.findViewById(R.id.affichage_nom_scientifique);
            conteneur.affichageLieu = (TextView) vue.findViewById(R.id.affichage_lieu);
            vue.setTag(conteneur);
        }
        else{
            conteneur = (ConteneurVue) vue.getTag();
        }

        Espece itemEspece = (Espece) listeDonnees.get(position);
        if(conteneur.affichageImage != null){
            new TacheAsynchroneTelechargementImage(conteneur.affichageImage).execute(itemEspece.getUrl());
        }
        conteneur.affichageNom.setText(itemEspece.getNom());
        conteneur.affichageNomScientifique.setText(itemEspece.getNomScientifique());
        conteneur.affichageLieu.setText(itemEspece.getLieu());
        return vue;
    }

    static class ConteneurVue{
        ImageView affichageImage;
        TextView affichageNom;
        TextView affichageNomScientifique;
        TextView affichageLieu;
    }
}
