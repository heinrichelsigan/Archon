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
 * DropBoard entity
 *
 * DropBoard represents the data abstraction of drop board
 *
 */
public class DropBoard extends Board {

    /**
     * field always indexes field[column][row]
     */
    public BoardField[][] dropStones;
	public int[] dropCount;
    public Context context;
    public int dimension = 10;

    /**
     * constructor
     *  initialize all fields of the board
     *
     * @param aContext (required NonNull) application context
     */
    public DropBoard(Context aContext) {
        this(aContext, 10);
    }

    /**
     * constructor
     *  initialize all fields of the board
     *
     * @param aContext (required NonNull) application context
     * @param size (required NonNull) application context
     */
    public DropBoard(Context aContext, int size) {
        super(aContext, size);

        dropStones = new BoardField[dimension][dimension];
		dropCount = new int[dimension];
		for (int ix = 0; ix < dimension; ix++) {
			dropCount[ix] = 0;
		}
        
    }

    /**
     * getField
     *
     * @param idxColumn the column indexer of our game board, e.g. A, B, C, D, E, F, G, ...
     * @return board field reference, that is in the board at col,row index
     */
    public BoardField getField(COLUMN idxColumn)  {
		int sCol = idxColumn.getValue();
        if (idxColumn == COLUMN.NONE || idxColumn.getUChar() == (char)'\0' || (int)sCol >= dimension) {
            return null;
        }

        int iCol = idxColumn.getValue();
		int iStack = dropCount[sCol];
		dropCount[sCol]--;
        return  dropStones[iCol][iStack];
    }

    /**
     * setStone
     * @param sColumn (link @COLUMN) the column indexer of our game board, e.g. A, B, C, D, E, F, G, ...
     * @param setFigure field to set
     * @return refField BoardField reference
     *                 null, if basic set operation failed
     *                 a field with same color inside cross row or cross column
     *                 the field, after the new gemColor has been set there
     */
    public BoardField setField(COLUMN sColumn, FIGURE setFigure)  {
        BoardField retField = null;
		int sCol = sColumn.getValue();
		if (sColumn == COLUMN.NONE || setFigure == null  || (int)sCol >= dimension) {
			return null;
		}

		dropStones[sCol][dropCount[sCol]].figure = setFigure;
		dropCount[sCol]++;
		retField = dropStones[sCol][dropCount[sCol]];

        return retField;
    }


}
