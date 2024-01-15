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
 * Enum SPELL
 */
public enum SPELL {
	DESINTEGRATE(0),
    MAGIC_MISSILE(1),
	MUTE_MAGE(2),
	PETRIFY(3),
	RESURRECT(4),
    SHIFT_TIME(5),
	STUN_SPELL(6),
	SUMMON_ELEMENTAL(7),
	
	NONE((int)Byte.MAX_VALUE);

    /**
     * NOTE: Enum constructor must have private or package scope. You can not use the public access modifier.
     */
    SPELL(long value) {
        this.value = value;
    }

    private final long value;

    /**
     * getValue
     * @return (@link long) value
     */
    public long getValue() { return value; }



	/**
	 * ToInt
	 * @return int value {@link int}
	 */
	public int ToInt() {
		return  (int)this.getValue();
	}

    /**
     * ToLong
     * @return long value {@link long}
     */
    public long ToLong() {
        return this.getValue();
    }




    /**
     * getName
     * @return name {@link String} of SUPUMODE
     */
    public String getName() {
        return (this.toString());
    }


}

