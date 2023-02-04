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

import java.lang.String;

/**
 * COLUMN represents the enumerator for columns of the board
 */
public enum COLUMN {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9),

    NONE((int)Byte.MAX_VALUE);

    /**
     * NOTE: Enum constructor must have private or package scope. You can not use the public access modifier.
     */
    COLUMN(int value) {
        this.value = value;
    }

    private final int value;

    /**
     * getValue
     * @return (@link int) value
     */
    public int getValue() { return value; }

    /**
     * getUChar
     * @return upper letter {@link char}
     */
    public char getUChar() {
        int value = this.getValue();
        char a = 'A';
        if (value >= 0 && value <= (int)Byte.MAX_VALUE) {
            return (char) (((int) a + value));
        }
        return '\0';
    }


    /**
     * getUChar
     * @return upper letter {@link char}
     */
    public char getlChar() {
        int value = this.getValue();
        char a = 'a';
        if (value >= 0 && value <= (int)Byte.MAX_VALUE) {
            return (char) (((int) a + value));
        }
        return '\0';
    }



    /**
     * getUChar
     * @return upper letter {@link char}
     */
    public char getChar() {
        int value = this.getValue();
        char a = 'A';
        if (value >= 0 && value < 256) {
            return (char) (((int) a + value));
        }
        return '\0';

    }

    /**
     * getName
     * @return upper letter {@link String} or NONE
     */
    public String getName() {
        return (this.getValue() < 0 || this.getValue() >= (int)Byte.MAX_VALUE) ? "NONE": String.valueOf(this.getUChar());
    }


    /**
     * getEnum
     * @param ch column character
     * @return the enum {@link COLUMN}
     */
    public static COLUMN getEnum(char ch) {
        char uch = String.valueOf(ch).toUpperCase().charAt(0);
        switch (uch) {
            case 'A': return COLUMN.A;
            case 'B': return COLUMN.B;
            case 'C': return COLUMN.C;
            case 'D': return COLUMN.D;
            case 'E': return COLUMN.E;
            case 'F': return COLUMN.F;
            case 'G': return COLUMN.G;
            case 'H': return COLUMN.H;
            case 'I': return COLUMN.I;
            case 'J': return COLUMN.J;

            default:  break;
        }
        return COLUMN.NONE;

    }

}

