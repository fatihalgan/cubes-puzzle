package com.t360.cubespuzzle;

/**
 * Represents a two dimensional piece (surface). A piece consists of four Edges(left, top, right, bottom),
 * and a body in the middle which consists of only FILLED_CELL cells
 * @author db2admin
 *
 */
public class Piece {
	
	private int id;
	
	private Edge left;
    private Edge top;
    private Edge right;
    private Edge bottom;
    
    private CellState[] body;
    
    private Piece(int id) {
    	super();
    	this.id = id;
    }
    
    /**
     * To construct a piece from a two dimensional ASCII representation
     * The 0th and the last rows form the bottom and top edges.
     * The 0th and the last columns form the left and right edges.
     * @param id
     * @param rows
     */
    public Piece(int id, String[] rows) {
    	this.id = id;
    	this.top = new Edge(rows[0]);
    	this.bottom = new Edge(rows[Edge.CELL_NUMS - 1]);
    	this.left = buildVerticalEdge(rows, 0);
    	this.right = buildVerticalEdge(rows, Edge.CELL_NUMS - 1);
    	this.body = new CellState[Edge.CELL_NUMS - 2];
    	for(int i = 0; i < body.length; i++) {
    		body[i] = CellState.FILLED_CELL;
    	}
    }
    
    public int getId() {
		return id;
	}

	private Edge buildVerticalEdge(String[] rows, int index) {
    	CellState[] cells = new CellState[rows.length];
    	for(int i = 0; i < rows.length; i++) {
    		cells[i] = CellState.factory(rows[i].charAt(index));
    	}
    	return new Edge(cells);
    }
    
    public Edge getTopEdge() {
    	return top;
    }

    public Edge getLeftEdge() {
    	return left;
    }

    public Edge getRightEdge() {
    	return right;
    }

    public Edge getBottomEdge() {
    	return bottom;
    }
    
    public CellState getTopLeftCorner() {
        return getTopEdge().getLeftCorner();
    }
    
    public CellState getTopRightCorner() {
    	return getTopEdge().getRightCorner();
    }
    
    public CellState getBottomLeftCorner() {
    	return getBottomEdge().getLeftCorner();
    }
    
    public CellState getBottomRigtCorner() {
    	return getBottomEdge().getRightCorner();
    }
    
    /**
     * Rotates the piece to left
     */
    public void rotateLeft() {
    	Edge tmp = top;
        top = right;
        right = bottom.flip();
        bottom = left;
        left = tmp.flip();
    }
    
    /**
     * Rotates the piece to right
     */
	public void rotateRight() {
		Edge tmp = top;
        top = left.flip();
        left = bottom;
        bottom = right.flip();
        right = tmp;
	}
	
	/**
	 * Flips (mirrors) the piece
	 */
	public void flip() {
		Edge tmp = left;
		top = top.flip();
        bottom = bottom.flip();
        left = right;
        right = tmp;
	}
	
	@Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(bottom.toString());
		sb.append(left.toString());
		sb.append(right.toString());
		sb.append(top.toString());
		return sb.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Piece other = (Piece) obj;
        if (!bottom.equals(other.bottom)) return false;
        if (!left.equals(other.left)) return false;
        if (!right.equals(other.right)) return false;
        if (!top.equals(other.top)) return false;
        return true;
    }
    
    /**
     * Returns a two dimensional ASCII representation of the piece
     * @return
     */
    public String[] getASCIIRepresentation() {
		String[] result = new String[Edge.CELL_NUMS];
        result[0] = top.toString();
        for (int i = 1; i < Edge.CELL_NUMS - 1; i++) {
            StringBuilder sb = new StringBuilder(Edge.CELL_NUMS);
            sb.append(left.getCellAt(i).toString());
            sb.append(CellState.toString(body));
            sb.append(right.getCellAt(i));
            result[i] = sb.toString();
        }
        result[Edge.CELL_NUMS - 1] = bottom.toString();
        return result;
	}
    
    /**
     * Deep copy
     */
    public Piece clone() {
    	Piece piece = new Piece(this.id);
    	piece.body = this.body;
    	piece.top = new Edge(top.toString());
    	piece.right = new Edge(right.toString());
    	piece.bottom = new Edge(bottom.toString());
    	piece.left = new Edge(left.toString());
    	return piece;
    }
}
