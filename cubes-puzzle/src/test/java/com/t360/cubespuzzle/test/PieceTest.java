package com.t360.cubespuzzle.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.t360.cubespuzzle.Piece;

public class PieceTest {

	String[] blue1 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  "};
	String[] blue2 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o"};
	String[] blue3 = { "  o  ", " oooo", "oooo ", " oooo", "  o  "};
	
	String[] red1 = {"   oo", " ooo ", "ooooo", " ooo ", " o oo"};
	String[] red2 = {" o o ", "oooo ", " oooo", "oooo ", " o   "};
	String[] red3 = {" oo o", "ooooo", " ooo ", "ooooo", "o  oo"};
	
	String[] blue2RotateRight = {"oo oo", " ooo ", "ooooo", " ooo ", "oo oo"}; 
	String[] red1RotateLeft = {"o o o", "ooooo", " ooo ", " oooo", "  o  "};
	String[] blue3Flip = {"  o  ", "oooo ", " oooo", "oooo ", "  o  "};
	String[] red3Flip = {"o oo ", "ooooo", " ooo ", "ooooo", "oo  o"};
	
	
	Piece b1, b2, b3 = null;
	Piece r1, r2, r3 = null;
	Piece r1RotateLeft, b2RotateRight = null;
	Piece b3Flip, r3Flip = null;
	
	@Before
	public void setUp() {
		b1 = new Piece(1, blue1);
		b2 = new Piece(2, blue2);
		b3 = new Piece(3, blue3);
		r1 = new Piece(1, red1);
		r2 = new Piece(2, red2);
		r3 = new Piece(3, red3);
		r1RotateLeft = new Piece(1, red1RotateLeft);
		b2RotateRight = new Piece(2, blue2RotateRight);
		b3Flip = new Piece(3, blue3Flip);
		r3Flip = new Piece(3, red3Flip);
	}
	
	@Test
	public void testCreatePieceSurface() {
		assertEquals(b1.getTopEdge().toString(), "  o  ");
		assertEquals(b1.getRightEdge().toString(), "  o  ");
		assertEquals(b1.getBottomEdge().toString(), "  o  ");
		assertEquals(b1.getLeftEdge().toString(), "  o  ");
		assertEquals(b2.getTopEdge().toString(), "o o o");
		assertEquals(b2.getRightEdge().toString(), "oo oo");
		assertEquals(b2.getBottomEdge().toString(), "o o o");
		assertEquals(b2.getLeftEdge().toString(), "oo oo");
		assertEquals(b3.getTopEdge().toString(), "  o  ");
		assertEquals(b3.getRightEdge().toString(), " o o ");
		assertEquals(b3.getBottomEdge().toString(), "  o  ");
		assertEquals(b3.getLeftEdge().toString(), "  o  ");
	}
	
	@Test
	public void testRotateRight() {
		b2.rotateRight();
		assertEquals(b2, b2RotateRight);
	}
	
	@Test
	public void testRotateLeft() {
		r1.rotateLeft();
		assertEquals(r1, r1RotateLeft);
	}
	
	@Test
	public void testFlip() {
		b3.flip();
		assertEquals(b3, b3Flip);
		r3.flip();
		assertEquals(r3, r3Flip);
	}
	
	@Test
	public void testEdgeFits() {
		assertTrue(b1.getLeftEdge().fitsWith(b3.getRightEdge()));
		assertTrue(b1.getRightEdge().fitsWith(b2.getLeftEdge()));
		assertTrue(r1.getRightEdge().fitsWith(r2.getLeftEdge()));
	}
	
	@Test
	public void testEdgeDoesNotFit() {
		assertFalse(b1.getBottomEdge().fitsWith(b2.getTopEdge()));
		assertFalse(b1.getRightEdge().fitsWith(b3.getLeftEdge()));
		assertFalse(r1.getRightEdge().fitsWith(r3.getLeftEdge()));
	}
	
	
}
