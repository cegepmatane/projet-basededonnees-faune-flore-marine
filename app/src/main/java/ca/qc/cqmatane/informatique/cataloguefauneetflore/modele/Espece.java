package ca.qc.cqmatane.informatique.cataloguefauneetflore.modele;

/**
 * Created by Maxime on 20/09/2017.
 */

public class Espece {
    protected String nom;
    protected String nomScientifique;
    protected String lieu;
    protected int id;

    public Espece(String nom, String nomScientifique, String lieu) {
        this.nom = nom;
        this.nomScientifique = nomScientifique;
        this.lieu = lieu;
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
}
