package com.t360.cubespuzzle;

import java.util.ArrayList;
import java.util.List;

public class CubesPuzzle {
	
	public Cube findSolution(List<Piece> pieces, Cube cube) {
		//if cube is complete, return to pevious recursion immediately since 
		//there should be no remaining pieces and the solution is found.
		if (cube.isComplete()) {
			return cube;
		}
		for (Piece piece : pieces) {
			for(int k = 0;  k < 2; k++) {
				for(int i = 0; i < 4; i++) {
					if(tryPiece(piece, cube)) {
						//The pieces interlocks with the partial cube,
						//take a deep copy of the cube and insert the piece into it (for the next level recursion),  
						//take a deep copy of the pieces collection and remove the piece from it(for the next level recursion),
						//and call findSolution again for the calculation of the remaining pieces.
						Cube temp = cube.clone();
						temp.insertPiece(piece);
						List<Piece> newList = new ArrayList<Piece>(pieces);
						newList.remove(piece);
						Cube solution = findSolution(newList, temp);
						// if a solution is found return to all previous recursions cascadingly
						//at this point
						if (solution != null) return solution;
					}
					piece.rotateLeft();
				}
				piece.flip();
			}
        }
        return null;
	}
	
	private boolean tryPiece(Piece piece, Cube cube) {
		if(cube.fitsPiece(piece)) {
			return true;
		}
		return false;
	}
}
