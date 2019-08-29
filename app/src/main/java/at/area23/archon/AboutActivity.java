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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

/**
 * AboutActivity class implements help text view.
 *
 * @see <a href="https://github.com/heinrichelsigan/ar/wiki</a>
 */
public class AboutActivity extends AppCompatActivity {

    Button backButton, learnMoreButton;
    TextView helpTextView, builtWithTextView;
    Menu myMenu;

    /**
     * Override onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        backButton = (Button) findViewById(R.id.backButton);
        learnMoreButton =  (Button) findViewById(R.id.learnMoreButton);
        helpTextView = (TextView) findViewById(R.id.helpTextView);
        builtWithTextView = (TextView) findViewById(R.id.builtWithTextView);

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

        learnMoreButton = (Button) findViewById(R.id.learnMoreButton);
        learnMoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                learnMoreButton_Clicked(arg0);
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


    /**
     * learnMoreButton_Clicked_Clicked
     * @param arg0
     */
    public void learnMoreButton_Clicked(View arg0) {

        helpTextView.setText(R.string.help_text);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String github = getString(R.string.github_uri);
        intent.setData(android.net.Uri.parse(github));
        startActivity(intent);
    }

}