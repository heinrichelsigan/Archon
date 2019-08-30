/*
*
* @author           Heinrich Elsigan
* @version          V 1.0.1
* @since            API 27 Oreo
*
*/
/*
	Copyright (C) 2019 Heinrich Elsigan (heinrich.elsigan@area23.at)

	Archon is a classic fantasy chess game
	1st implementation 1983 by Free Fall Associates and one of the first five games published by Electronic Arts.    
	https://en.wikipedia.org/wiki/Archon:_The_Light_and_the_Dark
   
	Archon android application port is free software; you can redistribute it and/or
	modify it under the terms of the GNU Library General Public License as
	published by the Free Software Foundation; either version 2 of the
	License, or (at your option) any later version.
	See the GNU Library General Public License for more details.

*/
package at.area23.archon;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import java.util.Objects;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    LinearLayout a8, b8, c8, d8, e8, f8, g8, h8, i8;
    LinearLayout a7, b7, c7, d7, e7, f7, g7, h7, i7;
    LinearLayout a6, b6, c6, d6, e6, f6, g6, h6, i6;
    LinearLayout a5, b5, c5, d5, e5, f5, g5, h5, i5;
    LinearLayout a4, b4, c4, d4, e4, f4, g4, h4, i4;
    LinearLayout a3, b3, c3, d3, e3, f3, g3, h3, i3;
    LinearLayout a2, b2, c2, d2, e2, f2, g2, h2, i2;
    LinearLayout a1, b1, c1, d1, e1, f1, g1, h1, i1;
    LinearLayout a0, b0, c0, d0, e0, f0, g0, h0, i0;
    LinearLayout currentLayout;

    ImageView myimageA8, myimageB8, myimageC8, myimageD8, myimageE8, myimageF8, myimageG8, myimageH8, myimageI8;
    ImageView myimageA7, myimageB7, myimageC7, myimageD7, myimageE7, myimageF7, myimageG7, myimageH7, myimageI7;
    ImageView myimageA1, myimageB1, myimageC1, myimageD1, myimageE1, myimageF1, myimageG1, myimageH1, myimageI1;
    ImageView myimageA0, myimageB0, myimageC0, myimageD0, myimageE0, myimageF0, myimageG0, myimageH0, myimageI0;
    ImageView currentImage;

    Menu myMenu;

    HashMap<Integer,ImageView> ImageRessources = new HashMap<Integer,ImageView>();
    HashMap<Integer, LinearLayout> LinearLayoutRessources = new HashMap<Integer, LinearLayout>();

    int index = 4;
    volatile int errNum = 0;
    String tmp = "";
    volatile boolean started = false;
    volatile int startedTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitLinearLayoutArchonFields();
        InitLinearLayoutRessourcesMap();

        InitImageArchonFields();
        InitImageRessourcesMap();

        InitImageOnTouchListeners();
        InitLinearLayoutOnDragListeners();
    }


    /**
     * Init local variables to LinearLayout Archon (chess) board fields
     */
    private void InitLinearLayoutArchonFields() {

        a8 = (LinearLayout) findViewById(R.id.A8);
        b8 = (LinearLayout) findViewById(R.id.B8);
        c8 = (LinearLayout) findViewById(R.id.C8);
        d8 = (LinearLayout) findViewById(R.id.D8);
        e8 = (LinearLayout) findViewById(R.id.E8);
        f8 = (LinearLayout) findViewById(R.id.F8);
        g8 = (LinearLayout) findViewById(R.id.G8);
        h8 = (LinearLayout) findViewById(R.id.H8);
        i8 = (LinearLayout) findViewById(R.id.I8);

        a7 = (LinearLayout) findViewById(R.id.A7);
        b7 = (LinearLayout) findViewById(R.id.B7);
        c7 = (LinearLayout) findViewById(R.id.C7);
        d7 = (LinearLayout) findViewById(R.id.D7);
        e7 = (LinearLayout) findViewById(R.id.E7);
        f7 = (LinearLayout) findViewById(R.id.F7);
        g7 = (LinearLayout) findViewById(R.id.G7);
        h7 = (LinearLayout) findViewById(R.id.H7);
        i7 = (LinearLayout) findViewById(R.id.I7);

        a6 = (LinearLayout) findViewById(R.id.A6);
        b6 = (LinearLayout) findViewById(R.id.B6);
        c6 = (LinearLayout) findViewById(R.id.C6);
        d6 = (LinearLayout) findViewById(R.id.D6);
        e6 = (LinearLayout) findViewById(R.id.E6);
        f6 = (LinearLayout) findViewById(R.id.F6);
        g6 = (LinearLayout) findViewById(R.id.G6);
        h6 = (LinearLayout) findViewById(R.id.H6);
        i6 = (LinearLayout) findViewById(R.id.I6);

        a5 = (LinearLayout) findViewById(R.id.A5);
        b5 = (LinearLayout) findViewById(R.id.B5);
        c5 = (LinearLayout) findViewById(R.id.C5);
        d5 = (LinearLayout) findViewById(R.id.D5);
        e5 = (LinearLayout) findViewById(R.id.E5);
        f5 = (LinearLayout) findViewById(R.id.F5);
        g5 = (LinearLayout) findViewById(R.id.G5);
        h5 = (LinearLayout) findViewById(R.id.H5);
        i5 = (LinearLayout) findViewById(R.id.I5);

        a4 = (LinearLayout) findViewById(R.id.A4);
        b4 = (LinearLayout) findViewById(R.id.B4);
        c4 = (LinearLayout) findViewById(R.id.C4);
        d4 = (LinearLayout) findViewById(R.id.D4);
        e4 = (LinearLayout) findViewById(R.id.E4);
        f4 = (LinearLayout) findViewById(R.id.F4);
        g4 = (LinearLayout) findViewById(R.id.G4);
        h4 = (LinearLayout) findViewById(R.id.H4);
        i4 = (LinearLayout) findViewById(R.id.I4);

        a3 = (LinearLayout) findViewById(R.id.A3);
        b3 = (LinearLayout) findViewById(R.id.B3);
        c3 = (LinearLayout) findViewById(R.id.C3);
        d3 = (LinearLayout) findViewById(R.id.D3);
        e3 = (LinearLayout) findViewById(R.id.E3);
        f3 = (LinearLayout) findViewById(R.id.F3);
        g3 = (LinearLayout) findViewById(R.id.G3);
        h3 = (LinearLayout) findViewById(R.id.H3);
        i3 = (LinearLayout) findViewById(R.id.I3);

        a2 = (LinearLayout) findViewById(R.id.A2);
        b2 = (LinearLayout) findViewById(R.id.B2);
        c2 = (LinearLayout) findViewById(R.id.C2);
        d2 = (LinearLayout) findViewById(R.id.D2);
        e2 = (LinearLayout) findViewById(R.id.E2);
        f2 = (LinearLayout) findViewById(R.id.F2);
        g2 = (LinearLayout) findViewById(R.id.G2);
        h2 = (LinearLayout) findViewById(R.id.H2);
        i2 = (LinearLayout) findViewById(R.id.I2);

        a1 = (LinearLayout) findViewById(R.id.A1);
        b1 = (LinearLayout) findViewById(R.id.B1);
        c1 = (LinearLayout) findViewById(R.id.C1);
        d1 = (LinearLayout) findViewById(R.id.D1);
        e1 = (LinearLayout) findViewById(R.id.E1);
        f1 = (LinearLayout) findViewById(R.id.F1);
        g1 = (LinearLayout) findViewById(R.id.G1);
        h1 = (LinearLayout) findViewById(R.id.H1);
        i1 = (LinearLayout) findViewById(R.id.I1);

        a0 = (LinearLayout) findViewById(R.id.A0);
        b0 = (LinearLayout) findViewById(R.id.B0);
        c0 = (LinearLayout) findViewById(R.id.C0);
        d0 = (LinearLayout) findViewById(R.id.D0);
        e0 = (LinearLayout) findViewById(R.id.E0);
        f0 = (LinearLayout) findViewById(R.id.F0);
        g0 = (LinearLayout) findViewById(R.id.G0);
        h0 = (LinearLayout) findViewById(R.id.H0);
        i0 = (LinearLayout) findViewById(R.id.I0);
    }


    /**
     * Init local variables to images inside LinearLayout Archon (chess) board fields
     */
    private void InitImageArchonFields() {

        myimageA8 = (ImageView) findViewById(R.id.myimageA8);
        myimageB8 = (ImageView) findViewById(R.id.myimageB8);
        myimageC8 = (ImageView) findViewById(R.id.myimageC8);
        myimageD8 = (ImageView) findViewById(R.id.myimageD8);
        myimageE8 = (ImageView) findViewById(R.id.myimageE8);
        myimageF8 = (ImageView) findViewById(R.id.myimageF8);
        myimageG8 = (ImageView) findViewById(R.id.myimageG8);
        myimageH8 = (ImageView) findViewById(R.id.myimageH8);
        myimageI8 = (ImageView) findViewById(R.id.myimageI8);

        myimageA7 = (ImageView) findViewById(R.id.myimageA7);
        myimageB7 = (ImageView) findViewById(R.id.myimageB7);
        myimageC7 = (ImageView) findViewById(R.id.myimageC7);
        myimageD7 = (ImageView) findViewById(R.id.myimageD7);
        myimageE7 = (ImageView) findViewById(R.id.myimageE7);
        myimageF7 = (ImageView) findViewById(R.id.myimageF7);
        myimageG7 = (ImageView) findViewById(R.id.myimageG7);
        myimageH7 = (ImageView) findViewById(R.id.myimageH7);
        myimageI7 = (ImageView) findViewById(R.id.myimageI7);

        myimageA1 = (ImageView) findViewById(R.id.myimageA1);
        myimageB1 = (ImageView) findViewById(R.id.myimageB1);
        myimageC1 = (ImageView) findViewById(R.id.myimageC1);
        myimageD1 = (ImageView) findViewById(R.id.myimageD1);
        myimageE1 = (ImageView) findViewById(R.id.myimageE1);
        myimageF1 = (ImageView) findViewById(R.id.myimageF1);
        myimageG1 = (ImageView) findViewById(R.id.myimageG1);
        myimageH1 = (ImageView) findViewById(R.id.myimageH1);
        myimageI1 = (ImageView) findViewById(R.id.myimageI1);

        myimageA0 = (ImageView) findViewById(R.id.myimageA0);
        myimageB0 = (ImageView) findViewById(R.id.myimageB0);
        myimageC0 = (ImageView) findViewById(R.id.myimageC0);
        myimageD0 = (ImageView) findViewById(R.id.myimageD0);
        myimageE0 = (ImageView) findViewById(R.id.myimageE0);
        myimageF0 = (ImageView) findViewById(R.id.myimageF0);
        myimageG0 = (ImageView) findViewById(R.id.myimageG0);
        myimageH0 = (ImageView) findViewById(R.id.myimageH0);
        myimageI0 = (ImageView) findViewById(R.id.myimageI0);
    }


    /**
     * Add local variables images corresponding to R.id ressources to ImageRessources HashMap
     */
    private void InitImageRessourcesMap() {
        // Clear Ressource Map first to avoid duplicated entries in HashMap, when method is called more than once
        ImageRessources.clear();
        // Init image ressource map
        ImageRessources.put(R.id.myimageA8, myimageA8);
        ImageRessources.put(R.id.myimageB8, myimageB8);
        ImageRessources.put(R.id.myimageC8, myimageC8);
        ImageRessources.put(R.id.myimageD8, myimageD8);
        ImageRessources.put(R.id.myimageE8, myimageE8);
        ImageRessources.put(R.id.myimageF8, myimageF8);
        ImageRessources.put(R.id.myimageG8, myimageG8);
        ImageRessources.put(R.id.myimageH8, myimageH8);
        ImageRessources.put(R.id.myimageI8, myimageI8);

        ImageRessources.put(R.id.myimageA7, myimageA7);
        ImageRessources.put(R.id.myimageB7, myimageB7);
        ImageRessources.put(R.id.myimageC7, myimageC7);
        ImageRessources.put(R.id.myimageD7, myimageD7);
        ImageRessources.put(R.id.myimageE7, myimageE7);
        ImageRessources.put(R.id.myimageF7, myimageF7);
        ImageRessources.put(R.id.myimageG7, myimageG7);
        ImageRessources.put(R.id.myimageH7, myimageH7);
        ImageRessources.put(R.id.myimageI7, myimageI7);

        ImageRessources.put(R.id.myimageA1, myimageA1);
        ImageRessources.put(R.id.myimageB1, myimageB1);
        ImageRessources.put(R.id.myimageC1, myimageC1);
        ImageRessources.put(R.id.myimageD1, myimageD1);
        ImageRessources.put(R.id.myimageE1, myimageE1);
        ImageRessources.put(R.id.myimageF1, myimageF1);
        ImageRessources.put(R.id.myimageG1, myimageG1);
        ImageRessources.put(R.id.myimageH1, myimageH1);
        ImageRessources.put(R.id.myimageI1, myimageI1);

        ImageRessources.put(R.id.myimageA0, myimageA0);
        ImageRessources.put(R.id.myimageB0, myimageB0);
        ImageRessources.put(R.id.myimageC0, myimageC0);
        ImageRessources.put(R.id.myimageD0, myimageD0);
        ImageRessources.put(R.id.myimageE0, myimageE0);
        ImageRessources.put(R.id.myimageF0, myimageF0);
        ImageRessources.put(R.id.myimageG0, myimageG0);
        ImageRessources.put(R.id.myimageH0, myimageH0);
        ImageRessources.put(R.id.myimageI0, myimageI0);
    }


    /**
     * Add local variables linear layouts corresponding to R.id ressources to InitLinearLayoutRessourcesMap
     */
    private void InitLinearLayoutRessourcesMap() {
        LinearLayoutRessources.clear();
        LinearLayoutRessources.put(R.id.A8, a8);
        LinearLayoutRessources.put(R.id.B8, b8);
        LinearLayoutRessources.put(R.id.C8, c8);
        LinearLayoutRessources.put(R.id.D8, d8);
        LinearLayoutRessources.put(R.id.E8, e8);
        LinearLayoutRessources.put(R.id.F8, f8);
        LinearLayoutRessources.put(R.id.G8, g8);
        LinearLayoutRessources.put(R.id.H8, h8);
        LinearLayoutRessources.put(R.id.I8, i8);

        LinearLayoutRessources.put(R.id.A7, a7);
        LinearLayoutRessources.put(R.id.B7, b7);
        LinearLayoutRessources.put(R.id.C7, c7);
        LinearLayoutRessources.put(R.id.D7, d7);
        LinearLayoutRessources.put(R.id.E7, e7);
        LinearLayoutRessources.put(R.id.F7, f7);
        LinearLayoutRessources.put(R.id.G7, g7);
        LinearLayoutRessources.put(R.id.H7, h7);
        LinearLayoutRessources.put(R.id.I7, i7);

        LinearLayoutRessources.put(R.id.A6, a6);
        LinearLayoutRessources.put(R.id.B6, b6);
        LinearLayoutRessources.put(R.id.C6, c6);
        LinearLayoutRessources.put(R.id.D6, d6);
        LinearLayoutRessources.put(R.id.E6, e6);
        LinearLayoutRessources.put(R.id.F6, f6);
        LinearLayoutRessources.put(R.id.G6, g6);
        LinearLayoutRessources.put(R.id.H6, h6);
        LinearLayoutRessources.put(R.id.I6, i6);

        LinearLayoutRessources.put(R.id.A5, a5);
        LinearLayoutRessources.put(R.id.B5, b5);
        LinearLayoutRessources.put(R.id.C5, c5);
        LinearLayoutRessources.put(R.id.D5, d5);
        LinearLayoutRessources.put(R.id.E5, e5);
        LinearLayoutRessources.put(R.id.F5, f5);
        LinearLayoutRessources.put(R.id.G5, g5);
        LinearLayoutRessources.put(R.id.H5, h5);
        LinearLayoutRessources.put(R.id.I5, i5);

        LinearLayoutRessources.put(R.id.A4, a4);
        LinearLayoutRessources.put(R.id.B4, b4);
        LinearLayoutRessources.put(R.id.C4, c4);
        LinearLayoutRessources.put(R.id.D4, d4);
        LinearLayoutRessources.put(R.id.E4, e4);
        LinearLayoutRessources.put(R.id.F4, f4);
        LinearLayoutRessources.put(R.id.G4, g4);
        LinearLayoutRessources.put(R.id.H4, h4);
        LinearLayoutRessources.put(R.id.I4, i4);

        LinearLayoutRessources.put(R.id.A3, a3);
        LinearLayoutRessources.put(R.id.B3, b3);
        LinearLayoutRessources.put(R.id.C3, c3);
        LinearLayoutRessources.put(R.id.D3, d3);
        LinearLayoutRessources.put(R.id.E3, e3);
        LinearLayoutRessources.put(R.id.F3, f3);
        LinearLayoutRessources.put(R.id.G3, g3);
        LinearLayoutRessources.put(R.id.H3, h3);
        LinearLayoutRessources.put(R.id.I3, i3);

        LinearLayoutRessources.put(R.id.A2, a2);
        LinearLayoutRessources.put(R.id.B2, b2);
        LinearLayoutRessources.put(R.id.C2, c2);
        LinearLayoutRessources.put(R.id.D2, d2);
        LinearLayoutRessources.put(R.id.E2, e2);
        LinearLayoutRessources.put(R.id.F2, f2);
        LinearLayoutRessources.put(R.id.G2, g2);
        LinearLayoutRessources.put(R.id.H2, h2);
        LinearLayoutRessources.put(R.id.I2, i2);

        LinearLayoutRessources.put(R.id.A1, a1);
        LinearLayoutRessources.put(R.id.B1, b1);
        LinearLayoutRessources.put(R.id.C1, c1);
        LinearLayoutRessources.put(R.id.D1, d1);
        LinearLayoutRessources.put(R.id.E1, e1);
        LinearLayoutRessources.put(R.id.F1, f1);
        LinearLayoutRessources.put(R.id.G1, g1);
        LinearLayoutRessources.put(R.id.H1, h1);
        LinearLayoutRessources.put(R.id.I1, i1);

        LinearLayoutRessources.put(R.id.A0, a0);
        LinearLayoutRessources.put(R.id.B0, b0);
        LinearLayoutRessources.put(R.id.C0, c0);
        LinearLayoutRessources.put(R.id.D0, d0);
        LinearLayoutRessources.put(R.id.E0, e0);
        LinearLayoutRessources.put(R.id.F0, f0);
        LinearLayoutRessources.put(R.id.G0, g0);
        LinearLayoutRessources.put(R.id.H0, h0);
        LinearLayoutRessources.put(R.id.I0, i0);
    }


    /**
     * Resets board map background color & diamonds
     */
    private void ResetBackgroundMap() {
        findViewById(R.id.A8).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.B8).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.C8).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.D8).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.E8).setBackground(getResources().getDrawable(R.drawable.bgblackdiamond, getApplicationContext().getTheme()));
        findViewById(R.id.F8).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.G8).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.H8).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.I8).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));

        findViewById(R.id.A7).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.B7).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.C7).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.D7).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.E7).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.F7).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.G7).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.H7).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.I7).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));

        findViewById(R.id.A6).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.B6).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.C6).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.D6).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.E6).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.F6).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.G6).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.H6).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.I6).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));

        findViewById(R.id.A5).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.B5).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.C5).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.D5).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.E5).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.F5).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.G5).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.H5).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.I5).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));

        findViewById(R.id.A4).setBackground(getResources().getDrawable(R.drawable.bgblackdiamond, getApplicationContext().getTheme()));
        findViewById(R.id.B4).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.C4).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.D4).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.E4).setBackground(getResources().getDrawable(R.drawable.bgblackdiamond, getApplicationContext().getTheme()));
        findViewById(R.id.F4).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.G4).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.H4).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.I4).setBackground(getResources().getDrawable(R.drawable.bgblackdiamond, getApplicationContext().getTheme()));

        findViewById(R.id.A3).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.B3).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.C3).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.D3).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.E3).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.F3).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.G3).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.H3).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.I3).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));

        findViewById(R.id.A2).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.B2).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.C2).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.D2).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.E2).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.F2).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.G2).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.H2).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.I2).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));

        findViewById(R.id.A1).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.B1).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.C1).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.D1).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.E1).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.F1).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.G1).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.H1).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.I1).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));

        findViewById(R.id.A0).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.B0).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.C0).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.D0).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.E0).setBackground(getResources().getDrawable(R.drawable.bgblackdiamond, getApplicationContext().getTheme()));
        findViewById(R.id.F0).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.G0).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
        findViewById(R.id.H0).setBackground(getResources().getDrawable(R.drawable.bgwhite, getApplicationContext().getTheme()));
        findViewById(R.id.I0).setBackground(getResources().getDrawable(R.drawable.bgblack, getApplicationContext().getTheme()));
    }

    /**
     * Resets board foreground map
     */
    private void ResetForegroundMap() {
        a8.removeAllViews();
        myimageA8.setImageResource(R.drawable.ogre);
        b8.removeAllViews();
        c8.removeAllViews();
        d8.removeAllViews();
        e8.removeAllViews();
        f8.removeAllViews();
        g8.removeAllViews();
        h8.removeAllViews();
        i8.removeAllViews();

        a7.removeAllViews();
        b7.removeAllViews();
        c7.removeAllViews();
        d7.removeAllViews();
        e7.removeAllViews();
        f7.removeAllViews();
        g7.removeAllViews();
        h7.removeAllViews();
        i7.removeAllViews();

        a6.removeAllViews();
        b6.removeAllViews();
        c6.removeAllViews();
        d6.removeAllViews();
        e6.removeAllViews();
        f6.removeAllViews();
        g6.removeAllViews();
        h6.removeAllViews();
        i6.removeAllViews();

        a5.removeAllViews();
        b5.removeAllViews();
        c5.removeAllViews();
        d5.removeAllViews();
        e5.removeAllViews();
        f5.removeAllViews();
        g5.removeAllViews();
        h5.removeAllViews();
        i5.removeAllViews();

        a4.removeAllViews();
        b4.removeAllViews();
        c4.removeAllViews();
        d4.removeAllViews();
        e4.removeAllViews();
        f4.removeAllViews();
        g4.removeAllViews();
        h4.removeAllViews();
        i4.removeAllViews();

        a3.removeAllViews();
        b3.removeAllViews();
        c3.removeAllViews();
        d3.removeAllViews();
        e3.removeAllViews();
        f3.removeAllViews();
        g3.removeAllViews();
        h3.removeAllViews();
        i3.removeAllViews();

        a2.removeAllViews();
        b2.removeAllViews();
        c2.removeAllViews();
        d2.removeAllViews();
        e2.removeAllViews();
        f2.removeAllViews();
        g2.removeAllViews();
        h2.removeAllViews();
        i2.removeAllViews();

        a1.removeAllViews();
        b1.removeAllViews();
        c1.removeAllViews();
        d1.removeAllViews();
        e1.removeAllViews();
        f1.removeAllViews();
        g1.removeAllViews();
        h1.removeAllViews();
        i1.removeAllViews();

        a0.removeAllViews();
        b0.removeAllViews();
        c0.removeAllViews();
        d0.removeAllViews();
        e0.removeAllViews();
        f0.removeAllViews();
        g0.removeAllViews();
        h0.removeAllViews();
        i0.removeAllViews();

        a8.addView(myimageA8);
        b8.addView(myimageB8);
        c8.addView(myimageC8);
        d8.addView(myimageD8);
        e8.addView(myimageE8);
        f8.addView(myimageF8);
        g8.addView(myimageG8);
        h8.addView(myimageH8);
        i8.addView(myimageI8);

        a7.addView(myimageA7);
        b7.addView(myimageB7);
        c7.addView(myimageC7);
        d7.addView(myimageD7);
        e7.addView(myimageE7);
        f7.addView(myimageF7);
        g7.addView(myimageG7);
        h7.addView(myimageH7);
        i7.addView(myimageI7);

        a1.addView(myimageA1);
        b1.addView(myimageB1);
        c1.addView(myimageC1);
        d1.addView(myimageD1);
        e1.addView(myimageE1);
        f1.addView(myimageF1);
        g1.addView(myimageG1);
        h1.addView(myimageH1);
        i1.addView(myimageI1);

        a0.addView(myimageA0);
        b0.addView(myimageB0);
        c0.addView(myimageC0);
        d0.addView(myimageD0);
        e0.addView(myimageE0);
        f0.addView(myimageF0);
        g0.addView(myimageG0);
        h0.addView(myimageH0);
        i0.addView(myimageI0);
    }

    /**
     * Init all image onTouchListener
     */
    private void InitImageOnTouchListeners() {

        for (HashMap.Entry<Integer, ImageView> entry : ImageRessources.entrySet()) {
            int ressourceKey = entry.getKey();
            final ImageView myImage = entry.getValue();
            findViewById(ressourceKey).setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return onTouchListener(view, motionEvent, myImage);
                }
            });
        }
    }


    /**
     * Init all linear layout onDragListener
     */
    private void InitLinearLayoutOnDragListeners() {
        for (HashMap.Entry<Integer, LinearLayout> entry : LinearLayoutRessources.entrySet()) {
            int ressourceKey = entry.getKey();
            LinearLayout linearLayout = entry.getValue();

            findViewById(ressourceKey).setOnDragListener(new OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    return onDragListener(view, dragEvent);
                }
            });
        }
    }

    /**
     * onTouchListener fired, when view with image is touched
     * @param v view which is touched
     * @param motionEvent specific devent
     * @param theImage image in the view, who is touched
     */
    public boolean onTouchListener(View view, MotionEvent motionEvent, ImageView theImage) {
        if (!started) {
            showMessage(getString(R.string.string_notstarted));
            return false;
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            currentImage = theImage;

            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            //view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);

            return true;
        } else {
            return false;
        }
    }


    /**
     * onDragListener fired, when drag and drop event is performed
     * @param v view on which image is dragged or dropped
     * @param event specific drag, drop event
     */
    public boolean onDragListener(View v, DragEvent event) {

        if (!started) {
            showMessage(getString(R.string.string_notstarted));
            return false;
        }

        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget, null);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape, null);

        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackground(enterShape);
                v.setBackgroundDrawable(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackground(normalShape);
                v.setBackgroundDrawable(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);

                System.runFinalization();
                System.gc();
                /*
                int drawableID = R.drawable.a0;
                try {
                    tmp = "a" + String.valueOf(++index);
                    drawableID = getResources().getIdentifier(tmp, "drawable", getPackageName());
                    currentImage.setImageResource(drawableID);
                    System.runFinalization();
                    System.gc();
                } catch (Exception ex) {
                    errHandler(ex);
                }
                index%=128;
                */

                LinearLayout container = (LinearLayout) v;
                container.addView(view, 0);
                // container.addView(view);
                view.setVisibility(View.VISIBLE);

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackground(normalShape);
                // v.setBackgroundDrawable(normalShape);
            default:
                break;
        }
        ResetBackgroundMap();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        myMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_start) {
            startGame();
            return true;
        }
        if (id == R.id.action_stop) {
            stopGame();
            return true;
        }
        if (id == R.id.action_help) {
            showHelp();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * startGame() starts a new game
     */
    public void startGame() {
        if (myMenu != null) {
            myMenu.findItem(R.id.action_start).setEnabled(false);
            myMenu.findItem(R.id.action_stop).setEnabled(true);
        }

        ResetBackgroundMap();
        ResetForegroundMap();

        if (startedTimes > 0) {
            InitLinearLayoutArchonFields();
            InitLinearLayoutRessourcesMap();
            InitImageArchonFields();
            InitImageRessourcesMap();

            InitLinearLayoutOnDragListeners();
            InitImageOnTouchListeners();
        }

        started = true;
        startedTimes++;
        showMessage(getString(R.string.string_started));
    }

    /**
     * startGame() starts a new game
     */
    public void stopGame() {
        started = false;
        if (myMenu != null) {
            myMenu.findItem(R.id.action_start).setEnabled(true);
            myMenu.findItem(R.id.action_stop).setEnabled(false);
        }
        showMessage(getString(R.string.string_stopped));
    }

    /**
     * showHelp() prints out help text
     */
    public void showHelp() {
        // try {
        //     Thread.currentThread().sleep(10);
        // } catch (Exception exInt) {
        //     errHandler(exInt);
        // }
        // tDbg.setText(R.string.help_text);

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    /**
     * showMessage shows a new Toast dynamic message
     * @param text to display
     */
    private void showMessage(CharSequence text) {

        if (text != null && text != "") {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    /**
     * showError simple dummy error handler
     * @param myErr java.lang.Throwable
     */
    void showError(java.lang.Throwable myErr) {

        if (myErr != null) {
            CharSequence text = "CRITICAL ERROR #" + String.valueOf((++errNum)) + " " + myErr.getMessage() + "\nMessage: " + myErr.getLocalizedMessage() + "\n";

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            myErr.printStackTrace();
        }
    }

}
