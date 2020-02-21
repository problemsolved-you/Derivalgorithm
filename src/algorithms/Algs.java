package algorithms;

import pieces.RubiksCube;
import java.util.LinkedList;
import java.io.InputStream;
import java.util.Scanner;


public class Algs {
	
	private static LinkedList<String> validTurns = new LinkedList<String>() {{
		InputStream turns = getClass().getResourceAsStream("/ValidFaceTurns.txt");
		Scanner inputTurns = new Scanner(turns);
		while(inputTurns.hasNextLine()) {
			String turn = inputTurns.nextLine().trim();
			add(turn);
		}
		inputTurns.close();
	}}; 
	
	/**
	 * Checks to see if an alg is valid or not 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B, M, S, E, x, y, z) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * A message will be printed to the console naming any parts of the algorithm that were not able to be executed.
	 * @param alg The string of the algorithm to be executed. 
	 * @param alg
	 * @throws InvalidAlgorithmException if the algorithm doesn't fit the rules above.
	 */
	private static String[] isValidAlg(String alg) throws InvalidAlgorithmException {
		String[] algSteps = alg.split(" ");
		for(String turn : algSteps) {
			if(!validTurns.contains(turn)) throw new InvalidAlgorithmException(turn);
		}
		return algSteps;
	}
	
	/**
	 * Converts a written algorithm to face turns on a cube. 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * A message will be printed to the console naming any parts of the algorithm that were not able to be executed.
	 * @param alg The string of the algorithm to be executed.
	 * @param cube The cube object to perform the algorithm on.
	 */
	public static void executeAlg(String alg, RubiksCube cube) throws InvalidAlgorithmException {
		String[] algSteps = isValidAlg(alg);
		for(String turn : algSteps) {
			switch (turn) {
				//Basic Face Turns
				case "U":
					cube.U();
					break;
				case "U'":
					cube.UPrime();
					break;
				case "U2":
					cube.U2();
					break;
				case "D":
					cube.D();
					break;
				case "D'":
					cube.DPrime();
					break;
				case "D2":
					cube.D2();
					break;
				case "R":
					cube.R();
					break;
				case "R'":
					cube.RPrime();
					break;
				case "R2":
					cube.R2();
					break;
				case "L":
					cube.L();
					break;
				case "L'":
					cube.LPrime();
					break;
				case "L2":
					cube.L2();
					break;
				case "F":
					cube.F();
					break;
				case "F'":
					cube.FPrime();
					break;
				case "F2":
					cube.F2();
					break;
				case "B":
					cube.B();
					break;
				case "B'":
					cube.BPrime();
					break;
				case "B2":
					cube.B2();
					break;
				//Slice Turns
				case "M":
					cube.M();
					break;
				case "M'":
					cube.MPrime();
					break;
				case "M2":
					cube.M2();
					break;
				case "S":
					cube.S();
					break;
				case "S'":
					cube.SPrime();
					break;
				case "S2'":
					cube.S2();
					break;
				case "E":
					cube.E();
					break;
				case "E'":
					cube.EPrime();
					break;
				case "E2":
					cube.E2();
					break;
				//Double Layer Face turns
				case "u":
					cube.u();
					break;
				case "Uw":
					cube.u();
					break;
				case "u'":
					cube.uPrime();
					break;
				case "Uw'":
					cube.uPrime();
					break;
				case "u2":
					cube.u2();
					break;
				case "Uw2":
					cube.u2();
					break;
				case "d":
					cube.d();
					break;
				case "Dw":
					cube.d();
					break;
				case "d'":
					cube.dPrime();
					break;
				case "Dw'":
					cube.dPrime();
					break;
				case "d2":
					cube.d2();
					break;
				case "Dw2":
					cube.d2();
					break;
				case "r":
					cube.r();
					break;
				case "Rw":
					cube.r();
					break;
				case "r'":
					cube.rPrime();
					break;
				case "Rw'":
					cube.rPrime();
					break;
				case "r2":
					cube.r2();
					break;
				case "Rw2":
					cube.r2();
					break;
				case "l":
					cube.l();
					break;
				case "Lw":
					cube.l();
					break;
				case "l'":
					cube.lPrime();
					break;
				case "Lw'":
					cube.lPrime();
					break;
				case "l2":
					cube.l2();
					break;
				case "Lw2":
					cube.l2();
					break;
				case "f":
					cube.f();
					break;
				case "Fw":
					cube.f();
					break;
				case "f'":
					cube.fPrime();
					break;
				case "Fw'":
					cube.fPrime();
					break;
				case "f2":
					cube.f2();
					break;
				case "Fw2":
					cube.f2();
					break;
				case "b":
					cube.b();
					break;
				case "Bw":
					cube.b();
					break;
				case "b'":
					cube.bPrime();
					break;
				case "Bw'":
					cube.bPrime();
					break;
				case "b2":
					cube.b2();
					break;
				case "Bw2":
					cube.b2();
					break;
				//Whole Cube Rotations
				case "x":
					cube.x();
					break;
				case "x'":
					cube.xPrime();
					break;
				case "x2":
					cube.x2();
					break;
				case "y":
					cube.y();
					break;
				case "y'":
					cube.yPrime();
					break;
				case "y2":
					cube.y2();
					break;
				case "z":
					cube.z();
					break;
				case "z'":
					cube.zPrime();
					break;
				case "z2":
					cube.z2();
					break;
				case "":
					break;
				default:
					System.out.println("Unable to decode *" + turn + "*");
			}
		}
	}

