package pieces;

public class Edge extends Piece {
	private Color color1;
	private Color color2;
	boolean oriented;
	
	/**
	 * Default Constructor. 
	 * Creates a colorless edge.
	 */
	Edge() {
		color1 = Color.None;
		color2 = Color.None;
		oriented = true;
	}
	
	/**
	 * Main Constructor
	 * @param clr1 The upward facing color on an oriented edge.
	 * @param clr2 The side color on an oriented edge.
	 * @param orient Pass 'true' if the edge is oriented, 'false' if it isn't.
	 */
	Edge(Color clr1, Color clr2, boolean orient) {
		color1 = clr1;
		color2 = clr2;
		oriented = orient;
	}
	
	/**
	 * Returns the colors of the edge.
	 * @return An array of the colors of the edge. {Top, Side}
	 */
	public Color[] getColorState() {
		Color[] toReturn = {color1, color2};
		if (!oriented) { //swap the colors in the array.
			toReturn[0] = color2;
			toReturn[1] = color1;
		}
		return toReturn;
	}
	
	/**
	 * Flips the orientation of the edge.
	 */
	public void changeOrientation(int rotation) {
		//The int doesn't matter here. If the method is called the orientation will change.
		oriented = !oriented;
	}
	
	public String toString() {
		return "Edge";
	}
}
