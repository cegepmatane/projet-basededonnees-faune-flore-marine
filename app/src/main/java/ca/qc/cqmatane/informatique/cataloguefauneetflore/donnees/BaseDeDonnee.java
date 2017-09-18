package ca.qc.cqmatane.informatique.cataloguefauneetflore.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maxime on 18/09/2017.
 */

public class BaseDeDonnee extends SQLiteOpenHelper {

        private static BaseDeDonnee instance = null;

        public static BaseDeDonnee getInstance(Context contexte) {
            if (null == instance) instance = new BaseDeDonnee(contexte);
            return instance;
        }

        public static BaseDeDonnee getInstance() {
            return instance;
        }

        public BaseDeDonnee(Context contexte) {
            super(contexte, "catalogue_faune_et_flore", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE = "";
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            String CREATE_TABLE = "";
            db.execSQL(CREATE_TABLE);        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            //db.execSQL("DROP TABLE rendez_vous");
            String CREATE_TABLE = "";
            db.execSQL(CREATE_TABLE);
        }

    }
