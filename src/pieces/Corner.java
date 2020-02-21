package pieces;

public class Corner extends Piece {
	private Color color1;
	private Color color2;
	private Color color3;
	
	private int orientation;
	final public static int ROTATED_LEFT = -1;
	final public static int ORIENTED = 0;	
	final public static int ROTATED_RIGHT = 1;
	
	/**
	 * Default Constructor
	 * Creates a colorless oriented corner.	
	 */
	Corner() {
		color1 = Color.None;
		color2 = Color.None;
		color3 = Color.None;
		orientation = ORIENTED;
	}
	
	/**
	 * Main Constructor. Creates a corner with the properties passed through.
	 * @param clr1 The color that faces up on an oriented corner.
	 * @param clr2 The color on the right side of an oriented corner
	 * @param clr3 The color on the left side of an oriented corner
	 * @param orient The orientation of the color. '0' for oriented, '-1' for twisted to the left, and '1' for twisted to the right.
	 */
	Corner(Color clr1, Color clr2, Color clr3, int orient) {
		color1 = clr1;
		color2 = clr2;
		color3 = clr3;
		orientation = orient; //TODO How to guarantee that this is a -1, 0, or 1?
	}
	
	/**
	 * Returns the values of each color on the corner.
	 * @return An array of the three colors of the corner. Order {Top, Right, Left}
	 */
	public Color[] getColorState() {
		Color[] toReturn = {color1, color2, color3}; //For the default orientation
		if (orientation == ROTATED_LEFT) {//Modify the return array if the corner isn't oriented.
			toReturn[0] = color2;
			toReturn[1] = color3;
			toReturn[2] = color1;
		}
		if (orientation == ROTATED_RIGHT) {//Modify the return array if the corner isn't oriented.
			toReturn[0] = color3;
			toReturn[1] = color1;
			toReturn[2] = color2;
		}
		return toReturn;
	}
	
	/**
	 * Rotates the corner.
	 * @param rotation Enter -1 to rotate to the left and 1 to rotate to the right
	 * TODO Make sure that the value passed in is only a -1 or 1.
	 */
	public void changeOrientation(int rotation) {
		orientation += rotation;
		if (orientation < -1) {orientation = 1;} //Two left rotations leave the corner rotated right.
		if (orientation > 1) {orientation = -1;} //Two right rotations leave the corner rotated left.
	}
	
	public String toString() {
		return "Corner";
	}
}
