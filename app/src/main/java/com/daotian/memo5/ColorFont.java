package com.daotian.memo5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static com.daotian.memo5.FragmentAdapter.position;

public class ColorFont {
    private Context context;
    public  ColorFont(Context context){
        this.context=context;
    }

    dataFragment dataFragment=null;

    void fragment1(int fontcolor){
        insertcolor(fontcolor);
    }
    void fragment2(int fontcolor) {
        insertcolor(fontcolor);
    }
    void fragment3(int fontcolor){
        insertcolor(fontcolor);
    }
    void fragment4( int fontcolor){
        insertcolor(fontcolor);
    }
    void fragment5(int fontcolor){
        insertcolor(fontcolor);
    }
    void insertcolor(int fontcolor){
        if(dataFragment==null){
            dataFragment = new dataFragment(context);
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();
        try {
            db.execSQL("update frag_TABLE set fontcolor = '"+ fontcolor +"' where position = '"+position+"'");
        } finally {
            db.close();
        }
    }

}
