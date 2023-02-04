/*
 *
 * @author           Heinrich Elsigan
 * @version          V 1.0.1
 * @since            API 27 Oreo 8.1
 *
 */
/*
	Copyleft (C) 2019 - 2023 Heinrich Elsigan (heinrich.elsigan@area23.at)

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

import android.content.Context;
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
import android.widget.Toast;


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

        // get player figures
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("Figure1") != null) {
                String drawable1 = bundle.getString("Figure1").substring(0,  bundle.getString("Figure1").length() - 2);
                int drawId1 = getApplicationContext().getResources().getIdentifier(
                        drawable1, "drawable", getApplicationContext().getPackageName());
                playerA.setImageResource(drawId1);

                showMessage("Player1 is " + bundle.getString("Figure1"));
            }
            if (bundle.getString("Figure2") != null) {
                String drawable2 = bundle.getString("Figure2").substring(0,  bundle.getString("Figure2").length() - 2);
                int drawId2 = getApplicationContext().getResources().getIdentifier(
                        drawable2, "drawable", getApplicationContext().getPackageName());
                playerB.setImageResource(drawId2);

                showMessage("Player2 is " + bundle.getString("Figure2"));
            }
        }

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
        if (id == R.id.action_exit) {
            finishAffinity();
            System.exit(0);
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
        backButton.setOnClickListener(arg0 -> backButton_Clicked(arg0));
    }


    /**
     * backButton_Clicked finish about activity
     * @param arg0 current View
     */
    public void backButton_Clicked(View arg0) {
        // finish activity
        finish();
    }




    /**
     * showMessage shows a new Toast dynamic message
     * @param text to display
     */
    private void showMessage(CharSequence text) {
        showMessage(text, Toast.LENGTH_SHORT);
    }

    /**
     * showMessage shows a new Toast dynamic message
     * @param text to display
     * @param duration time to display
     */
    private void showMessage(CharSequence text, int duration) {
        if (text != null && text != "") {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}