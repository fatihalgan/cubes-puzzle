package com.t360.cubespuzzle.test;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.t360.cubespuzzle.Cube;
import com.t360.cubespuzzle.CubesPuzzle;
import com.t360.cubespuzzle.Piece;
import static org.junit.Assert.*;

public class CubesPuzzleTest {

	//Define the pieces in this order, the same as given in the example solution so that
	//the first found solution will be the same as the example for easy comparison.
	String[] blue1 = {"  o  ", " ooo ", "ooooo", " ooo ", "  o  "};
	String[] blue2 = {"  o o", "ooooo", " ooo ", "ooooo", " o oo"};
	String[] blue3 = {" o o ", " ooo ", "ooooo", " ooo ", "  o  "};
	String[] blue4 = {"o o  ", "ooooo", " ooo ", "ooooo", " o o "};
	String[] blue5 = {"o o o", "ooooo", " ooo ", "ooooo", "o o o"};
	String[] blue6 = {" o o ", "oooo ", " oooo", "oooo ", "oo o "};
	
	String[] purple1 = {"oo o ", "oooo ", "oooo ", " oooo", "  o  "};
	String[] purple2 = {"   oo", "oooo ", "ooooo", " ooo ", " o o "};
	String[] purple3 = {" o   ", "oooo ", " oooo", "oooo ", "  o  "};
	String[] purple4 = {"oo oo", " oooo", "oooo ", " ooo ", " o o "};
	String[] purple5 = {"  o o", " oooo", "ooooo", "oooo ", "o oo "};
	String[] purple6 = {" o oo", " ooo ", " oooo", "oooo ", "oo o "};
	
	List<Piece> pieces = null, pieces2 = null; 
	
	@Before
	public void setUp() {
		pieces = new ArrayList<Piece>();
		pieces2 = new ArrayList<Piece>();
		Piece b1, b2, b3, b4, b5, b6 = null;
		Piece p1, p2, p3, p4, p5, p6 = null;
		b1 = new Piece(1, blue1);
		b2 = new Piece(2, blue2);
		b3 = new Piece(3, blue3);
		b4 = new Piece(4, blue4);
		b5 = new Piece(5, blue5);
		b6 = new Piece(6, blue6);
		pieces.add(b1);
		pieces.add(b2);
		pieces.add(b3);
		pieces.add(b4);
		pieces.add(b5);
		pieces.add(b6);
		p1 = new Piece(1, purple1);
		p2 = new Piece(2, purple2);
		p3 = new Piece(3, purple3);
		p4 = new Piece(4, purple4);
		p5 = new Piece(5, purple5);
		p6 = new Piece(6, purple6);
		pieces2.add(p1);
		pieces2.add(p2);
		pieces2.add(p3);
		pieces2.add(p4);
		pieces2.add(p5);
		pieces2.add(p6);
	}
	
	/**
	@Test
	public void testFindSolution() {
		CubesPuzzle puzzle = new CubesPuzzle();
		Cube cube = puzzle.findSolution(pieces, new Cube());
		assertNotNull(cube);
		cube.printASCII(new PrintWriter(System.out));
	}
	**/
	
	@Test
	public void testFindPurpleSolution() {
		CubesPuzzle puzzle = new CubesPuzzle();
		Cube cube = puzzle.findSolution(pieces2, new Cube());
		assertNotNull(cube);
		cube.printASCII(new PrintWriter(System.out));
	}
	
}
