package ca.qc.cqmatane.informatique.cataloguefauneetflore.modele;

import java.util.HashMap;

/**
 * Created by Maxime on 20/09/2017.
 */

public class Espece {
    protected int id;
    protected String nom;
    protected String nomScientifique;
    protected String lieu;

    public Espece(String nom, String nomScientifique, String lieu) {
        this.nom = nom;
        this.nomScientifique = nomScientifique;
        this.lieu = lieu;
    }

    public Espece(int id,String nom, String nomScientifique, String lieu) {
        this.nom = nom;
        this.nomScientifique = nomScientifique;
        this.lieu = lieu;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public HashMap<String, String> exporterHashMap(){
        HashMap<String,String> faune = new HashMap<>();
        faune.put("Id", id+"");
        faune.put("Nom", nom);
        faune.put("NomScientifique", nomScientifique);
        faune.put("Lieu", lieu);
        return faune;
    }

    public int getId() {
        return id;
    }


}
