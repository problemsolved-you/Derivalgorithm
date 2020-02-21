package pieces;

public abstract class Piece {
	Piece() {};
	
	/**
	 * Rotates a piece to change its orientation
	 * @param rotation: enter a positive number to rotate to the right, a negative number to rotate to the left.
	 */
	public abstract void changeOrientation(int rotation);
	
	/**
	 * Pulls an organized array of the enum colors of a piece.
	 * @return An array of the enum colors of a piece.
	 */
	protected abstract Color[] getColorState();
	
	/**
	 * Converts a piece's colors into a char[] for state reading and printing
	 * @return An array of the character representations of a pieces colors. The array is organized with the up faceing color first, followed by the side colors (right -> left) if applicable.
	 */
	public char[] getColor() {
		Color[] toConvert = getColorState();
		char[] colorState = new char[toConvert.length];
		for(int i = 0; i < toConvert.length; i++) {
			colorState[i] = getColorChar(toConvert[i]);
		}
		return colorState;
	}
	
	/**
	 * Converts a Color enum value into a single character representation.
	 * @param color The Color value to convert.
	 * @return The character representation of the parameter Color.
	 */
	private char getColorChar(Color color) {
		char toReturn = 'N';
		switch (color) {
			case White: 
				toReturn = 'W';
				break;
			case Yellow:
				toReturn = 'Y';
				break;
			case Green:
				toReturn = 'G';
				break;
			case Blue:
				toReturn = 'B';
				break;
			case Red:
				toReturn = 'R';
				break;
			case Orange:
				toReturn = 'O';
				break;
			case None:
				toReturn = ' ';
				break;
		}
		return toReturn;
	}
}
