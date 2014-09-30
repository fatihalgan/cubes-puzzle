package com.t360.cubespuzzle;

/**
 * @author Fatih Algan
 * Represents one edge of a piece. Is represented by a row(array) of CellState sequence 
 */
public class Edge {

	public static final int CELL_NUMS = 5;
	
	/**
     * The char sequence representing the edge.
     */
    private final CellState[] representation;
    
    public Edge(CellState[] representation) {
    	if(representation == null) throw new IllegalArgumentException("Cannot create edge from null representation..");
    	if(representation.length != CELL_NUMS)
    		throw new IllegalArgumentException("Representation of edge does not match with the expected size: " + representation.length);
    	if(representation == null || representation.length == 0) {
    		throw new IllegalArgumentException("Represantation of an edge cannot be null or zero length..");
    	}
    	this.representation = representation;
    }
    
    public Edge(String representation) {
    	this(CellState.factory(representation));
    }
    
    public CellState getLeftCorner() {
    	return representation[0];
    }
    
    public CellState getRightCorner() {
    	return representation[representation.length - 1];
    }
    
    public CellState getCellAt(int index) {
    	return representation[index];
    }
    
    /**
     * Returns false if two edges cannot be interlocked
     * @param other
     * @return
     */
    public boolean fitsWith(Edge other) {
    	return fitsCornersWith(other) && fitsBodyWith(other);
    }
    
    /**
     * Returns false if two same corners are both FILLED_CELL, which prevents interlocking
     * of these two edges
     * @param other
     * @return
     */
    private boolean fitsCornersWith(Edge other) {
    	return 	(getLeftCorner() == CellState.VOID_CELL || other.getLeftCorner() == CellState.VOID_CELL)
                && (getRightCorner() == CellState.VOID_CELL || other.getRightCorner() == CellState.VOID_CELL);
    }
    
    /**
     * Returns false if bodies of two edges cannot be interlocked.
     * @param other
     * @return
     */
    private boolean fitsBodyWith(Edge other) {
    	return CellState.toString(getBody()).equals(
    		CellState.toString(other.invert().getBody()));
    }
    
    /**
     * Body is a row(array) of CellState sequence without the left and right corner cells
     * @return
     */
    private CellState[] getBody() {
    	CellState[] output = new CellState[CELL_NUMS - 2];
    	for(int i = 0; i < CELL_NUMS - 2; i++) {
    		output[i] = getCellAt(i + 1);
    	}
    	return output;
    }
    
    /**
     * Flips (mirrors) the Edge
     * @return
     */
    public Edge flip() {
    	return new Edge(CellState.mirror(representation));
    }
    
    /**
     * Inverts(VOID_CELL to FILLED_CELL and vice-versa) all the cells of the Edge 
     * @return
     */
    public Edge invert() {
    	return new Edge(CellState.invert(representation));
    }
    
    @Override
    public int hashCode() {
        String sequence = CellState.toString(representation);
        return sequence.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Edge other = (Edge) obj;
        if (representation == null) {
            if (other.representation != null)
                return false;
        } else if (!toString(). equals(other.toString()))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < representation.length; i++) {
    		sb.append(representation[i].toChar());
    	}
    	return sb.toString();
    }
}
