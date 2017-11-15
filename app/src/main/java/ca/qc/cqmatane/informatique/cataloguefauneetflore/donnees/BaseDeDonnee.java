package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnee extends SQLiteOpenHelper {

    private static BaseDeDonnee instance = null;

    public static BaseDeDonnee getInstance(Context contexte) {
        if (null == instance) instance = new BaseDeDonnee(contexte);
        return instance;
    }

    private static String REQUETE_CREATION_TABLE_FAUNE = "CREATE TABLE IF NOT EXISTS faune(idFaune INTEGER PRIMARY KEY, nom TEXT, nomScientifique TEXT, lieu TEXT, type TEXT, population TEXT, urlImage TEXT)";
    private static String REQUETE_CREATION_TABLE_FLAURE = "CREATE TABLE IF NOT EXISTS flore(idFlore INTEGER PRIMARY KEY, nom TEXT, nomScientifique TEXT, lieu TEXT, urlImage TEXT)";

    public static BaseDeDonnee getInstance() {
        return instance;
    }

    public BaseDeDonnee(Context contexte) {
        super(contexte, "catalogue_faune_et_flore", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_TABLE_FAUNE);
        db.execSQL(REQUETE_CREATION_TABLE_FLAURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL(REQUETE_CREATION_TABLE_FAUNE);
        db.execSQL(REQUETE_CREATION_TABLE_FLAURE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_TABLE_FAUNE);
        db.execSQL(REQUETE_CREATION_TABLE_FLAURE);
    }

}
