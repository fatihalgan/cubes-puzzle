package com.t360.cubespuzzle;

/**
 * 
 * @author Fatih Algan
 * Represents a single cell's state. Either filled(FILLED_CELL) or void(VOID_CELL) 
 * 
 */
public enum CellState {

	VOID_CELL(' '),
	FILLED_CELL('o');
	
	private final char state;
	
	private CellState(char state) {
		this.state = state;
	}
	
	/**
	 * Factory method to return a CellState enum. ('o' for FILLED_CELL, ' ' for VOID_CELL)
	 * @param cell
	 * @return
	 */
	public static CellState factory(final char cell) {
		if(cell != ' ' && cell != 'o') throw new IllegalArgumentException("Invalid Cell State: " + cell);
		for (CellState val : CellState.values())
            if (val.state == cell) return val;
		throw new IllegalArgumentException("Invalid Cell State: " + cell);
	}
	
	/**
	 * Returns a CellState array representing a row of cells from a sequence of 'o' s and ' ' s.
	 * @param sequence
	 * @return
	 */
	public static CellState[] factory(final String sequence) {
		if(sequence == null || sequence.length() == 0) throw new IllegalArgumentException("Invalid Representation: Representation cannot be null or empty");
		char[] chars = sequence.toCharArray();
		CellState[] states = new CellState[chars.length];
		for(int i = 0; i < chars.length; i++) {
			states[i] = factory(chars[i]);
		}
		return states;
	}
	
	/**
	 * Returns the character representation of CellState ('o' for FILLED_CELL, ' ' for VOID_CELL)
	 * @return
	 */
	public char toChar() {
		return state;
	}
	
	public String toString() {
		return String.valueOf(state);
	}
	
	/**
	 * Returns the String representation of a row(array) of CellState sequence 
	 * @param sequence
	 * @return
	 */
	public static String toString(CellState[] sequence) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sequence.length; i++) {
			sb.append(sequence[i].toString());
		}
		return sb.toString();
	}
	
	/**
	 * Inverses the CellState of input (return VOID_CELL for FILLED_CELL and vice-versa)
	 * @param input
	 * @return
	 */
	public static CellState invert(CellState input) {
		if(input == VOID_CELL) return FILLED_CELL;
		else return VOID_CELL;
	}
	
	/**
	 * Inverses a row(array) of CellState sequence
	 * @param input
	 * @return
	 */
	public static CellState[] invert(CellState[] input) {
		CellState[] output = new CellState[input.length];
		for(int i = 0; i < input.length; i++) {
			output[i] = invert(input[i]);
		}
		return output;
	}
	
	/**
	 * Mirrors (flips) a row(array) of CellState sequence
	 * @param input
	 * @return
	 */
	public static CellState[] mirror(CellState[] input) {
		CellState[] output = new CellState[input.length];
		for(int i = 0; i < input.length; i++) {
			output[i] = input[(input.length - 1) - i];
		}
		return output;
	}
}
