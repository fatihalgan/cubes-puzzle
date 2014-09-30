package com.t360.cubespuzzle.test;

import org.junit.Test;

import static org.junit.Assert.*;

import com.t360.cubespuzzle.CellState;

public class CellStateTest {

	@Test(expected=IllegalArgumentException.class)
	public void createInvalidCellStateChar() {
		char state = 'x';
		CellState.factory(state);
	}
	
	@Test
	public void createValidCellStateChar() {
		char state = ' ';
		CellState cell = CellState.factory(state);
		assertEquals(CellState.VOID_CELL, cell);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidRepresentationFromNullString() {
		String rep = null;
		CellState[] cells = CellState.factory(rep);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createInvalidRepresentationFromEmptyString() {
		String rep = "";
		CellState[] cells = CellState.factory(rep);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidRepresentationFromString() {
		String rep = " oo x ";
		CellState[] cells = CellState.factory(rep);
	}
	
	@Test
	public void createValidRepresentationFromString() {
		String rep = " oo  o";
		CellState[] cells = CellState.factory(rep);
		assertEquals(cells.length, rep.length());
	}
	
	@Test
	public void testToString() {
		String rep = " oo ";
		CellState[] cells = CellState.factory(rep);
		assertEquals(CellState.toString(cells), rep);
	}
	
	@Test
	public void invertSingle() {
		char cell = ' ';
		assertEquals(CellState.invert(CellState.factory(cell)).toChar(), 'o');
	}
	
	@Test
	public void testInvert() {
		String rep = " oo o";
		CellState[] cells = CellState.factory(rep);
		assertEquals(CellState.toString(CellState.invert(cells)), "o  o ");
	}
	
	@Test
	public void testMirror() {
		String rep = " oo o";
		CellState[] cells = CellState.factory(rep);
		assertEquals(CellState.toString(CellState.mirror(cells)), "o oo ");
	}
}
