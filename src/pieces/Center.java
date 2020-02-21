package pieces;

public class Center extends Piece {
	private Color color;
	
	/**
	 * Default constructor. Creates a colorless center.
	 */
	Center() {
		color = Color.None;
	}
	
	/**
	 * Main constructor
	 * @param clr The color of the Center.
	 */
	Center (Color clr) {
		color = clr;
	}
	
	/**
	 * Returns the current color of the piece.
	 * @return
	 */
	public Color[] getColorState() {
		Color[] toReturn = {color};
		return toReturn;
	}
	
	public void changeOrientation(int rotation) {
		//Do nothing since the center doesn't have an orientation. 
		//This can be updated later for picture cube simulation
	}
	
	public String toString() {
		return "Center";
	}
}
