package com.t360.cubespuzzle;

import java.io.PrintWriter;

/**
 * Represents a cube which consists of interlocking of six Pieces(surface), each of which forms
 * a surface. A cube also consists of 12 Edges.
 * Each piece is represented by a number and the open form of the cube is represented as following.
 * 
 *       ******* ******* *******
 *       ** 1 ** ** 2 ** ** 3 **
 *       **   ** **   ** **   **
 *       ******* ******* *******
 *               *******
 *               ** 4 **
 *               **   **
 *               *******
 *               *******
 *               ** 5 **
 *               **   **
 *               *******
 *               *******
 *               ** 6 **
 *               **   **
 *               *******
 * These pieces correspond to pieces 0 to NUM_FACES in the @pieces array.
 * If you change the ordering of the pieces then the calculation of the fitsPieces method
 * has to change as well accordingly.
 * 
 * @author db2admin
 *
 */
public class Cube {

	private static final int NUM_FACES = 6;
	public Piece[] pieces = new Piece[NUM_FACES];
	
	/**
	 * Current
	 */
	private int current = 0;
	
	public Cube() {
		super();
	}
	
	/**
	 * A corner constitutes interlocking of 3 edge corners. So, to be able to interlock,
	 * only one corner needs to have a FILLED_CELL and the other two VOID_CELL s. 
	 * @param corner1
	 * @param corner2
	 * @param corner3
	 * @return
	 */
	private boolean fitsCorners(final boolean corner1, final boolean corner2, final boolean corner3) {
		return (corner1 && !corner2 && !corner3)
				|| (!corner1 && corner2 && !corner3)
				|| (!corner1 && !corner2 && corner3);
	}
	
	/**
	 * Inserts a new piece to the next available (void) surface of the cube
	 * @param piece
	 * @return
	 */
	public boolean insertPiece(Piece piece) {
		if(current == NUM_FACES) return false;
		pieces[current] = piece;
		current++;
		return true;
	}
	
	/**
	 * Removes the last inserted piece from the cube
	 * @return
	 */
	public Piece removeCurrent() {
		if(current == 0) return null;
		current--;
		Piece returnVal = pieces[current];
		pieces[current] = null;
		return returnVal;
	}
	
	/**
	 * Returns true if a new piece can be interlocked at the next available position
	 * with the already existing pieces of the cube
	 * @param piece
	 * @return
	 */
	public boolean fitsPiece(final Piece piece) {
		try {
			if(pieces[0].getId() == 6 && pieces[1].getId() == 4 && 
					pieces[2].getId() == 2 && pieces[3].getId() == 3
					&& piece.getId() == 1) {
				System.out.println("Checking piece 5");
			}
		} catch(RuntimeException e) {
			
		}
		if(current == 0) return true;
		if(current == 1) {
			return pieces[0].getRightEdge().fitsWith(piece.getLeftEdge());
		}
		if(current == 2) {
			return pieces[1].getRightEdge().fitsWith(piece.getLeftEdge());
		}
		if(current == 3) {
			return pieces[0].getBottomEdge().fitsWith(piece.getLeftEdge())
					&& pieces[1].getBottomEdge().fitsWith(piece.getTopEdge())
					&& pieces[2].getBottomEdge().fitsWith(piece.getRightEdge())
					&& fitsCorners(pieces[0].getBottomRigtCorner() == CellState.FILLED_CELL, 
						pieces[1].getBottomLeftCorner() == CellState.FILLED_CELL, 
						piece.getTopLeftCorner() == CellState.FILLED_CELL)
					&& fitsCorners(pieces[1].getBottomRigtCorner() == CellState.FILLED_CELL, 
						pieces[2].getBottomLeftCorner() == CellState.FILLED_CELL, 
						piece.getTopRightCorner() == CellState.FILLED_CELL);
		}
		if(current == 4) {
			return pieces[0].getLeftEdge().fitsWith(piece.getLeftEdge())
					&& pieces[2].getRightEdge().fitsWith(piece.getRightEdge())
					&& pieces[3].getBottomEdge().fitsWith(piece.getTopEdge())
					&& fitsCorners(pieces[0].getBottomLeftCorner() == CellState.FILLED_CELL,
						pieces[3].getBottomLeftCorner() == CellState.FILLED_CELL, 
						piece.getTopLeftCorner() == CellState.FILLED_CELL)
					&& fitsCorners(pieces[2].getBottomRigtCorner() == CellState.FILLED_CELL,
						pieces[3].getBottomRigtCorner() == CellState.FILLED_CELL, 
						piece.getTopRightCorner() == CellState.FILLED_CELL);
		}
		if(current == 5) {
			return pieces[0].getTopEdge().fitsWith(piece.getLeftEdge())
					&& pieces[1].getTopEdge().fitsWith(piece.getBottomEdge())
					&& pieces[2].getTopEdge().fitsWith(piece.getRightEdge())
					&& pieces[4].getBottomEdge().fitsWith(piece.getTopEdge())
					&& fitsCorners(piece.getTopLeftCorner() == CellState.FILLED_CELL,
						pieces[0].getTopLeftCorner() == CellState.FILLED_CELL, 
						pieces[4].getBottomLeftCorner() == CellState.FILLED_CELL)
					&& fitsCorners(piece.getTopRightCorner() == CellState.FILLED_CELL, 
						pieces[2].getTopRightCorner() == CellState.FILLED_CELL, 
						pieces[4].getBottomRigtCorner() == CellState.FILLED_CELL)
					&& fitsCorners(piece.getBottomLeftCorner() == CellState.FILLED_CELL, 
						pieces[0].getTopRightCorner() == CellState.FILLED_CELL, 
						pieces[1].getTopLeftCorner() == CellState.FILLED_CELL)
					&& fitsCorners(piece.getBottomRigtCorner() == CellState.FILLED_CELL, 
						pieces[1].getTopRightCorner() == CellState.FILLED_CELL, 
						pieces[2].getTopLeftCorner() == CellState.FILLED_CELL);
		}
		return false;
	}
	
	/**
	 * Returns true if all NUM_FACES pieces are inserted
	 * @return
	 */
	public boolean isComplete() {
		return current == NUM_FACES;
	}
	
	/**
	 * Prints the open form ASCII representation of the cube to the @out writer param 
	 * @param out
	 */
	public void printASCII(PrintWriter out) {
		final String[] piece0 = pieces[0].getASCIIRepresentation();
		final String[] piece1 = pieces[1].getASCIIRepresentation();
		final String[] piece2 = pieces[2].getASCIIRepresentation();
		for(int i = 0; i < Edge.CELL_NUMS; i++) {
			out.print(piece0[i]);
			out.print(piece1[i]);
			out.println(piece2[i]);
		}
		printPiece(pieces[3].getASCIIRepresentation(), out);
		printPiece(pieces[4].getASCIIRepresentation(), out);
		printPiece(pieces[5].getASCIIRepresentation(), out);
		out.flush();
	}
	
	private void printPiece(String[] piece, PrintWriter out) {
		for (int i = 0; i < Edge.CELL_NUMS; i++) {
			printSpace(out);
			out.print(piece[i]);
			printSpace(out);
			out.println();
		}
	}
	
	private void printSpace(PrintWriter out) {
		for(int i = 0; i < Edge.CELL_NUMS; i++) {
			out.print(" ");
		}
	}
	
	/**
	 * Deep copy
	 */
	public Cube clone() {
		Cube cube = new Cube();
		cube.current = current;
		for(int i = 0; i < pieces.length; i++) {
			if(pieces[i] != null) cube.pieces[i] = pieces[i].clone();
		}
		return cube;
	}
	
}
