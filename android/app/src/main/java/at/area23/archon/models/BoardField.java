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
 * BoardField entity
 *
 * BoardField represents a potential field in the board
 *
 */
public class BoardField {
    public Context context;
    public int dimension = 9;
    public int row = -1;
    public COLUMN column = COLUMN.NONE;
	public FIGURE figure = FIGURE.NONE;
    public String fieldName = null;

    /**
     * constructor
     *
     * @param aContext (required NonNull) application context
     */
    public BoardField(@NonNull Context aContext) {
        this.context = aContext;
    }

    /**
     * constructor
     *
     * @param aContext (required NonNull) application context
     * @param aColumn board column BOARDCOL enum
     * @param aRow board row
     */
    public BoardField(@NonNull Context aContext, int size, COLUMN aColumn, int aRow) {
        this.context = aContext;
        this.dimension = size;
        this.column = aColumn;
        this.row = aRow;

        try {
            fieldName = this.getName();
        } catch (Exception ex) {
        }
    }

    /**
     * constructor
     *
     * @param aContext (required NonNull) application context
     * @param aColumn board column BOARDCOL enum
     * @param aRow board row
     * @param aFigure field figure 
     */
    public BoardField(@NonNull Context aContext, int size, COLUMN aColumn, int aRow, FIGURE aFigure) {
        this.context = aContext;
        this.dimension = size;
        this.column = aColumn;
        this.row = aRow;
		this.figure = aFigure;

        try {
            fieldName = this.getName();
        } catch (Exception ex) {
        }
    }

    /**
     * constructor with single BoardField
     *
     * @param aField BoardField
     */
    public BoardField(BoardField aField) {
        if (aField == null)
            return;
        this.context = aField.context;
        this.dimension = aField.dimension;
        this.row = aField.row;
        this.column = aField.column;
		this.figure = aField.figure;
    }


    /**
     * IsValid
     * @return true, if this is a valid field inside the board, otherwise false
     */
    public boolean isValid() {
        return  (column != COLUMN.NONE && row >= 0 && row < dimension);
    }

    /**
     * getName
     * @return name of field
     */
    public String getName() {
        if (!this.isValid()) {
            return null;
        }
        return (String.valueOf(column.getUChar()) + String.valueOf(row));
    }

}
