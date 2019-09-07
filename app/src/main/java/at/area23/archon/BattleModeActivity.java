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

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

/**
 * BattleModeActivity class implements battle ground.
 *
 * @see <a href="https://github.com/heinrichelsigan/Archon/wiki/Archon-wiki-home</a>
 */
public class BattleModeActivity extends AppCompatActivity {

    Button backButton;
    // TextView helpTextView, builtWithTextView;
    Menu myMenu;
	LinearLayout battleGround;
	ImageView playerA, playerB;

    /**
     * Override onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlemode);

        backButton = (Button) findViewById(R.id.backButton);
        battleGround =  (LinearLayout) findViewById(R.id.battleGround);
        playerA = (ImageView) findViewById(R.id.playerA);
        playerB = (ImageView) findViewById(R.id.playerB);

        addListenerOnClickables();

    }

    /**
     * onCreateOptionsMenu
     * @param menu
     * @return true|false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        myMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_start) {
            finish();
            return true;
        }
        if (id == R.id.action_stop) {
            finish();
            return true;
        }
        if (id == R.id.action_help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * add listeners on all clickables
     */
    public void addListenerOnClickables() {

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                backButton_Clicked(arg0);
            }
        });

    }


    /**
     * backButton_Clicked finish about activity
     * @param arg0 current View
     */
    public void backButton_Clicked(View arg0) {
        // finish activity
        finish();
    }



}