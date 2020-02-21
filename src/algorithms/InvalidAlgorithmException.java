package algorithms;

public class InvalidAlgorithmException extends RuntimeException {
	
	public InvalidAlgorithmException() {
		super("The Algorithm was invalid\r\n" + 
				"Algorithms must be written with a space between each face turn. No comma separation.\r\n" + 
				"Algorithms must be written in standard notation (Faces: U, D, R, L, F, B, M, S, E, x, y, z) (Superscripts: none, ', 2)\r\n" + 
				"Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by \"w\" (Uw, Fw, Rw, Lw, Fw, Bw).");
	}
	
	public InvalidAlgorithmException(String badAlg) {
		super(badAlg + " is not a valid Rubik's Cube turn.\r\n" + 
				"Algorithms must be written with a space between each face turn. No comma separation.\r\n" + 
				"Algorithms must be written in standard notation (Faces: U, D, R, L, F, B, M, S, E, x, y, z) (Superscripts: none, ', 2)\r\n" + 
				"Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by \"w\" (Uw, Fw, Rw, Lw, Fw, Bw).");
	}
}
