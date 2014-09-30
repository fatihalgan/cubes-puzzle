package com.t360.cubespuzzle.test;

import org.junit.Before;
import org.junit.Test;

import com.t360.cubespuzzle.Cube;
import com.t360.cubespuzzle.Piece;

import static org.junit.Assert.*;

public class CubeTest {

	String[] blue1 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  "};
	String[] blue2 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o"};
	String[] blue3 = { "  o  ", " oooo", "oooo ", " oooo", "  o  "};
	String[] blue4 = { " o o ", "oooo ", " oooo", "oooo ", "oo o "};
	String[] blue5 = { " o o ", "ooooo", " ooo ", "ooooo", "o o  "};
	String[] blue6 = { " o o ", " oooo", "oooo ", " oooo", "oo oo"};
	
	Piece b1, b2, b3, b4, b5, b6 = null;
	
	@Before
	public void setUp() {
		b1 = new Piece(1, blue1);
		b2 = new Piece(2, blue2);
		b3 = new Piece(3, blue3);
		b4 = new Piece(4, blue4);
		b5 = new Piece(5, blue5);
		b6 = new Piece(6, blue6);
	}
	
	@Test
	public void testMakeBlue() {
		Cube cube = new Cube();
		boolean success = false;
		if(cube.fitsPiece(b1)) {
			success = cube.insertPiece(b1);
		}
		assertEquals(true, success);
		success = false;
		b6.flip();
		b6.rotateLeft();
		if(cube.fitsPiece(b6)) {
			success = cube.insertPiece(b6);
		}
		assertEquals(true, success);
		success = false;
		b3.rotateLeft();
		if(cube.fitsPiece(b3)) {
			success = cube.insertPiece(b3);
		}
		assertEquals(true, success);
		success = false;
		b5.flip();
		b5.rotateRight();
		b5.rotateRight();
		if(cube.fitsPiece(b5)) {
			success = cube.insertPiece(b5);
		}
		assertEquals(true, success);
		success = false;
		if(cube.fitsPiece(b2)) {
			success = cube.insertPiece(b2);
		}
		assertTrue(success);
		success = false;
		if(cube.fitsPiece(b4)) {
			success = cube.insertPiece(b4);
		}
		assertTrue(success);
		
		assertTrue(cube.isComplete());
	}
}
