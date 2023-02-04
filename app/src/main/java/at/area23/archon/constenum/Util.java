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

import at.area23.archon.constenum.COLUMN;

/**
 * Util provides only static fields
 */
public class Util {

	public final static boolean ENQ_MSG = false; // false;
	
    public static final COLUMN[] allCols = {
            COLUMN.A, COLUMN.B, COLUMN.C, COLUMN.D, COLUMN.E,
            COLUMN.F, COLUMN.G, COLUMN.H, COLUMN.I, COLUMN.NONE };

    public static final int[] allRows = { 0, 1, 2, 3, 4, 5, 6, 7, 8, Integer.MAX_VALUE };


    public static final String[] fromViews = {
            "GA0", "GB1", "GC2", "GD3", "GE4", "GF5", "GG6", "GH7", "GI8", "GJ9",
            "ga0", "gb1", "gc2", "gd3", "ge4", "gf5", "gg6", "gh7", "gi8", "gj9",
            "gA0", "gB1", "gC2", "gD3", "gE4", "gF5", "gG6", "gH7", "gI8", "gJ9",
            "Ga0", "Gb1", "Gc2", "Gd3", "Ge4", "Gf5", "Gg6", "Gh7", "Gi8", "Gj9" };

    public static final String[] toViews = {
            "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8",
            "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8",
            "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8",
            "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8",
            "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8",
            "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8",
            "g0", "g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8",
            "h0", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8",
            "i0", "i1", "i2", "i3", "i4", "i5", "i6", "i7", "i8",
            
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
            "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", 
            "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", 
            "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", 
            "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", 
            "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", 
            "G0", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8",
            "H0", "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", 
            "I0", "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8"
		};


    public static final String[] figures = {
            "gemA0", "gemA1", "gemA2", "gemA3", "gemA4", "gemA5", "gemA6", "gemA7", "gemA8", "gemA9",
            "gemB0", "gemB1", "gemB2", "gemB3", "gemB4", "gemB5", "gemB6", "gemB7", "gemB8", "gemB9",
            "gemC0", "gemC1", "gemC2", "gemC3", "gemC4", "gemC5", "gemC6", "gemC7", "gemC8", "gemC9",
            "gemD0", "gemD1", "gemD2", "gemD3", "gemD4", "gemD5", "gemD6", "gemD7", "gemD8", "gemD9",
            "gemE0", "gemE1", "gemE2", "gemE3", "gemE4", "gemE5", "gemE6", "gemE7", "gemE8", "gemE9",
            "gemF0", "gemF1", "gemF2", "gemF3", "gemF4", "gemF5", "gemF6", "gemF7", "gemF8", "gemF9",
            "gemG0", "gemG1", "gemG2", "gemG3", "gemG4", "gemG5", "gemG6", "gemG7", "gemG8", "gemG9",
            "gemH0", "gemH1", "gemH2", "gemH3", "gemH4", "gemH5", "gemH6", "gemH7", "gemH8", "gemH9",
            "gemI0", "gemI1", "gemI2", "gemI3", "gemI4", "gemI5", "gemI6", "gemI7", "gemI8", "gemI9",
            "gemJ0", "gemJ1", "gemJ2", "gemJ3", "gemJ4", "gemJ5", "gemJ6", "gemJ7", "gemJ8", "gemJ9",
            "gema0", "gema1", "gema2", "gema3", "gema4", "gema5", "gema6", "gema7", "gema8", "gema9",
            "gemb0", "gemb1", "gemb2", "gemb3", "gemb4", "gemb5", "gemb6", "gemb7", "gemb8", "gemB9",
            "gemc0", "gemc1", "gemc2", "gemc3", "gemc4", "gemc5", "gemc6", "gemc7", "gemc8", "gemc9",
            "gemd0", "gemd1", "gemd2", "gemd3", "gemd4", "gemd5", "gemd6", "gemd7", "gemd8", "gemd9",
            "geme0", "geme1", "geme2", "geme3", "geme4", "geme5", "geme6", "geme7", "geme8", "geme9",
            "gemf0", "gemf1", "gemf2", "gemf3", "gemf4", "gemf5", "gemf6", "gemf7", "gemf8", "gemf9",
            "gemg0", "gemg1", "gemg2", "gemg3", "gemg4", "gemg5", "gemg6", "gemg7", "gemg8", "gemg9",
            "gemh0", "gemh1", "gemh2", "gemh3", "gemh4", "gemh5", "gemh6", "gemh7", "gemh8", "gemh9",
            "gemi0", "gemi1", "gemi2", "gemi3", "gemi4", "gemi5", "gemi6", "gemi7", "gemi8", "gemi9",
            "gemj0", "gemj1", "gemj2", "gemj3", "gemj4", "gemj5", "gemj6", "gemj7", "gemj8", "gemj9" };



}

