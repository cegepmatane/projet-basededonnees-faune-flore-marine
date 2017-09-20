package ca.qc.cqmatane.informatique.cataloguefauneetflore.modele;

/**
 * Created by Maxime on 20/09/2017.
 */

public class Faune extends Espece {

    private String type;
    private int population;

    public Faune(String nom, String nomScientifique, String lieu, String type, int population) {
        super(nom, nomScientifique, lieu);
        this.type = type;
        this.population = population;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
