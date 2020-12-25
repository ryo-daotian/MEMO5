package com.daotian.memo5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import static com.daotian.memo5.FragmentAdapter.position;


public class MainActivity extends AppCompatActivity {

    private FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    Toolbar toolbar;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    public static int groupMax=3;
    public static Bitmap childImages[][] = new Bitmap[groupMax][];
    private ColorBack colorback;
    private ColorFont colorFont;
    private List<String> groups;
    private List<List<String>> colors;
    ViewPager viewPager;
    private dataFragment dataFragment;
    private FragmentManager fragmentManager;
    private MenuItem title1;
    private AdView mAdView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        fragmentManager = getSupportFragmentManager();
        //Navigationviewのリスト
        groups=new ArrayList<String>();
        groups.add("backcolor");
        groups.add("fontcolor");

        List<String> insideview = new ArrayList<>();
        insideview.add("white");
        insideview.add("black");
        insideview.add("yellow");

        List<String> fontcolor = new ArrayList<>();
        fontcolor.add("white");
        fontcolor.add("black");
        fontcolor.add("red");
        fontcolor.add("blue");
        fontcolor.add("green");


        colors = new ArrayList<>();

        colors.add(insideview);
        colors.add(fontcolor);



        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(
                adapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("m e m o");
        setSupportActionBar(toolbar);

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy年MM月dd日" );
        String setdate = format.format(date);
        TextView datetext=findViewById(R.id.date);
        datetext.setText(setdate);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
        title1 =menu.findItem(R.id.optiontitle1);
        MenuItem title2 =menu.findItem(R.id.optiontitle2);
        MenuItem title3 =menu.findItem(R.id.optiontitle3);
        MenuItem title4 =menu.findItem(R.id.optiontitle4);
        MenuItem title5 =menu.findItem(R.id.optiontitle5);


        if(dataFragment==null){
            dataFragment = new dataFragment(this);
        }
        SQLiteDatabase db = dataFragment.getWritableDatabase();
        String optiontitle[] = new String[5];
        int i=0;
    try{
            //Cursor c = db.rawQuery("select title from frag_TABLE where position = '"+ 0 +"'", null);
            Cursor c = db.rawQuery("select title from frag_TABLE order by Id", null);
            boolean next = c.moveToFirst();
            while (next) {
                optiontitle[i]=c.getString(0);
                next = c.moveToNext();
                i++;
            }
        }finally {
            db.close();
        }
        title1.setTitle(optiontitle[0]);
        title2.setTitle(optiontitle[1]);
        title3.setTitle(optiontitle[2]);
        title4.setTitle(optiontitle[3]);
        title5.setTitle(optiontitle[4]);




    //title1.setTitle("dofja");



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                System.out.println("ゴミ箱");
                new AlertDialog.Builder(this)
                        .setTitle("メモをクリアしますか？"+"\n")
                        .setPositiveButton("いいえ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        })
                        .setNegativeButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (position) {
                                    case 0:
                                    EditText edit1 = findViewById(R.id.fragmemo1);
                                    edit1.getText().clear();
                                    break;
                                    case 1:
                                        EditText edit2 = findViewById(R.id.fragmemo2);
                                        edit2.getText().clear();
                                    break;
                                    case 2:
                                        EditText edit3 = findViewById(R.id.fragmemo3);
                                        edit3.getText().clear();
                                    break;
                                    case 3:
                                        EditText edit4 = findViewById(R.id.fragmemo4);
                                        edit4.getText().clear();
                                    break;
                                    case 4:
                                        EditText edit5 = findViewById(R.id.fragmemo5);
                                        edit5.getText().clear();
                                    break;

                                }

                            }
                        })
                .show();
                break;

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                switch (position) {
                    case 0:
                        EditText edit1 = findViewById(R.id.fragmemo1);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, edit1.getText().toString());
                        break;
                    case 1:
                        EditText edit2 = findViewById(R.id.fragmemo2);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, edit2.getText().toString());
                        break;
                    case 2:
                        EditText edit3 = findViewById(R.id.fragmemo3);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, edit3.getText().toString());
                        break;
                    case 3:
                        EditText edit4 = findViewById(R.id.fragmemo4);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, edit4.getText().toString());
                        break;
                    case 4:
                        EditText edit5 = findViewById(R.id.fragmemo5);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, edit5.getText().toString());
                        break;
                }

                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;

            case R.id.optiontitle1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.optiontitle2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.optiontitle3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.optiontitle4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.optiontitle5:
                viewPager.setCurrentItem(4);
                break;
        }
        return false;
    }



    private void prepareMenuData() {
        childImages[0] = new Bitmap[3];
        childImages[0][0] = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        childImages[0][1] = BitmapFactory.decodeResource(getResources(), R.drawable.black);
        childImages[0][2] = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        childImages[1] = new Bitmap[5];
        childImages[1][0] = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        childImages[1][1] = BitmapFactory.decodeResource(getResources(), R.drawable.black);
        childImages[1][2] = BitmapFactory.decodeResource(getResources(), R.drawable.fontred);
        childImages[1][3] = BitmapFactory.decodeResource(getResources(), R.drawable.fontblue);
        childImages[1][4] = BitmapFactory.decodeResource(getResources(), R.drawable.fontgreen);

    }


    private void populateExpandableList() {
        expandableListAdapter = new ExpandableAdapter(this,groups,colors);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
              colorback = new ColorBack(MainActivity.this);
              colorFont = new ColorFont(MainActivity.this);
                int backcolor = 0, fontcolor = 0;
                /**
                 * 背景内側
                 *
                 */
                if (groupPosition == 0 && childPosition == 0) {
                    backcolor = 1;
                    Backcolor2(backcolor);
                } else if (groupPosition == 0 && childPosition == 1) {
                    backcolor = 2;
                    Backcolor2(backcolor);
                } else if (groupPosition == 0 && childPosition == 2) {
                    backcolor = 3;
                    Backcolor2(backcolor);
                }

                if (groupPosition == 1 && childPosition == 0) {
                    fontcolor = 1;
                    Fontcolor2(fontcolor);
                } else if (groupPosition == 1 && childPosition == 1) {
                    fontcolor = 2;
                    Fontcolor2(fontcolor);
                } else if (groupPosition == 1 && childPosition == 2) {
                    fontcolor = 3;
                    Fontcolor2(fontcolor);
                } else if (groupPosition == 1 && childPosition == 3) {
                    fontcolor = 4;
                    Fontcolor2(fontcolor);
                } else if (groupPosition == 1 && childPosition == 4) {
                    fontcolor = 5;
                    Fontcolor2(fontcolor);
                }

                return false;
            }
        });


    }



    private void Fontcolor2(int fontcolor){
        if(position==0){
            colorFont.fragment1(fontcolor);
            ((fragmemo1) adapter.getFragment()).setcolorfont(fontcolor);
        }else if(position==1){
            colorFont.fragment2(fontcolor);
            ((fragmemo2) adapter.getFragment()).setcolorfont(fontcolor);
        }else if (position==2){
            colorFont.fragment3(fontcolor);
            ((fragmemo3) adapter.getFragment()).setcolorfont(fontcolor);
        }else if (position==3){
            colorFont.fragment4(fontcolor);
            ((fragmemo4) adapter.getFragment()).setcolorfont(fontcolor);
        }else if (position==4) {
            colorFont.fragment5(fontcolor);
            ((fragmemo5) adapter.getFragment()).setcolorfont(fontcolor);
        }

    }

    private void Backcolor2(int backcolor){
        if(position==0){
            colorback.fragment1(backcolor);
            ((fragmemo1) adapter.getFragment()).setbackcolor(backcolor);
        }else if(position==1){
            colorback.fragment2(backcolor);
            ((fragmemo2) adapter.getFragment()).setbackcolor(backcolor);
        }else if (position==2){
            colorback.fragment3(backcolor);
            ((fragmemo3) adapter.getFragment()).setbackcolor(backcolor);
        }else if (position==3){
            colorback.fragment4(backcolor);
            ((fragmemo4) adapter.getFragment()).setbackcolor(backcolor);
        }else if (position==4) {
            colorback.fragment5(backcolor);
            ((fragmemo5) adapter.getFragment()).setbackcolor(backcolor);
        }

    }

   void fontset(String fontset){
 toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(fontset+"文字");
    }




    }



