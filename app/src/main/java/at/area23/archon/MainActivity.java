/*
 *
 * @author           Heinrich Elsigan
 * @version          V 1.0.1
 * @since            API 27 Oreo 8.1
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
import java.util.ArrayList;
import at.area23.archon.BaseMainActivity;

public class MainActivity extends BaseMainActivity  {

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

    ImageView ogreA8, basiliskB8, whichC8, devilD8, sorcererE8, dragonF8, whichG8, basiliskH8, ogreI8;
    ImageView goblinA7, goblinB7, goblinC7, goblinD7, goblinE7, goblinF7, goblinG7, goblinH7, goblinI7;
    ImageView knightA1, knightB1, knightC1, knightD1, knightE1, knightF1, knightG1, knightH1, knightI1;
    ImageView golemA0, unicornB0, pegasusC0, djinnD0, mageE0, phoenixF0, pegasusG0, unicornH0, golemI0;
    ImageView currentImage;
    Drawable savedBackground;

    HashMap<Integer,ImageView> ImageRessources = new HashMap<Integer,ImageView>();
    HashMap<Integer, LinearLayout> LinearLayoutRessources = new HashMap<Integer, LinearLayout>();
    HashMap<Integer, Drawable> dragNDropMap = new HashMap<>();

    int index = 4;

    volatile boolean started = false;
    volatile int startedTimes = 0;
    volatile boolean knightSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = getWindow().getDecorView().getRootView();
        RessourceViewHashMap(rootView, viewMap);

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
        int[] rids = getResources().getIntArray(R.array.rid);
        ogreA8 = (ImageView) findViewById(R.id.ogreA8);
        basiliskB8 = (ImageView) findViewById(R.id.basiliskB8);
        whichC8 = (ImageView) findViewById(R.id.whichC8);
        devilD8 = (ImageView) findViewById(R.id.devilD8);
        sorcererE8 = (ImageView) findViewById(R.id.sorcererE8);
        dragonF8 = (ImageView) findViewById(R.id.dragonF8);
        whichG8 = (ImageView) findViewById(R.id.whichG8);
        basiliskH8 = (ImageView) findViewById(R.id.basiliskH8);
        ogreI8 = (ImageView) findViewById(R.id.ogreI8);

        goblinA7 = (ImageView) findViewById(R.id.goblinA7);
        goblinB7 = (ImageView) findViewById(R.id.goblinB7);
        goblinC7 = (ImageView) findViewById(R.id.goblinC7);
        goblinD7 = (ImageView) findViewById(R.id.goblinD7);
        goblinE7 = (ImageView) findViewById(R.id.goblinE7);
        goblinF7 = (ImageView) findViewById(R.id.goblinF7);
        goblinG7 = (ImageView) findViewById(R.id.goblinG7);
        goblinH7 = (ImageView) findViewById(R.id.goblinH7);
        goblinI7 = (ImageView) findViewById(R.id.goblinI7);

        knightA1 = (ImageView) findViewById(R.id.knightA1);
        knightB1 = (ImageView) findViewById(R.id.knightB1);
        knightC1 = (ImageView) findViewById(R.id.knightC1);
        knightD1 = (ImageView) findViewById(R.id.knightD1);
        knightE1 = (ImageView) findViewById(R.id.knightE1);
        knightF1 = (ImageView) findViewById(R.id.knightF1);
        knightG1 = (ImageView) findViewById(R.id.knightG1);
        knightH1 = (ImageView) findViewById(R.id.knightH1);
        knightI1 = (ImageView) findViewById(R.id.knightI1);

        golemA0 = (ImageView) findViewById(R.id.golemA0);
        unicornB0 = (ImageView) findViewById(R.id.unicornB0);
        pegasusC0 = (ImageView) findViewById(R.id.pegasusC0);
        djinnD0 = (ImageView) findViewById(R.id.djinnD0);
        mageE0 = (ImageView) findViewById(R.id.mageE0);
        phoenixF0 = (ImageView) findViewById(R.id.phoenixF0);
        pegasusG0 = (ImageView) findViewById(R.id.pegasusG0);
        unicornH0 = (ImageView) findViewById(R.id.unicornH0);
        golemI0 = (ImageView) findViewById(R.id.golemI0);
    }



    /**
     * Add local variables images corresponding to R.id ressources to ImageRessources HashMap
     */
    private void InitImageRessourcesMap() {
        // Clear Ressource Map first to avoid duplicated entries in HashMap, when method is called more than once


        ImageRessources.clear();

        // Init image ressource map
        ImageRessources.put(R.id.ogreA8, ogreA8);
        ImageRessources.put(R.id.basiliskB8, basiliskB8);
        ImageRessources.put(R.id.whichC8, whichC8);
        ImageRessources.put(R.id.devilD8, devilD8);
        ImageRessources.put(R.id.sorcererE8, sorcererE8);
        ImageRessources.put(R.id.dragonF8, dragonF8);
        ImageRessources.put(R.id.whichG8, whichG8);
        ImageRessources.put(R.id.basiliskH8, basiliskH8);
        ImageRessources.put(R.id.ogreI8, ogreI8);

        ImageRessources.put(R.id.goblinA7, goblinA7);
        ImageRessources.put(R.id.goblinB7, goblinB7);
        ImageRessources.put(R.id.goblinC7, goblinC7);
        ImageRessources.put(R.id.goblinD7, goblinD7);
        ImageRessources.put(R.id.goblinE7, goblinE7);
        ImageRessources.put(R.id.goblinF7, goblinF7);
        ImageRessources.put(R.id.goblinG7, goblinG7);
        ImageRessources.put(R.id.goblinH7, goblinH7);
        ImageRessources.put(R.id.goblinI7, goblinI7);

        ImageRessources.put(R.id.knightA1, knightA1);
        ImageRessources.put(R.id.knightB1, knightB1);
        ImageRessources.put(R.id.knightC1, knightC1);
        ImageRessources.put(R.id.knightD1, knightD1);
        ImageRessources.put(R.id.knightE1, knightE1);
        ImageRessources.put(R.id.knightF1, knightF1);
        ImageRessources.put(R.id.knightG1, knightG1);
        ImageRessources.put(R.id.knightH1, knightH1);
        ImageRessources.put(R.id.knightI1, knightI1);

        ImageRessources.put(R.id.golemA0, golemA0);
        ImageRessources.put(R.id.unicornB0, unicornB0);
        ImageRessources.put(R.id.pegasusC0, pegasusC0);
        ImageRessources.put(R.id.djinnD0, djinnD0);
        ImageRessources.put(R.id.mageE0, mageE0);
        ImageRessources.put(R.id.phoenixF0, phoenixF0);
        ImageRessources.put(R.id.pegasusG0, pegasusG0);
        ImageRessources.put(R.id.unicornH0, unicornH0);
        ImageRessources.put(R.id.golemI0, golemI0);
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
        ogreA8.setImageResource(R.drawable.ogre);
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

        a8.addView(ogreA8);
        b8.addView(basiliskB8);
        c8.addView(whichC8);
        d8.addView(devilD8);
        e8.addView(sorcererE8);
        f8.addView(dragonF8);
        g8.addView(whichG8);
        h8.addView(basiliskH8);
        i8.addView(ogreI8);

        a7.addView(goblinA7);
        b7.addView(goblinB7);
        c7.addView(goblinC7);
        d7.addView(goblinD7);
        e7.addView(goblinE7);
        f7.addView(goblinF7);
        g7.addView(goblinG7);
        h7.addView(goblinH7);
        i7.addView(goblinI7);

        a1.addView(knightA1);
        b1.addView(knightB1);
        c1.addView(knightC1);
        d1.addView(knightD1);
        e1.addView(knightE1);
        f1.addView(knightF1);
        g1.addView(knightG1);
        h1.addView(knightH1);
        i1.addView(knightI1);

        a0.addView(golemA0);
        b0.addView(unicornB0);
        c0.addView(pegasusC0);
        d0.addView(djinnD0);
        e0.addView(mageE0);
        f0.addView(phoenixF0);
        g0.addView(pegasusG0);
        h0.addView(unicornH0);
        i0.addView(golemI0);
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
     * @param view which is touched
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
            if (theImage == null) {
                return false;
            }
            if (theImage == knightA1 || theImage == knightB1 || theImage == knightC1 || theImage == knightD1 || theImage == knightE1 ||
                theImage == knightF1 || theImage == knightG1 || theImage == knightH1 || theImage == knightI1) {
                knightSelected = true;
            }

            dragNDropMap = new HashMap<>();
            dragNDropMap.put(view.getId(), view.getBackground());

            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, shadowBuilder, view, View.DRAG_FLAG_GLOBAL);
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
                savedBackground = v.getBackground();
                if (!dragNDropMap.containsKey(v.getId())) {
                    dragNDropMap.put(v.getId(), savedBackground);
                }
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                savedBackground = v.getBackground();
                if (!dragNDropMap.containsKey(v.getId())) {
                    dragNDropMap.put(v.getId(), savedBackground);
                }
                v.setBackgroundDrawable(enterShape);
                v.setBackground(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                if (dragNDropMap.containsKey(v.getId())) {
                    Drawable dr = dragNDropMap.get(v.getId());
                    if (dr != null)
                        v.setBackground(dr);
                }
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);

                LinearLayout container = (LinearLayout) v;

                int viewId = view.getId();
                int containerId = container.getId();
                int childViewId = -1;
                boolean enterBattleMode = false;
                String figure1 = "", figure2 = "";

                String viewDbgInfo = "Added view id: " + String.valueOf(view.getId()) + " to container id: " + String.valueOf(container.getId()) + " \r\n";

                String[] figures = {"ogreA8", "basiliskB8", "whichC8", "devilD8", "sorcererE8", "dragonF8", "whichG8", "basiliskH8", "ogreI8",
                        "goblinA7", "goblinB7", "goblinC7", "goblinD7", "goblinE7", "goblinF7", "goblinG7", "goblinH7", "goblinI7",
                        "knightA1", "knightB1", "knightC1", "knightD1", "knightE1", "knightF1", "knightG1", "knightH1", "knightI1",
                        "golemA0", "unicornB0", "pegasusC0", "djinnD0", "mageE0", "phoenixF0", "pegasusG0", "unicornH0", "golemI0"};

                for (String droppedFigure: figures) {
                    //Do your stuff here
                    int myId = getApplicationContext().getResources().getIdentifier(droppedFigure, "id", getApplicationContext().getPackageName());
                    if (viewId == myId) {
                        String figureString = "Dropped " + droppedFigure + " \r\n";
                        showMessage(figureString);

                        figure1 = droppedFigure;
                        viewDbgInfo += figureString;
                    }
                }


                int childViewCount = container.getChildCount();
                for (int vc = 0; vc < childViewCount; vc++) {

                    childViewId = container.getChildAt(vc).getId();
                    viewDbgInfo += "child view " + String.valueOf(vc) + " id: " + childViewId + " \r\n";

                    for (String containingFigure: figures) {
                        //Do your stuff here
                        int myId = getApplicationContext().getResources().getIdentifier(
                                containingFigure, "id", getApplicationContext().getPackageName());
                        if (childViewId == myId) {
                            String figureString = "Containing " + containingFigure + " \r\n";
                            showMessage(figureString);
                            viewDbgInfo += figureString;
                            figure2 = containingFigure;
                            enterBattleMode = true;
                        } else {
                            playDropStone();
                        }
                    }
                }

                container.addView(view, 0);
                if (savedBackground != null)
                    container.setBackground(savedBackground);

                // container.addView(view);
                view.setVisibility(View.VISIBLE);

                showMessage(viewDbgInfo, false);

                if (enterBattleMode) {
                    playRawCompleted();
                    // TODO: Define Figure[12] as constant
                    Intent intent = new Intent(this, BattleModeActivity.class);
                    intent.putExtra("Figure1", figure1);
                    intent.putExtra("Figure2", figure2);
                    startActivity(intent);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if (dragNDropMap.containsKey(v.getId())) {
                    Drawable dr = dragNDropMap.get(v.getId());
                    if (dr != null)
                        v.setBackground(dr);
                }
                break;
            default:
                break;
        }
        // ResetBackgroundMap();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // super.onCreateOptionsMenu(menu);
        myMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * actionMenuItem - you must implement that method for your purpose
     * @param itemId - ressource Id of menu item
     * @param item - MenuItem item entity
     * @param parentMenu - parent Menu instance, where the menu item belongs to
     * @return true --> Event Consumed here, now It should not be forwarded for other event
     * 			false --> Forward for other event to get consumed
     */
    @Override
    public boolean actionMenuItem(int itemId, MenuItem item, Menu parentMenu) {
        if (itemId >= 0 && item.getItemId() == itemId) {
            if (itemId == R.id.action_start) {
                startGame();
            }
            if (itemId == R.id.action_stop) {
                stopGame();
            }
            if (itemId == R.id.action_help) {
                showHelp();
            } else {
                return false;
            }
            return true;
        }
        // we fall through by default
        return super.actionMenuItem(itemId, item, parentMenu);
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

            rootView = getWindow().getDecorView().getRootView();
            RessourceViewHashMap(rootView, viewMap);

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

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    /*
    protected void startIdent() {

        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("Figure1", figure1);
        intent.putExtra("Figure2", figure2);
        startActivity(intent);
     }
     /*
     */

}
