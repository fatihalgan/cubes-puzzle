package com.t360.cubespuzzle.test;

import org.junit.Test;

import com.t360.cubespuzzle.CellState;
import com.t360.cubespuzzle.Edge;

import static org.junit.Assert.*;

public class EdgeTest {

	@Test(expected=IllegalArgumentException.class)
	public void createEdgeFromNullRep() {
		String rep = null;
		Edge edge = new Edge(rep);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createEdgeFromEmptyRep() {
		String rep = "";
		Edge edge = new Edge(rep);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createEdgeFromUnexpectedSize() {
		String rep = "o  o";
		Edge edge = new Edge(rep);
	}
	
	@Test
	public void createValidEdge() {
		String rep = "o o o";
		Edge edge = new Edge(rep);
		assertEquals(edge.toString(), rep);
	}
	
	@Test
	public void isLeftCornerEmpty() {
		String rep = "o oo ";
		Edge edge = new Edge(rep);
		assertEquals(false, edge.getLeftCorner() == CellState.VOID_CELL);
	}
	
	@Test
	public void isRightCornerEmpty() {
		String rep = "o oo ";
		Edge edge = new Edge(rep);
		assertEquals(true, edge.getRightCorner() == CellState.VOID_CELL);
	}
	
	@Test
	public void getCellAt() {
		String rep = "o oo ";
		Edge edge = new Edge(rep);
		assertEquals(edge.getCellAt(2).toChar(), 'o');
	}
	
	@Test
	public void testInvert() {
		String rep = "o oo ";
		Edge edge = new Edge(rep).invert();
		assertEquals(" o  o", edge.toString());
	}
	
	@Test
	public void testFlip() {
		String rep = "o oo ";
		Edge edge = new Edge(rep).flip();
		assertEquals(" oo o", edge.toString());
	}
	
	@Test
	public void testCornersDoesNotFit() {
		Edge edge1 = new Edge("o oo ");
		Edge edge2 = new Edge("oo  o");
		assertEquals(edge1.fitsWith(edge2), false);
	}
	
	@Test
	public void testCornerRemainsVoid() {
		Edge edge1 = new Edge("  oo ");
		Edge edge2 = new Edge(" o  o");
		assertEquals(edge1.fitsWith(edge2), true);
	}
	
	@Test
	public void testBodyDoesNotFit() {
		Edge edge1 = new Edge("o oo ");
		Edge edge2 = new Edge(" oo o");
		assertEquals(edge1.fitsWith(edge2), false);
	}
	
	@Test
	public void testFitsWith() {
		Edge edge1 = new Edge("o oo ");
		Edge edge2 = new Edge(" o  o");
		assertEquals(edge1.fitsWith(edge2), true);
	}
	
	
}
