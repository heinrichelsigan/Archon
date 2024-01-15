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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;

import at.area23.archon.constenum.*;


/**
 * Board entity
 *
 * Board represents the data abstraction of archon game board
 * see BoardField 
 *
 */
public class Board {

    public Context context;
    public int dimension = 9;
    public int fieldIdCnt = 0;
    public int maxFields = dimension * dimension;
    public BoardField[][] fields;
    public BoardField lastField = null;

    /**
     * constructor
     *  initialize all fields of the board
     *
     * @param aContext (required NonNull) application context
     */
    public Board(Context aContext) {
        this(aContext, 9);
    }

    /**
     * constructor
     *  initialize all fields of the board
     *
     * @param aContext (required NonNull) application context
     * @param level (required NonNull) application context
     */
    public Board(Context aContext, int size) {
        this.context = aContext;
        this.dimension = size;

        maxFields = dimension * dimension;        
        fields =  new BoardField[dimension][dimension];

        lastField = null;

        int aRow = 0;
		for (int y = 0; y < dimension; y++) {
			for (int x = 0; x < dimension; x++) {
			
				COLUMN aCol = Util.allCols[x];
				fields[x][y] = new BoardField(aContext, dimension, aCol, y);
			}
        }
    }



    /**
     * getFieldByToView
     *  static method, that returns a field, that was identified by view short bane
     * @param toView view String identifier
     * @return BoardField, that represents column and row
     */
    @Deprecated
    public BoardField getFieldIndexer(Context aContext, String toView) {

        if (toView == null || toView.length() < 2)
            return null;

        int row4View  =  (int) Integer.parseInt(String.valueOf(toView.charAt(1)));
        COLUMN column4View = COLUMN.getEnum(toView.charAt(0));

        return new BoardField(aContext, dimension, column4View, row4View);
    }
}
