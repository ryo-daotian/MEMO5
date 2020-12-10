package com.daotian.memo5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataFragment extends SQLiteOpenHelper {
    static final private String DBName="Frag_DB";
    static final int VERSION=3;
    public dataFragment(Context context){
        super(context,DBName,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE frag_TABLE("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "position INTEGER,"+
                "text TEXT,"+
                "fontcolor INTEGER,"+
                "textTime TEXT,"+
                "title TEXT,"+
                "backcolor INTEGER)");

        sqLiteDatabase.execSQL("insert into frag_TABLE(position)VALUES(0)");
        sqLiteDatabase.execSQL("insert into frag_TABLE(position)VALUES(1)");
        sqLiteDatabase.execSQL("insert into frag_TABLE(position)VALUES(2)");
        sqLiteDatabase.execSQL("insert into frag_TABLE(position)VALUES(3)");
        sqLiteDatabase.execSQL("insert into frag_TABLE(position)VALUES(4)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS frag_TABLE");
        onCreate(sqLiteDatabase);
    }
}
