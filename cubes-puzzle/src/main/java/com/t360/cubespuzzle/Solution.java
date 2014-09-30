package com.t360.cubespuzzle;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static final String[] blue1 = { "  o  ", " ooo ", "ooooo", " ooo ", "  o  "};
	public static final String[] blue2 = { "o o o", "ooooo", " ooo ", "ooooo", "o o o"};
	public static final String[] blue3 = { "  o  ", " oooo", "oooo ", " oooo", "  o  "};
	public static final String[] blue4 = { " o o ", "oooo ", " oooo", "oooo ", "oo o "};
	public static final String[] blue5 = { " o o ", "ooooo", " ooo ", "ooooo", "o o  "};
	public static final String[] blue6 = { " o o ", " oooo", "oooo ", " oooo", "oo oo"};
	
	public static void main(String[] args) {
		List<Piece> pieces = null; 
		pieces = new ArrayList<Piece>();
		Piece b1, b2, b3, b4, b5, b6 = null;
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
		CubesPuzzle puzzle = new CubesPuzzle();
		Cube cube = puzzle.findSolution(pieces, new Cube());
		cube.printASCII(new PrintWriter(System.out));
	}
}
