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

/**
 * SCOLOR represents the enumerator for stone colors
 */
public enum FIGURE {
    KNIGHT(0),
    UNICORN(1),
    PEGASUS(2),
    DJINN(3),
    FIREBIRD(4),
	MAGE(5),
	
    GOBLIN(8),
    OGRE(9),
	BASILISK(10),
    WITCH(11),
    VAMPIRE(12),
    DRAGON(13),
    SORCERESS(14),
	
    NONE((int)Byte.MAX_VALUE);

    /**
     * NOTE: Enum constructor must have private or package scope. You can not use the public access modifier.
     */
    FIGURE(int value) {
        this.value = value;
    }

    private final int value;

    /**
     * getValue
     * @return (@link int) value
     */
    public int getValue() { return value; }



    /**
     * getChar
     * @return character letter {@link char}
     */
    public char getChar() {
        int value = this.getValue();
        switch (value) {
			case 0: return 'k';
			case 1: return 'u';
			case 2: return 'p';
			case 3: return 'j';
			case 4: return 'f';
			case 5: return 'm';
			
			case 8: return 'g';
			case 9: return 'o';
			case 10: return 'b';
			case 11: return 'w';
			case 12: return 'v';
			case 13: return 'd';
			case 14: return 's';
			
			default:  break;
		}
        return '\0';
    }

    /**
     * getName
     * @return upper letter {@link String} or NONE
     */
    public String getName() {
        int value = this.getValue();
        switch (value) {
			case 0: return "KNIGHT";
			case 1: return "UNICORN";
			case 2: return "PEGASUS";
			case 3: return "DJINN";
			case 4: return "FIREBIRD";
			case 5: return "MAGE";
			
			case 8: return "GOBLIN";
			case 9: return "OGRE";
			case 10: return "BASILISK";
			case 11: return "WITCH";
			case 12: return "VAMPIRE";
			case 13: return "DRAGON";
			case 14: return "SORCERESS";
			
			default:  break;
		}
        return "NONE";
		    
    }


    /**
     * getEnum
     * @param ch column character
     * @return the enum {@link FIGURE}
     */
    public static FIGURE getEnum(char ch) {
        char uch = String.valueOf(ch).toLowerCase().charAt(0);
        switch (uch) {
            case 'k': return FIGURE.KNIGHT;
			case 'u': return FIGURE.UNICORN;
			case 'p': return FIGURE.PEGASUS;
			case 'j': return FIGURE.DJINN;
			case 'f': return FIGURE.FIREBIRD;
			case 'm': return FIGURE.MAGE;
			
			case 'g': return FIGURE.GOBLIN;
			case 'o': return FIGURE.OGRE;
			case 'b': return FIGURE.BASILISK;
			case 'w': return FIGURE.WITCH;
			case 'v': return FIGURE.VAMPIRE;
			case 'd': return FIGURE.DRAGON;
			case 's': return FIGURE.SORCERESS;			
			
            default:  break;
        }
        return FIGURE.NONE;

    }


    /**
     * toString
     * @return name {@link String} of FIGURE
     */
    public String toString() {
        return (this.toString());
    }


}

