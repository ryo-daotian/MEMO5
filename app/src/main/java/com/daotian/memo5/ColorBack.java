package com.daotian.memo5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.daotian.memo5.FragmentAdapter.position;

public class ColorBack {
    private Context context;
    public ColorBack(Context context){
        this.context=context;
    }

    dataFragment dataFragment=null;

    void fragment1(int backcolor) {
        insertcolor(backcolor);
    }
    void fragment2(int backcolor) {
        insertcolor(backcolor);
    }
    void fragment3(int backcolor){
        insertcolor(backcolor);
    }
    void fragment4( int backcolor){
        insertcolor(backcolor);
    }
    void fragment5(int backcolor){
        insertcolor(backcolor);
    }

    void insertcolor(int backcolor){
        if(dataFragment==null){
            dataFragment = new dataFragment(context);
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();
        try {
            db.execSQL("update frag_TABLE set backcolor = '"+ backcolor +"' where position = '"+position+"'");

        } finally {
            db.close();
        }
    }

}
