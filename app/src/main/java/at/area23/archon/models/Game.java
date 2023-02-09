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

package at.area23.archon.models;


import android.content.Context;
import android.widget.LinearLayout;
import android.view.View;

import at.area23.archon.constenum.*;

import androidx.annotation.NonNull;

/**
 * Game entity
 *
 * Game represents a potential field in the board
 *
 */
public class Game {
    public Context context;
    public int dimension = 9;
    public int size = 9;
    public int fieldIdCnt = 0;
    public int battleCount = 0;
    public int playerFiguresCount = 22;
    public int computerFiguresCount = 22;
    public boolean perfectLevel = true;
	
	boolean _dropBoardEnabled = false;
	
	public DropBoard dropBoard;
	public Board board; 
   
    /**
     * constructor
     *
     * @param aContext (required NonNull) application context     	 
     */
    public Game(@NonNull Context aContext) {
        this(aContext, 9);        
    }


    /**
     * constructor
     *
     * @param aContext (required NonNull) application context
     * @param size - int (dimension = level)	 	  
     */
    public Game(@NonNull Context aContext, int size) {
        this.context = aContext;
        dimension = size;
        size = size;
		board = new Board(aContext, size);
    }
	
	public boolean isFinished() {
		return false;
	}

    
    public int getFieldIdCnt() {
        return fieldIdCnt;
    }

    public void setFieldIdCnt(int _fieldIdCnt) {
        this.fieldIdCnt = _fieldIdCnt;        
    }

    /**
     * getName
     * @return String with full field name including color
     */
    public String getName() {
        try {
            return ("Game_size_" + dimension);
        } catch (Exception ex) {
        }
         return null;
    }



}