	/**
	 * Computes the reverse of any given alg. 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * @param alg The string of the algorithm to be reversed. 
	 * @return A string containing the reversed algorithm
	 */
	public static String reverse(String alg) throws InvalidAlgorithmException {
		String reverse = "";
		String[] algSteps = isValidAlg(alg);
		for(int i = algSteps.length - 1; i >= 0; i--) {
			reverse += algSteps[i] + " ";
		}
		return reverse;
	}
	
	/**
	 * Computes the mirror of any given alg. (Mirrors across the x-rotation axis) 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * @param alg The string of the algorithm to be mirrored. 
	 * @return A string containing the mirrored algorithm
	 */
	public static String mirror(String alg) throws InvalidAlgorithmException {
		String mirror = "";
		String[] tempMirror = isValidAlg(alg);
		for(int i = 0; i < tempMirror.length; i++) {
			switch(tempMirror[i]) {
				case "R":
					tempMirror[i] = "L'";
					break;
				case "R'":
					tempMirror[i] = "L";
					break;
				case "R2":
					tempMirror[i] = "L2";
					break;
				case "r":
					tempMirror[i] = "l'";
					break;
				case "r'":
					tempMirror[i] = "l";
					break;
				case "r2":
					tempMirror[i] = "l2";
					break;
				case "Rw":
					tempMirror[i] = "Lw'";
					break;
				case "Rw'":
					tempMirror[i] = "Lw";
					break;
				case "Rw2":
					tempMirror[i] = "Lw2";
					break;
				case "L":
					tempMirror[i] = "R'";
					break;
				case "L'":
					tempMirror[i] = "R";
					break;
				case "L2":
					tempMirror[i] = "R2";
					break;
				case "l":
					tempMirror[i] = "r'";
					break;
				case "l'":
					tempMirror[i] = "r";
					break;
				case "l2":
					tempMirror[i] = "r2";
					break;
				case "Lw":
					tempMirror[i] = "Rw'";
					break;
				case "Lw'":
					tempMirror[i] = "Rw";
					break;
				case "Lw2":
					tempMirror[i] = "Rw2";
					break;
				case "U":
					tempMirror[i] = "U'";
					break;
				case "U'":
					tempMirror[i] = "U";
					break;
				case "u":
					tempMirror[i] = "u'";
					break;
				case "u'":
					tempMirror[i] = "u";
					break;
				case "Uw":
					tempMirror[i] = "Uw'";
					break;
				case "Uw'":
					tempMirror[i] = "Uw";
					break;
				case "D":
					tempMirror[i] = "D'";
					break;
				case "D'":
					tempMirror[i] = "D";
					break;
				case "d":
					tempMirror[i] = "d'";
					break;
				case "d'":
					tempMirror[i] = "d";
					break;
				case "Dw":
					tempMirror[i] = "Dw'";
					break;
				case "Dw'":
					tempMirror[i] = "Dw";
					break;
				case "F":
					tempMirror[i] = "F'";
					break;
				case "F'":
					tempMirror[i] = "F";
					break;
				case "f":
					tempMirror[i] = "f'";
					break;
				case "f'":
					tempMirror[i] = "f";
					break;
				case "Fw":
					tempMirror[i] = "Fw'";
					break;
				case "Fw'":
					tempMirror[i] = "Fw";
					break;
				case "B":
					tempMirror[i] = "B'";
					break;
				case "B'":
					tempMirror[i] = "B";
					break;
				case "b":
					tempMirror[i] = "b'";
					break;
				case "b'":
					tempMirror[i] = "b";
					break;
				case "Bw":
					tempMirror[i] = "Bw'";
					break;
				case "Bw'":
					tempMirror[i] = "Bw";
					break;
				case "E":
					tempMirror[i] = "E'";
					break;
				case "E'":
					tempMirror[i] = "E";
					break;
				case "S":
					tempMirror[i] = "S'";
					break;
				case "S'":
					tempMirror[i] = "S";
					break;
				case "y":
					tempMirror[i] = "y'";
					break;
				case "y'":
					tempMirror[i] = "y";
					break;
				case "z":
					tempMirror[i] = "z'";
					break;
				case "z'":
					tempMirror[i] = "z";
					break;
				case "U2": //Fall Through Intentional.
				case "u2": //These moves are not modified during mirroring.
				case "Uw2":
				case "D2":
				case "d2":
				case "Dw2":
				case "F2":
				case "f2":
				case "Fw2":
				case "B2":
				case "b2":
				case "Bw2":
				case "x":
				case "x'":
				case "x2":
				case "y2":
				case "z2":
				case "M":
				case "M'":
				case "M2":
				case "S2":
				case "E2":
					break;
				default:
					System.out.println("Cannot mirror " + tempMirror[i]);
			}
			mirror += tempMirror[i] + " ";
		}
		return mirror;
	}
	
