package com.daotian.memo5;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class fragmemo2 extends Fragment implements TextWatcher {
    dataFragment dataFragment = null;
    private View v;
    private int  j = 0,fontcount=0;
    private EditText edit2,title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        title=((com.daotian.memo5.MainActivity)getContext()).findViewById(R.id.title);
        v = inflater.inflate(R.layout.fragmemo2, null);
        int backcolorStart = 0;
        int fontcolorStart = 0;
        String text;
        edit2 = v.findViewById(R.id.fragmemo2);
        edit2.addTextChangedListener(this);

        if (dataFragment == null) {
            dataFragment = new dataFragment(getActivity());
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();


        try {

            Cursor c = db.rawQuery("select fontcolor,backcolor,text from frag_TABLE where position = '" + 1 + "'", null);
            boolean next = c.moveToFirst();
            while (next) {
                fontcolorStart = c.getInt(0);
                backcolorStart = c.getInt(1);
                text = c.getString(2);
                edit2.setText(text, TextView.BufferType.NORMAL);
                next = c.moveToNext();
            }
        } finally {
            db.close();
        }
        if (fontcolorStart == 1) {
            edit2.setTextColor(Color.WHITE);
        } else if (fontcolorStart == 2) {
            edit2.setTextColor(Color.BLACK);
        } else if (fontcolorStart == 3) {
            edit2.setTextColor(Color.RED);
        } else if (fontcolorStart == 4) {
            edit2.setTextColor(Color.BLUE);
        } else if (fontcolorStart == 5) {
            edit2.setTextColor(Color.GREEN);
        }


        if (backcolorStart == 1) {
            edit2.setBackgroundColor(Color.WHITE);
        } else if (backcolorStart == 2) {
            edit2.setBackgroundColor(Color.BLACK);
        } else if (backcolorStart == 3) {
            edit2.setBackgroundColor(getResources().getColor(R.color.backyellow));

        }

        return v;
    }


    void setcolorfont(int fontcolor) {
        EditText edit2 = getActivity().findViewById(R.id.fragmemo2);

        if (fontcolor == 1) {
            edit2.setTextColor(Color.WHITE);
        } else if (fontcolor == 2) {
            edit2.setTextColor(Color.BLACK);
        } else if (fontcolor == 3) {
            edit2.setTextColor(Color.RED);
        } else if (fontcolor == 4) {
            edit2.setTextColor(Color.BLUE);
        } else if (fontcolor == 5) {
            edit2.setTextColor(Color.GREEN);
        }

    }

    void setbackcolor(int backcolor) {
        EditText edit2 = getActivity().findViewById(R.id.fragmemo2);

        if (backcolor == 1) {
            edit2.setBackgroundColor(Color.WHITE);
        } else if (backcolor == 2) {
            edit2.setBackgroundColor(Color.BLACK);
        } else if (backcolor == 3) {
            edit2.setBackgroundColor(getResources().getColor(R.color.backyellow));

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        EditText title = (EditText) ((com.daotian.memo5.MainActivity) getActivity()).findViewById(R.id.title);

        EditText edit = getActivity().findViewById(R.id.fragmemo2);
        String text,title2;
        text = edit.getText().toString();
        title2=title.getText().toString();
        if (dataFragment == null) {
            dataFragment = new dataFragment(getActivity());
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();
        try {
            db.execSQL("update frag_TABLE set text = '" + text + "' where position = '" + 1 + "'");
            db.execSQL("update frag_TABLE set title = '" + title2 + "' where position = '" + 1 + "'");

        } finally {
            db.close();
        }


    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        MainActivity mainActivity = (MainActivity) getActivity();
        String count = String.valueOf(charSequence);
        j = 0;

        String[] ar = count.split("");
        for (String ar2 : ar) {

            if (charSequence.length() == 0) {
                j = 0;
            } else if (ar2.equals("　") || ar2.equals(" ")) {

            } else if (ar2.equals("\n")) {

            } else {
                j++;

            }
        }

        String fontset = String.valueOf(j);
        mainActivity.fontset(fontset);
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }


    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity=(MainActivity)getActivity();
        String count = edit2.getText().toString();
        fontcount=0;
        String[] ar = count.split("");

        for (String ar2 : ar) {

            if(count.length()==0){
                fontcount=0;
            } else if (ar2.equals("　") || ar2.equals(" ")) {

            }else if (ar2.equals("\n")) {

            }else{
                fontcount++;
            }
        }

        String fontset=String.valueOf(fontcount);
        mainActivity.fontset(fontset);

        String title2;
        if(dataFragment==null){
            dataFragment = new dataFragment(getActivity());
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();

        try{

            Cursor c = db.rawQuery("select title from frag_TABLE where position = '"+ 1 +"'", null);
            boolean next = c.moveToFirst();
            while (next) {
                title2=c.getString(0);
                title.setText(title2,TextView.BufferType.NORMAL);
                next = c.moveToNext();
            }
        }finally {
            db.close();
        }

    }

    }






