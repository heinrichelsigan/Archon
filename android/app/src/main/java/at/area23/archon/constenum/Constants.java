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

package at.area23.archon.constenum;

public class Constants {
	public final static String COLUMNSTRING = "ABCDEFGHI";
    public final static COLUMN[] COLUMNS = { COLUMN.A, COLUMN.B, COLUMN.C,
            COLUMN.D, COLUMN.E, COLUMN.F, COLUMN.G, COLUMN.H, COLUMN.I };
    public final static int[] ROWS  = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };    
    public final static int[] Y  = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    public static char X[] = COLUMNSTRING.toCharArray();
    // int[][] BOARD = new int[9][9];
}