	/**
	 * Negates any given algorithm. 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * @param alg The string of the algorithm to be negated. 
	 * @return A string containing the negated algorithm.
	 */
	public static String negate(String alg) throws InvalidAlgorithmException {
		String negated = "";
		String[] tempNegated = isValidAlg(alg);
		for(int i = 0; i < tempNegated.length; i++) { //Look at each step of the algorithm.
			if(tempNegated[i].charAt(tempNegated[i].length() - 1) == '2') { //If the last character is a '2'
				negated += tempNegated[i] + " ";
			}
			else if(tempNegated[i].charAt(tempNegated[i].length() - 1) == '\'') { //If the last character is a prime
				for(int j = 0; j < tempNegated[i].length() - 1; j++) {//Go until right before the last character
					negated += tempNegated[i].charAt(j); //and add it all back into the alg 
				} //Basically cutting off the prime to negate this step.
				negated += " ";
			}
			else {
				negated += tempNegated[i] + "' "; //Otherwise, add a prime.
			}
		}
		return negated;
	}
	
	/**
	 * Computes the inverse of any given alg. 
	 * Algorithms must be written with a space between each face turn. No comma separation.
	 * Algorithms must be written in standard notation (Faces: U, D, R, L, F, B) (Superscripts: none, ', 2)
	 * Double layer turns may be denoted either with a lower-case letter (u, d, r, l, f, b) or an upper-case letter followed by "w" (Uw, Fw, Rw, Lw, Fw, Bw).
	 * @param alg The string of the algorithm to be reversed. 
	 * @return A string containing the inverse algorithm
	 */
	public static String inverse(String alg) {
		return negate(reverse(alg));
	}
	
	public static void scramble(RubiksCube cube) {
		String scramble = "";
		for(int i = 0; i < 20; i++) {
			int turnIndex = (int)(Math.random() * (validTurns.size() - 1));
			scramble += validTurns.get(turnIndex) + " ";
		}
		executeAlg(scramble, cube);
	}
}
